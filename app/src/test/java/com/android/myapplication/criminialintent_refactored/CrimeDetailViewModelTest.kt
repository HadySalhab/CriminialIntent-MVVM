package com.android.myapplication.criminialintent_refactored

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import java.util.*

class CrimeDetailViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val CRIME_ID = UUID.randomUUID().toString()
    private lateinit var repoMock:Repository
    private lateinit var SUT:CrimeDetailViewModel


    @Before
    fun setUp() {
        repoMock = mock()
        SUT = CrimeDetailViewModel(repoMock,CRIME_ID)
    }

    @Test
    fun initializeCrime_idNotNull_shouldCallRepo()= runBlocking {
        repoMock = mock()
        SUT = CrimeDetailViewModel(repoMock,CRIME_ID)



        val job = launch {
            SUT.initializeCrime()
            verify(repoMock).loadCrime(ArgumentMatchers.eq(CRIME_ID))
        }
        job.cancel()

    }


    @Test
    fun saveCrime_titleAndSuspectAreEmpty_shouldShowErrorMessage(){
        SUT._suspect.value = ""
        SUT._titleEditText.value = ""
        val mockObserer = mock<Observer<Boolean>>()
        SUT.warningMessage.observeForever(mockObserer)

        SUT.saveCrime()

        verify(mockObserer).onChanged(eq(true))
    }
    @Test
    fun saveCrime_titleAndSuspectAreNeitherEmptyNorNull_shouldCallUpdateUI() = runBlocking {
        SUT._suspect.value = "SUSPECT"
        SUT._titleEditText.value = "TITLE"

        val job = launch {
            SUT.saveCrime()

            verify(SUT).updateUI()
        }
        job.cancel()
    }
    @Test
    fun saveCrime_titleAndSuspectAreNeitherEmptyNorNull_shouldCallRepoSave() = runBlocking {
        SUT._suspect.value = "SUSPECT"
        SUT._titleEditText.value = "TITLE"

        val job = launch {
            SUT.saveCrime()

            verify(repoMock).save(ArgumentMatchers.any())
        }
        job.cancel()
    }

    @Test
    fun saveCrime_titleAndSuspectAreNeitherEmptyNorNull_shouldNavigateUp() = runBlocking {
        SUT._suspect.value = "SUSPECT"
        SUT._titleEditText.value = "TITLE"
        val mockObserer = mock<Observer<Boolean>>()
        SUT.navigateUp.observeForever(mockObserer)


        val job = launch {
            SUT.saveCrime()

            verify(mockObserer).onChanged(ArgumentMatchers.eq(true))
        }
        job.cancel()
    }

    @Test
    fun resetNavigateUp_shouldSetValueToFalse()=runBlocking{
        val mockObserer = mock<Observer<Boolean>>()
        SUT.navigateUp.observeForever(mockObserer)


        val job = launch {
            SUT.resetNavigateUp()

            verify(mockObserer, times(2)).onChanged(ArgumentMatchers.eq(true))
        }
        job.cancel()
    }





}