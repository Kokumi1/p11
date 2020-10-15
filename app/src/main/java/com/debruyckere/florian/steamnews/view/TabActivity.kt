package com.debruyckere.florian.steamnews.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        viewPager = findViewById(R.id.tab_viewpager)
    }


    class tabAdapter (fm: FragmentManager, var totalTabs: Int): FragmentPagerAdapter(fm, totalTabs){

        override fun getCount(): Int {
            return totalTabs
        }

        override fun getItem(position: Int): Fragment {
            return when(position){
                0 -> NewsFragment()

                1 -> CommentFragment()

                else -> NewsFragment()
            }
        }
    }
}