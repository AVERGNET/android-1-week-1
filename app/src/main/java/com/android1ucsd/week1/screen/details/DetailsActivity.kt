package com.android1ucsd.week1.screen.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android1ucsd.week1.R
import com.android1ucsd.week1.screen.list.ListItemObject

/**
 * Created by rjaylward on 4/5/19
 */

class DetailsActivity : AppCompatActivity() {

    private lateinit var adSubtitle: TextView
    private lateinit var adTitle: TextView
    private lateinit var adImageView: ImageView

    companion object {

        const val ITEM_EXTRA = "item_extra"

        fun createIntent(context: Context, item: ListItemObject): Intent {
            return Intent(context, DetailsActivity::class.java).apply {
                putExtra(ITEM_EXTRA, item)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val item: ListItemObject = intent.getParcelableExtra(ITEM_EXTRA)

        adTitle = findViewById<TextView>(R.id.ad_title)
        adSubtitle = findViewById<TextView>(R.id.ad_subtitle)
        adImageView = findViewById<ImageView>(R.id.ad_image_view)

        adTitle.text = item.title
        adSubtitle.text = item.subtitle
        adImageView.setBackgroundColor(item.colorInt)
    }
}