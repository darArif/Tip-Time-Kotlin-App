package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers.containsString

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule



/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CalculatorTests {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun calculate_15_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text_id))
            .perform(typeText("60.00"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.tip_calculator_button_id))
            .perform(click())

        onView(withId(R.id.tip_result_id))
            .check(matches(withText(containsString("$9.00"))))
    }
    @Test
    fun calculate_18_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text_id))
            .perform(typeText("60.00"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.radio_button_18_id))
            .perform(click())

        onView(withId(R.id.round_up_switch_id))
            .perform(click())

        onView(withId(R.id.tip_calculator_button_id))
            .perform(click())

        onView(withId(R.id.tip_result_id))
            .check(matches(withText(containsString("$10.80"))))
    }
}