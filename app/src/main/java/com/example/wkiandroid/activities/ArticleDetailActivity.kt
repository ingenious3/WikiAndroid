package com.example.wkiandroid.activities

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.wkiandroid.R
import com.example.wkiandroid.models.WikiPage
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetailActivity : AppCompatActivity() {

    private var currentPage:WikiPage ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        setSupportActionBar(toolbar as Toolbar?);
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);

        val wikiPageJson =  intent.getStringExtra("page")
        currentPage = Gson().fromJson<WikiPage>(wikiPageJson, WikiPage::class.java)

        article_detail_webview?.webViewClient = object:WebViewClient() {
            override fun shouldOverrideUrlLoading( view: WebView?, request: WebResourceRequest? ): Boolean {
                return true
            }
        }
        
        article_detail_webview.loadUrl(currentPage!!.fullurl)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home){
            finish()
        }
        return true;
    }

}