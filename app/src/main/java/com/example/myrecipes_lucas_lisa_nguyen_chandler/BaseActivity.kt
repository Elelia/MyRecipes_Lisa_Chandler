package com.example.myrecipes_lucas_lisa_nguyen_chandler

import android.content.Intent
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var drawerLayout: DrawerLayout

    override fun setContentView(layoutResID: Int) {
        val drawer = layoutInflater.inflate(R.layout.activity_base, null) as DrawerLayout
        val content = drawer.findViewById<FrameLayout>(R.id.content_frame)
        layoutInflater.inflate(layoutResID, content, true)
        super.setContentView(drawer)

        drawerLayout = drawer
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navView = findViewById<NavigationView>(R.id.navigation_view)
        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_categories -> {
                    if (this !is CategoryActivity) {
                        startActivity(Intent(this, CategoryActivity::class.java))
                    }
                    true
                }
                R.id.nav_favorites -> {
                    if (this !is FavoriteActivity) {
                        startActivity(Intent(this, FavoriteActivity::class.java))
                    }
                    true
                }
                else -> false
            }.also {
                drawerLayout.closeDrawers()
            }
        }
    }
}
