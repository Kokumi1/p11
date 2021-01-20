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
import com.google.android.material.tabs.TabLayout

class TabActivity : AppCompatActivity() {

    private lateinit var viewPager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)

        setSupportActionBar(findViewById(R.id.tab_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extraId = intent.getStringExtra("newsId")
        val extraUrl = intent.getStringExtra("newsUrl")
        val bundle = Bundle()
        bundle.putString("newsId",extraId)
        bundle.putString("newsUrl",extraUrl)

        viewPager = findViewById(R.id.tab_viewpager)
        val tabs = findViewById<TabLayout>(R.id.tab_tablayout)
        val adapter = TabAdapter(supportFragmentManager,2,bundle)
        tabs.setupWithViewPager(viewPager)

        viewPager.adapter = adapter
    }

   /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean =

       if(item.itemId == android.R.id.home){
                val homeIntent = Intent(this, MainActivity::class.java)
                startActivity(homeIntent)
                true
       }
        else super.onOptionsItemSelected(item)



    class TabAdapter (fm: FragmentManager, var totalTabs: Int, private val pBundle: Bundle): FragmentPagerAdapter(fm, totalTabs){

        override fun getCount(): Int {
            return totalTabs
        }

        override fun getPageTitle(position: Int): CharSequence {
            when(position){
                0 -> run{   //News Tab
                    return "News" }

                1 -> run{   //Comment Tab
                    return "Comment" }

                else -> run{
                    return "News"}
            }
        }

        override fun getItem(position: Int): Fragment {
            when(position){
                0 -> run{
                    val fragment = NewsFragment()
                    fragment.arguments = pBundle
                    return fragment }

                1 -> run{
                    val fragment = CommentFragment()
                    fragment.arguments = pBundle
                    return fragment  }

                else -> run{
                    val fragment = NewsFragment()
                    fragment.arguments = pBundle
                    return fragment}
            }
        }
    }
}