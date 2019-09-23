package com.example.wkiandroid

import android.app.Application
import com.example.wkiandroid.managers.WikiManager
import com.example.wkiandroid.provider.ArticleDataProvider
import com.example.wkiandroid.repositories.ArticleDatabaseOpenHelper
import com.example.wkiandroid.repositories.FavoritesRepository
import com.example.wkiandroid.repositories.HistoryRepository

class WikiApplication: Application() {
    private var dbHelper: ArticleDatabaseOpenHelper? = null
    private var favoritesRepository: FavoritesRepository? = null
    private var historyRepository: HistoryRepository? = null
    private var wikiProvider: ArticleDataProvider? = null
    var wikiManager: WikiManager? = null
        private set

    override fun onCreate() {
        super.onCreate()

        dbHelper = ArticleDatabaseOpenHelper(applicationContext)
        favoritesRepository = FavoritesRepository(dbHelper!!)
        historyRepository = HistoryRepository(dbHelper!!)
        wikiProvider = ArticleDataProvider()
        wikiManager = WikiManager(wikiProvider!!, favoritesRepository!!, historyRepository!!)
    }
}