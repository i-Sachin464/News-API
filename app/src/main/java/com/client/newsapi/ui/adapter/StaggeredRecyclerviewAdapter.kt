package com.client.newsapi.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.client.newsapi.R
import com.client.newsapi.model.News

/**
 * Created by i-Sachin464 on 16/04/2019.
 */
class StaggeredRecyclerviewAdapter(context: Context, news: List<News>) :
    RecyclerView.Adapter<StaggeredRecyclerviewAdapter.ViewHolder>() {
    private var mNews = ArrayList<News>()
    private val mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: called.")
        holder.title.text = mNews[position].title
        holder.description.text = mNews[position].description
    }

    override fun getItemCount(): Int {
        return mNews.size
    }

    fun getList(): List<News> {
        return this.mNews
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var description: TextView = itemView.findViewById(R.id.description)
    }

    companion object {
        private const val TAG = "StaggeredRecyclerViewAd"
    }

    init {
        mNews.addAll(ArrayList(news))
        mContext = context
    }
}