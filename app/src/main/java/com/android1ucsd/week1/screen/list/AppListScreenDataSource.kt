package com.android1ucsd.week1.screen.list

import android.graphics.Color

/**
 * This class will provide the data for us to display on the ListActivity. In a real app this data probably would not
 * be static and unchanging which can make it really hard to write tests that rely on the real data. That is why we make
 * a interface like [ListScreenDataSource] which describes the type of data that our views expect. We can then have a
 * version in the app that gets the data from a web service or a database, but in our tests we can create a static data
 * source.
 */

class AppListScreenDataSource : ListScreenDataSource {

    override fun getListData(): List<ListItemObject> {
        return listOf(
            ListItemObject("Red", "1", Color.RED),
            ListItemObject("Blue", "2", Color.BLUE),
            ListItemObject("Green", "3", Color.GREEN),
            ListItemObject("Pink", "4", Color.CYAN),
            ListItemObject("Red", "5", Color.YELLOW),
            ListItemObject("Red", "6", Color.MAGENTA),
            ListItemObject("Red", "1", Color.RED),
            ListItemObject("Blue", "2", Color.BLUE),
            ListItemObject("Green", "3", Color.GREEN),
            ListItemObject("Pink", "4", Color.CYAN),
            ListItemObject("Red", "5", Color.YELLOW),
            ListItemObject("Red", "6", Color.MAGENTA)
        )
    }

}