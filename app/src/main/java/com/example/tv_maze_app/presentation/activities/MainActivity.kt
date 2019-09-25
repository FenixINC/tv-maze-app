package com.example.tv_maze_app.presentation.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.tv_maze_app.R
import com.example.tv_maze_app.databinding.ActivityMainBinding
import com.example.tv_maze_app.presentation.fragments.TvShowListFragment
import com.google.android.material.bottomnavigation.LabelVisibilityMode

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(mBinding.toolbar as Toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        supportFragmentManager.addOnBackStackChangedListener {
            val isBackStack = supportFragmentManager.backStackEntryCount != 0
            lockDrawer(isBackStack)
            actionBar?.setHomeAsUpIndicator(if (isBackStack) R.drawable.ic_arrow_white else R.drawable.ic_menu)
        }

        changeContainerFragment(TvShowListFragment.newInstance())

        mBinding.bottomNavigation.selectedItemId = R.id.bottom_nav_tv_show
        mBinding.bottomNavigation.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        mBinding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_tv_show -> {
                    changeContainerFragment(TvShowListFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true

                }
                R.id.bottom_nav_search -> {
//                    changeContainerFragment(SearchFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bottom_nav_favorites -> {
//                    changeContainerFragment(FavoritesFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                mBinding.drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (!closeDrawer()) {
            super.onBackPressed()
        }
    }

    private fun lockDrawer(isLock: Boolean) {
        mBinding.drawerLayout.setDrawerLockMode(if (isLock) DrawerLayout.LOCK_MODE_LOCKED_CLOSED else DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    private fun closeDrawer(): Boolean {
        var result = false
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START)
            result = true
        }
        return result
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

    private fun changeContainerFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }
}