package com.android1ucsd.week1

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android1ucsd.week1.screen.details.DetailsActivity
import com.android1ucsd.week1.screen.list.ListActivity
import com.android1ucsd.week1.screen.list.ListItemObject
import com.android1ucsd.week1.screen.list.ListScreenDataSource
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import kotlin.random.Random

/**
 * Created by rjaylward on 4/5/19
 */

@Config(qualifiers = "h640dp")
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class ListActivityTest {

    @get:Rule
    val activityRule: IntentsTestRule<ListActivity> = IntentsTestRule(ListActivity::class.java, true, false)

    // In order for this test to pass the 21st item must be offscreen initially. Since we are running this test on a
    // virtual device with a height of 640dp our list items must be at-least 640/(21 - 1) = 32dp
    private val offscreenTestPosition = 21
    private val testDataSource = TestListScreenDataSource(30)
    private val expectedItemAtOffscreenPosition: ListItemObject = testDataSource.getListData()[offscreenTestPosition]

    private val testTitle = "test title"

    @Before
    fun launchActivity() {
        // we have to set our dependencies before the activity launches so we can make sure the list will be loaded
        // with test data instead of the real data.
        App.init(Dependencies(testDataSource))

        // the activityRule automatically creates an intent to the correct activity so we don't need to pass the class
        // and context to the intent constructor, we can just add our text data.
        val intent = Intent()
        intent.putExtra(ListActivity.TITLE_EXTRA, testTitle)

        activityRule.launchActivity(intent)
    }

    @Test
    fun testCheckIntentAndRecyclerView() {
        // Makes sure what was passed to the activity is shown as the title
        onView(withText(testTitle)).check(matches(withParent(isAssignableFrom(Toolbar::class.java))))

        // Makes sure there is a RecyclerView
        val recyclerView = onView(isAssignableFrom(RecyclerView::class.java))
        recyclerView.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        val hasListItemWithExpectedTitle = hasDescendant(withText(expectedItemAtOffscreenPosition.title))
        val hasListItemWithExpectedSubtitle = hasDescendant(withText(expectedItemAtOffscreenPosition.subtitle))

        // We expect the item at the offscreenTestPosition to be initially offscreen
        // if this is failing, try making your view-holder layout taller
        recyclerView.check(matches(not(hasListItemWithExpectedTitle)))
        recyclerView.check(matches(not(hasListItemWithExpectedSubtitle)))

        // Here we scroll the recycler view to the 21st item
        recyclerView.perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(offscreenTestPosition))

        // after scrolling the item should be visible, we check that it has the title and subtitle we expect
        recyclerView.check(matches(hasListItemWithExpectedSubtitle))
        recyclerView.check(matches(hasListItemWithExpectedSubtitle))

        recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(offscreenTestPosition, click()))
    }

    @Test
    fun testStartDetailsActivity() {
        // performs a click on the item in the recycler view
        onView(isAssignableFrom(RecyclerView::class.java)).perform(RecyclerViewActions
            .actionOnItemAtPosition<RecyclerView.ViewHolder>(offscreenTestPosition, click()))

        // checks to see that the item is correctly passed to the next activity
        Intents.intended(IntentMatchers.hasExtra(DetailsActivity.ITEM_EXTRA, expectedItemAtOffscreenPosition))
    }

    /**
     * A data source that is filled with test data, it generates a random list of items.
     */
    private class TestListScreenDataSource(listSize: Int) : ListScreenDataSource {

        private val list: List<ListItemObject> = listOf(
            *0.until(listSize).map { generateTestItem(it) }.toList().toTypedArray()
        )

        override fun getListData(): List<ListItemObject> {
            return list
        }

        private fun generateTestItem(index: Int): ListItemObject {
            return ListItemObject(
                "Title $index",
                "Subtitle $index",
                Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
            )
        }
    }

}