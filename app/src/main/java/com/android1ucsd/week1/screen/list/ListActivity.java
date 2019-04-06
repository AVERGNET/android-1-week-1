package com.android1ucsd.week1.screen.list;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.android1ucsd.week1.App;

import java.util.List;

/**
 * Created by rjaylward on 4/6/19
 */
public class ListActivity extends AppCompatActivity {

    public static final String TITLE_EXTRA = "title_extra";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<ListItemObject> listItems = App.getDependencies().getListScreenDataSource().getListData();

        //TODO add layout with recycler view, pass the listItems to the adapter and display them
    }

}