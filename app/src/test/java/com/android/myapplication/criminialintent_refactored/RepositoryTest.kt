package com.android.myapplication.criminialintent_refactored

import android.media.audiofx.DynamicsProcessing
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.android.myapplication.criminialintent_refactored.database.CrimeDao
import com.android.myapplication.criminialintent_refactored.database.CrimeEntity
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor

class RepositoryTest {
    private val crimeEntity = CrimeEntity()
    private val crimeModel = CrimeModel()
    private lateinit var SUT: Repository
    private lateinit var daoMock: CrimeDao
    private lateinit var dataTransformerMock: DataTransformer


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        daoMock = mock()
        dataTransformerMock = mock()
        SUT = Repository(daoMock, dataTransformerMock)
    }

    @Test
    //unitOfWork_stateUnderTest_expectedBehavior
    fun save_shouldCallDb() = runBlockingTest {
        doReturn(crimeEntity).whenever(dataTransformerMock).transformToEntity(any())
        val job = launch {
            SUT.save(crimeModel)
            verify(daoMock).addCrime(eq(crimeEntity))
        }
        job.cancel()
    }

    @Test
    fun delete_shouldCallDb() = runBlockingTest {
        doReturn(crimeEntity).whenever(dataTransformerMock).transformToEntity(any())

        val job = launch {
            SUT.delete(crimeModel)
            verify(daoMock).deleteCrime(eq(crimeEntity))
        }
        job.cancel()
    }

    @Test
    fun loadCrime_shouldCallDb() = runBlockingTest {
        val job = launch {

            SUT.loadCrime(crimeEntity.id)

            verify(daoMock).getCrime(eq(crimeEntity.id))
        }
        job.cancel()
    }

    @Test
    fun loadCrime_dbReturnsValue_shouldCallDataTransformer() = runBlocking {
        val job = launch {

            SUT.loadCrime(crimeEntity.id)
            verify(dataTransformerMock).transformToModel(eq(crimeEntity))
        }
        job.cancel()
    }

    @Test
    fun loadCrime_shouldReturnCorrectValue()=runBlocking{
        val job = launch {
            whenever(dataTransformerMock.transformToModel(any())).thenReturn(crimeModel)

           val result =  SUT.loadCrime(crimeEntity.id)

           Assert.assertEquals(crimeModel,result)

        }
        job.cancel()
    }


}




