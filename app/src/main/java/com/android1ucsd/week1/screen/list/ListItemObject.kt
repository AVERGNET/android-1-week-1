package com.android1ucsd.week1.screen.list

import android.os.Parcelable
import androidx.annotation.ColorInt
import kotlinx.android.parcel.Parcelize

/**
 * Created by rjaylward on 4/5/19
 */

@Parcelize
data class ListItemObject(
    val title: String,
    val subtitle: String,
    @get:ColorInt val colorInt: Int
) : Parcelable