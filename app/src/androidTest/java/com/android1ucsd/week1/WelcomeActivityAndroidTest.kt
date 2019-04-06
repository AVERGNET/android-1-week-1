package com.android1ucsd.week1

import android.content.ComponentName
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.rule.ActivityTestRule
import com.android1ucsd.week1.screen.list.ListActivity
import com.android1ucsd.week1.screen.welcome.WelcomeActivity


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class WelcomeActivityAndroidTest {

    @get:Rule
    var activityRule: IntentsTestRule<WelcomeActivity> = IntentsTestRule(WelcomeActivity::class.java)

    private val testString = "Testing"

    @Test
    fun testAssertHelloText() {
        Espresso.onView(ViewMatchers.withHint(R.string.welcome_page_hint))
            .withFailureHandler { _, _ -> fail("missing a view with the hint set to R.string.hint") }
            .check(ViewAssertions.matches(ViewMatchers.isAssignableFrom(EditText::class.java))) // makes sure the view is an EditText
            .perform(ViewActions.typeText(testString), ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withText(R.string.welcome_page_button_text))
            .withFailureHandler { _, _ -> fail("missing a button with text set to R.string.submit_button_text") }
            .check(ViewAssertions.matches(ViewMatchers.isAssignableFrom(Button::class.java))) // make sure the view is a Button
            .perform(ViewActions.click())

        // here we check that the string that was typed in the edit text was passed to the next activity
        Intents.intended(IntentMatchers.hasExtra(ListActivity.TITLE_EXTRA, testString))
        // here we make sure the intent is going to the right place, the ListActivity
        Intents.intended(IntentMatchers.hasComponent(ComponentName(activityRule.activity, ListActivity::class.java)))
    }

}