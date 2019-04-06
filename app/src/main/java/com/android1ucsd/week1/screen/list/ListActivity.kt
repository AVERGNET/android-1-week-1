package com.android1ucsd.week1.screen.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android1ucsd.week1.App
import com.android1ucsd.week1.R
import com.android1ucsd.week1.screen.details.DetailsActivity

/**
 * Created by rjaylward on 4/5/19
 */

class ListActivity : AppCompatActivity() {

    companion object {

        const val TITLE_EXTRA = "title_extra"

        @JvmStatic fun createIntent(context: Context, title: String): Intent {
            return Intent(context, ListActivity::class.java).apply {
                putExtra(TITLE_EXTRA, title)
            }
        }
    }

    private val dataSource: ListScreenDataSource = App.dependencies.listScreenDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        title = intent.getStringExtra(TITLE_EXTRA)

        val recyclerView = findViewById<RecyclerView>(R.id.al_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = ListItemAdapter()
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener { colorItem ->
            startActivity(DetailsActivity.createIntent(this, colorItem))
        }

        adapter.setItems(dataSource.getListData())
    }

    class ListItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private var items: List<ListItemObject> = emptyList()
        private var onItemClick: ((ListItemObject) -> Unit)? = null

        fun setItems(listItems: List<ListItemObject>) {
            this.items = listItems
            notifyDataSetChanged()
        }

        fun setOnItemClickListener(clickListener: ((ListItemObject) -> Unit)) {
            onItemClick = clickListener
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return ColorViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_list_item,
                    parent,
                    false
                )
            ).apply {
                setOnClickAtIndex { index ->
                    onClickAtIndex(index)
                }
            }
        }

        private fun onClickAtIndex(index: Int) {
            onItemClick?.invoke(items[index])
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            when(holder) {
                is ColorViewHolder -> holder.bind(items[position])
            }
        }

        override fun getItemViewType(position: Int): Int = 0

        override fun getItemCount(): Int = items.size
    }

    class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.vli_title)
        private val subtitle: TextView = itemView.findViewById(R.id.vli_subtitle)
        private val image: ImageView = itemView.findViewById(R.id.vli_image_view)

        private var onClickAtIndex: ((Int) -> Unit)? = null

        init {
            itemView.setOnClickListener {
                onClickAtIndex?.invoke(adapterPosition)
            }
        }

        fun bind(item: ListItemObject) {
            title.text = item.title
            subtitle.text = item.subtitle
            image.setBackgroundColor(item.colorInt)
        }

        fun setOnClickAtIndex(onClickAtIndex: (Int) -> Unit) {
            this.onClickAtIndex = onClickAtIndex
        }

    }

}