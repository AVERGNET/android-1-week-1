package com.android1ucsd.week1

import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.rule.ActivityTestRule
import com.android1ucsd.week1.screen.welcome.WelcomeActivity


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class WelcomeActivityAndroidTest {

    @get:Rule
    var activityRule: ActivityTestRule<WelcomeActivity> = ActivityTestRule(WelcomeActivity::class.java)

    @Before
    fun launchActivity() {
    }

    @Test
    fun testAssertHelloText() {
        Espresso.onView(ViewMatchers.withHint("search"))
            .withFailureHandler { _, _ -> fail("missing a view with the hint search") }
            .check(ViewAssertions.matches(ViewMatchers.isAssignableFrom(EditText::class.java)))
            .perform(ViewActions.typeText("query"), ViewActions.closeSoftKeyboard())

//        matchToolbarTitle("welcome").check(matches(isDisplayed()))

        Espresso.onView(ViewMatchers.withText("Submit"))
            .withFailureHandler { _, _ -> fail("missing submit button")}
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.isAssignableFrom(RecyclerView::class.java))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}