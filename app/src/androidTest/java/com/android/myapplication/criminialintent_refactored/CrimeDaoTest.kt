package com.android.myapplication.criminialintent_refactored

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.android.myapplication.criminialintent_refactored.database.CrimeDao
import com.android.myapplication.criminialintent_refactored.database.CrimeDb
import com.android.myapplication.criminialintent_refactored.database.CrimeEntity
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.junit.MockitoJUnitRunner

@RunWith(AndroidJUnit4::class)
class CrimeDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var crimeDb: CrimeDb
    private lateinit var SUT: CrimeDao

    @Before
    fun setUp() {
        crimeDb = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            CrimeDb::class.java
        ).build()
        SUT = crimeDb.crimeDao()
    }

    @After
    fun closeDb() {
        crimeDb.close()
    }

    @Test
    fun allCrimes_emptyDB_shouldReturnEmptyList(){
        val testObserver:Observer<List<CrimeEntity>> = mock()

        SUT.allCrimes().observeForever(testObserver)

        verify(testObserver).onChanged(emptyList())
    }

    @Test
    fun addCrime_shouldSaveCrimeToDb()= runBlocking{
        val crime = CrimeEntity()
        val testObserver:Observer<List<CrimeEntity>> = mock()


        SUT.addCrime(crime)

        SUT.allCrimes().observeForever(testObserver)
        val listClass = ArrayList::class.java as Class<ArrayList<CrimeEntity>>
        val argumentCaptor = ArgumentCaptor.forClass(listClass)
        verify(testObserver).onChanged(argumentCaptor.capture())
        Assert.assertTrue(argumentCaptor.allValues.size>0)

    }

    @Test
    fun allCrimes_dbNotEmpty_shouldReturnCorrectData()=runBlocking{
        val crime1 = CrimeEntity()
        val crime2 = CrimeEntity()
        SUT.addCrime(crime1)
        SUT.addCrime(crime2)
        val testObserver:Observer<List<CrimeEntity>> = mock()

        SUT.allCrimes().observeForever(testObserver)

        val listClass = ArrayList::class.java as Class<ArrayList<CrimeEntity>>
        val argumentCaptor = ArgumentCaptor.forClass(listClass)
        verify(testObserver).onChanged(argumentCaptor.capture())
        val capturedArgument = argumentCaptor.value
        Assert.assertTrue(capturedArgument.containsAll(listOf(crime1,crime2)))

    }

    @Test
    fun getCrime_shouldReturnCorrectData()=runBlocking{
        val crime1 = CrimeEntity()
        SUT.addCrime(crime1)

        val result = SUT.getCrime(crime1.id)

        Assert.assertEquals(crime1,result)
    }


}