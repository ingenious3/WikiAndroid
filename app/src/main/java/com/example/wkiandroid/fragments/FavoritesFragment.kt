package com.example.wkiandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wkiandroid.R
import com.example.wkiandroid.adapter.ArticleCardRecyclerAdapter


class FavoritesFragment : Fragment() {

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        var favoritesRecyler: RecyclerView? = null

        val view = inflater!!.inflate(R.layout.fragment_favorites, container, false)

        favoritesRecyler = view.findViewById<RecyclerView>(R.id.favorites_article_recyclerview);
        favoritesRecyler!!.layoutManager = LinearLayoutManager(context)
        favoritesRecyler!!.adapter = ArticleCardRecyclerAdapter()

        return view
    }


}
