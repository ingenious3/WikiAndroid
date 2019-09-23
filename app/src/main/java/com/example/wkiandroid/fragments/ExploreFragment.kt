package com.example.wkiandroid.fragments


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.wkiandroid.R
import com.example.wkiandroid.activities.SearchActivity
import com.example.wkiandroid.adapter.ArticleCardRecyclerAdapter
import com.example.wkiandroid.provider.ArticleDataProvider

class ExploreFragment : Fragment() {


    private val articleProvider : ArticleDataProvider = ArticleDataProvider()
    var searchCardView: CardView? = null
    var exploreRecycler: RecyclerView? = null
    var adapter : ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()
    var refresher : SwipeRefreshLayout? = null

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        var view =  inflater.inflate(R.layout.fragment_explore, container, false)

        searchCardView = view.findViewById<CardView>(R.id.search_card_view)
        exploreRecycler = view.findViewById<RecyclerView>(R.id.explore_article_recyclerview)
        refresher = view.findViewById<SwipeRefreshLayout>(R.id.refresher)
        searchCardView!!.setOnClickListener{
            val searchIntent = Intent(context, SearchActivity::class.java)
            context?.startActivity(searchIntent)
        }

        exploreRecycler!!.layoutManager = LinearLayoutManager(context)
        exploreRecycler!!.adapter = adapter

        refresher?.setOnRefreshListener {
            getRandomArticles()
        }
        getRandomArticles()
        return view
    }

    private fun getRandomArticles() {
        refresher?.isRefreshing = true
        try {
            articleProvider.getRandom(15, { wikiresult ->
                adapter.currentResults.clear()
                adapter.currentResults.addAll(wikiresult.query!!.pages)
                activity?.runOnUiThread {
                    adapter.notifyDataSetChanged()
                    refresher?.isRefreshing = false
                    print("here")
                }
            })
        } catch (ex: Exception) {
            val builder = AlertDialog.Builder(activity)
            builder.setMessage(ex.message).setTitle("Oops!")
            val dialog = builder.create()
            dialog.show()
        }
    }
}
