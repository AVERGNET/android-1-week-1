package com.android1ucsd.week1.screen.list;

import android.graphics.Color;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * This class will provide the data for us to display on the ListActivity. In a real app this data probably would not
 * be static and unchanging which can make it really hard to write tests that rely on the real data. That is why we make
 * a interface like [ListScreenDataSource] which describes the type of data that our views expect. We can then have a
 * version in the app that gets the data from a web service or a database, but in our tests we can create a static data
 * source.
 */
public class AppListScreenDataSource implements ListScreenDataSource {

    // feel free to make the list item title, subtitle and colors anything you want.

    private List<ListItemObject> items = Arrays.asList(
            new ListItemObject("RED", "Item 1", Color.RED),
            new ListItemObject("BLUE", "Item 2", Color.BLUE),
            new ListItemObject("GREEN", "Item 3", Color.GREEN),
            new ListItemObject("CYAN", "Item 4", Color.CYAN),
            new ListItemObject("YELLOW", "Item 5", Color.YELLOW),
            new ListItemObject("MAGENTA", "Item 6", Color.MAGENTA),
            new ListItemObject("RED", "Item 7", Color.RED),
            new ListItemObject("BLUE", "Item 8", Color.BLUE),
            new ListItemObject("GREEN", "Item 9", Color.GREEN),
            new ListItemObject("CYAN", "Item 10", Color.CYAN),
            new ListItemObject("YELLOW", "Item 11", Color.YELLOW),
            new ListItemObject("MAGENTA", "Item 12", Color.MAGENTA),
            new ListItemObject("RED", "Item 13", Color.RED),
            new ListItemObject("BLUE", "Item 14", Color.BLUE),
            new ListItemObject("GREEN", "Item 15", Color.GREEN),
            new ListItemObject("CYAN", "Item 16", Color.CYAN),
            new ListItemObject("YELLOW", "Item 17", Color.YELLOW),
            new ListItemObject("MAGENTA", "Item 18", Color.MAGENTA)
    );

    @NotNull
    @Override
    public List<ListItemObject> getListData() {
        return items;
    }

}
