package com.candroid.candroidrcc026

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide

class NewsAdapter(val a: Activity, val articles: ArrayList<Article>) :
    RecyclerView.Adapter<NewsAdapter.NewsVH>() {

    class NewsVH(v: View) : RecyclerView.ViewHolder(v) {
        val title: TextView = v.findViewById(R.id.news_title)
        val parent: CardView = v.findViewById(R.id.parent)
        val image: ImageView = v.findViewById(R.id.news_image)
    }

    override fun onBindViewHolder(holder: NewsVH, position: Int) {
        holder.title.text = articles[position].title

        val imgLink = articles[position].urlToImage
        Glide
            .with(a)
            .load(imgLink)
            .error(R.drawable.broken_image)
            .into(holder.image)

        val articleLink = articles[position].url
        holder.parent.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, articleLink.toUri())
            a.startActivity(i)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        val view = a.layoutInflater.inflate(R.layout.news_list_item, parent, false)
        return NewsVH(view)
    }

    override fun getItemCount() = articles.size

}