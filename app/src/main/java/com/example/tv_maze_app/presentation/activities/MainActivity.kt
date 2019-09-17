package com.example.tv_maze_app.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.tv_maze_app.R
import com.example.tv_maze_app.presentation.fragments.TvShowListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
//        toolbar.setNavigationOnClickListener { view -> onBackPressed() }

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                resetToolbar()
            }
        }

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, TvShowListFragment.newInstance())
                .commit()
    }

    private fun resetBackStack() {
        val fm = supportFragmentManager
        if (fm.backStackEntryCount > 0) {
            var i = 0
            val count = fm.backStackEntryCount
            while (i < count) {
                fm.popBackStack()
                i++
            }
            resetToolbar()
        }
    }

    private fun resetToolbar() {
        val ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(false)
    }
}