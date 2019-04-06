package com.android1ucsd.week1.screen.list

/**
 * This is an interface that describes the type of data that the [ListActivity] can display. We create an interface so
 * we can have test version and real version of the data.
 */
interface ListScreenDataSource {
    fun getListData(): List<ListItemObject>
}