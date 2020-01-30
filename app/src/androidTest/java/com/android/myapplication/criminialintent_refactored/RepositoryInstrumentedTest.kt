package com.android.myapplication.criminialintent_refactored

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.android.myapplication.criminialintent_refactored.database.CrimeDao
import com.android.myapplication.criminialintent_refactored.database.CrimeDb
import com.android.myapplication.criminialintent_refactored.database.CrimeEntity
import com.nhaarman.mockitokotlin2.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class RepositoryInstrumentedTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val crimeDao: CrimeDao = Mockito.spy(
        Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            CrimeDb::class.java
        ).build().crimeDao()
    )
    private val dataTransformer: DataTransformer = mock()
    private val SUT = Repository(crimeDao, dataTransformer)

    @Test
    fun addCrime_shouldTriggerAddEvent() {
        val crime = CrimeEntity()
        val crimeModel =
            CrimeModel(crime.id, crime.title, crime.date, crime.isSolved, crime.suspect)
        val mockObserver = mock<Observer<List<CrimeModel>?>>()
        SUT.crimesLiveData.observeForever(mockObserver)
        whenever(dataTransformer.transformListToModel(any())).thenReturn(listOf(crimeModel))

        crimeDao.addCrime(crime)

        verify(mockObserver).onChanged(eq(listOf(crimeModel)))
    }
}