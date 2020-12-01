package com.debruyckere.florian.steamnews.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.debruyckere.florian.steamnews.R
import com.debruyckere.florian.steamnews.view.fragment.CommentFragment
import com.debruyckere.florian.steamnews.view.fragment.NewsFragment

class TabActivity : AppCompatActivity() {

    private var viewPager : ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)

        setSupportActionBar(findViewById(R.id.tab_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extra = intent.getStringExtra("news")
        val bundle = Bundle()
        bundle.putString("news",extra)

        viewPager = findViewById(R.id.tab_viewpager)
        val adapter = TabAdapter(supportFragmentManager,2,bundle)
        viewPager!!.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =

        when (item.itemId){

            R.id.toolbar_login -> {
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
                true
            }

            R.id.toolbar_config-> {
                val configIntent = Intent(this, ConfigActivity::class.java)
                startActivity(configIntent)
                true
            }
            android.R.id.home-> {
            val homeIntent = Intent(this, MainActivity::class.java)
            startActivity(homeIntent)
            true
        }

            else-> super.onOptionsItemSelected(item)
        }


    class TabAdapter (fm: FragmentManager, var totalTabs: Int, private val pBundle: Bundle): FragmentPagerAdapter(fm, totalTabs){

        override fun getCount(): Int {
            return totalTabs
        }

        override fun getItem(position: Int): Fragment {
            when(position){
                0 -> run{
                    val fragment = NewsFragment()
                    fragment.arguments = pBundle
                    return fragment }

                1 -> return CommentFragment()

                else -> run{
                    val fragment = NewsFragment()
                    fragment.arguments = pBundle
                    return fragment}
            }
        }
    }
}