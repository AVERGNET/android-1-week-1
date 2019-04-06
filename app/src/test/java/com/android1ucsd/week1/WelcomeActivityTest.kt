package com.android1ucsd.week1

import android.content.ComponentName
import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android1ucsd.week1.screen.list.ListActivity
import com.android1ucsd.week1.screen.welcome.WelcomeActivity
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class WelcomeActivityTest {

    @get:Rule
    var activityRule: IntentsTestRule<WelcomeActivity> = IntentsTestRule(WelcomeActivity::class.java)

    private val testString = "Testing"

    @Test
    fun testAssertHelloText() {
        onView(withHint(R.string.welcome_page_hint))
            .withFailureHandler { _, _ -> fail("missing a view with the hint set to R.string.hint") }
            .check(matches(isAssignableFrom(EditText::class.java)))
            .perform(ViewActions.typeText(testString), ViewActions.closeSoftKeyboard())

        onView(withText(R.string.welcome_page_button_text))
            .withFailureHandler { _, _ -> fail("missing a button with text set to R.string.submit_button_text")}
            .perform(ViewActions.click())

        // here we check that the string that was typed in the edit text was passed to the next activity
        intended(IntentMatchers.hasExtra(ListActivity.TITLE_EXTRA, testString))
        // here we make sure the intent is going to the right place, the ListActivity
        intended(IntentMatchers.hasComponent(ComponentName(activityRule.activity, ListActivity::class.java)))
    }

}