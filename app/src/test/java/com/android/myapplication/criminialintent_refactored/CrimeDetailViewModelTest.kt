package com.android.myapplication.criminialintent_refactored

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.eq
import org.mockito.Mockito.mock
import java.util.*

class CrimeDetailViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val CRIME_ID = UUID.randomUUID().toString()
    private lateinit var repoMock:Repository
    private lateinit var SUT:CrimeDetailViewModel


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
}