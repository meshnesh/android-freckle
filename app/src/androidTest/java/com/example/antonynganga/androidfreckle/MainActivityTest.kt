package com.example.antonynganga.androidfreckle

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import com.example.antonynganga.androidfreckle.view.DashBoard.MainActivity
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @Rule
    @JvmField
    var rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testLoadsHelloWorld() {
        onView(withId(R.id.original_text)).check(matches(isDisplayed()))
    }
}