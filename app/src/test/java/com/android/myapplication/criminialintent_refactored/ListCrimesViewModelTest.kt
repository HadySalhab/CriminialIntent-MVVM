package com.android.myapplication.criminialintent_refactored

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ListCrimesViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repoMock:Repository
    private lateinit var SUT:ListCrimesViewModel

    @Before
    fun setUp() {
        repoMock = mock()
        SUT = ListCrimesViewModel(repoMock)
    }

    @Test
    fun navigateToEdit_shouldTriggerNavigateEvent(){
        val mockObserver = mock<Observer<Boolean>>()
        SUT.navigate.observeForever(mockObserver)

        SUT.navigateToEdit()

        verify(mockObserver).onChanged(eq(true))
    }

    @Test
    fun navigationEvent_shouldHaveFalseValueByDefault(){
        val mockObserver = mock<Observer<Boolean>>()
        SUT.navigate.observeForever(mockObserver)


        verify(mockObserver).onChanged(eq(false))
    }

    @Test
    fun resetNavigation_shouldResetNavigationEvent(){
        val mockObserver = mock<Observer<Boolean>>()
        SUT.navigate.observeForever(mockObserver)

        SUT.resetNavigation()

        //2 times,one as default and one when reset is called
        verify(mockObserver, times(2)).onChanged(eq(false))
    }

}