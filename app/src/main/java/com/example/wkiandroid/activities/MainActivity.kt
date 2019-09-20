package com.example.wkiandroid.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wkiandroid.R
import com.example.wkiandroid.fragments.ExploreFragment
import com.example.wkiandroid.fragments.FavoritesFragment
import com.example.wkiandroid.fragments.HistoryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val exploreFragment :ExploreFragment
    private val historyFragment :HistoryFragment
    private val favoritesFragment : FavoritesFragment

    init {
        exploreFragment = ExploreFragment()
        historyFragment = HistoryFragment()
        favoritesFragment = FavoritesFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, exploreFragment)
        transaction.commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)

        when (item.itemId) {
            R.id.navigation_explore -> {
                transaction.replace(R.id.fragment_container, exploreFragment)
            }
            R.id.navigation_favorites -> {
                transaction.replace(R.id.fragment_container, favoritesFragment)
            }
            R.id.navigation_history -> {
                transaction.replace(R.id.fragment_container, historyFragment)
            }
        }
        transaction.commit()
        true
    }

}
