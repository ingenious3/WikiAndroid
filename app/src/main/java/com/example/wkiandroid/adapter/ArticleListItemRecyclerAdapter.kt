package com.example.wkiandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wkiandroid.R
import com.example.wkiandroid.holders.ListItemHolder

class ArticleListItemRecyclerAdapter() : RecyclerView.Adapter<ListItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        var listItem = LayoutInflater.from(parent?.context).inflate(R.layout.article_list_item, parent, false)
        return ListItemHolder(listItem)
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 15
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}