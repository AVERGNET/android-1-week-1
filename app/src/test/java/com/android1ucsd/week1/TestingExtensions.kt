package com.android1ucsd.week1

import androidx.test.espresso.ViewInteraction
import org.junit.Assert

/**
 * These are extension functions, which is a cool feature of Kotlin that allows us to create functions that look like
 * methods on an object but are really just static helper methods.
 */

/**
 * Helps log a more readable error message to the console when a test cannot find a view
 */
fun ViewInteraction.withFailureMessage(message: String): ViewInteraction {
    return this.withFailureHandler { _, _ -> Assert.fail(message) }
}