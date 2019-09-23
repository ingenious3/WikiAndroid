package com.example.wkiandroid.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.wkiandroid.R
import com.example.wkiandroid.WikiApplication
import com.example.wkiandroid.managers.WikiManager
import com.example.wkiandroid.models.WikiPage
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_article_detail.*
import org.jetbrains.anko.toast

class ArticleDetailActivity : AppCompatActivity() {

    private var currentPage:WikiPage ? = null
    private var wikiManager : WikiManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        wikiManager = (applicationContext as WikiApplication).wikiManager
        setSupportActionBar(toolbar as Toolbar?)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val wikiPageJson =  intent.getStringExtra("page")
        currentPage = Gson().fromJson<WikiPage>(wikiPageJson, WikiPage::class.java)

        toolbar?.title = currentPage?.title
        article_detail_webview?.webViewClient = object:WebViewClient() {
            override fun shouldOverrideUrlLoading( view: WebView?, request: WebResourceRequest? ): Boolean {
                return true
            }
        }
        
        article_detail_webview.loadUrl(currentPage!!.fullurl)
        wikiManager?.addHistory(currentPage!!)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu);
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home){
            finish()
        } else if (item!!.itemId == R.id.action_favorite){
            try {
                // determine if article is already a favorite or not
                if(wikiManager!!.getIsFavorite(currentPage!!.pageid!!)){
                    wikiManager!!.removeFavorite(currentPage!!.pageid!!)
                    toast("Article removed from favorites")
                }
                else{
                    wikiManager!!.addFavorite(currentPage!!)
                    toast("Article added to favorites")
                }
            }
            catch (ex: Exception){
                toast("Unable to update this article.")
            }
        }
        return true;
    }

}