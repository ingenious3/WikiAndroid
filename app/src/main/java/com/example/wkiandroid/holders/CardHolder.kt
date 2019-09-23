package com.example.wkiandroid.holders


import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wkiandroid.R
import com.example.wkiandroid.activities.ArticleDetailActivity
import com.example.wkiandroid.models.WikiPage
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val articleImageView: ImageView = itemView.findViewById<ImageView>(R.id.article_image)
    private val titleTextView: TextView = itemView.findViewById<TextView>(R.id.article_title)

    private var currentPage : WikiPage? = null

    init {
        itemView.setOnClickListener { view: View? ->
            var detailPageIntent = Intent(itemView.context, ArticleDetailActivity::class.java)
            var pageJson = Gson().toJson(currentPage)
            detailPageIntent.putExtra("page" , pageJson)
            itemView.context.startActivity(detailPageIntent)
        }
    }

    fun updateWithPage(page : WikiPage) {
        currentPage = page

        titleTextView.text = page.title
        if(page.thumbnail != null) {
            Picasso.with(itemView.context).load(page.thumbnail!!.source).into(articleImageView)
        }
    }

}