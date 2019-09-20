package com.example.wkiandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wkiandroid.R
import com.example.wkiandroid.holders.CardHolder

class ArticleCardRecyclerAdapter() : RecyclerView.Adapter<CardHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        var cardItem = LayoutInflater.from(parent?.context).inflate(R.layout.article_card_item, parent, false)
        return CardHolder(cardItem)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 15

    }
}