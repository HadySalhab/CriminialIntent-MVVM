package com.android.myapplication.criminialintent_refactored

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest
{

    lateinit var testScenario: ActivityScenario<MainActivity>

    @Test
    fun pressingOnAboutMenuItem_shouldNavigateToAboutFragment(){
        val testScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.aboutFragment)).perform(click())

        onView(withId(R.id.about)).check(matches(isDisplayed()))
    }

    @Test
    fun onBackPressed_AboutFragmentIsDisplayed_ShouldNavigateToListFragment(){
        val testScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.aboutFragment)).perform(click())
        pressBack()

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }
}