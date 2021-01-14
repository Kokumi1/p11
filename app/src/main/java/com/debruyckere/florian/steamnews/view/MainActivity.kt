package com.debruyckere.florian.steamnews.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.debruyckere.florian.steamnews.R
import com.debruyckere.florian.steamnews.model.generatedclass.Newsitem
import com.debruyckere.florian.steamnews.viewmodel.MainViewModel

private lateinit var mMainViewModel: MainViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data: MutableList<Newsitem> = mutableListOf()
        mMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //RecyclerView
        val rv: RecyclerView = findViewById(R.id.main_recycler)
        rv.layoutManager = LinearLayoutManager(baseContext)
        rv.adapter = NewsAdapter(data, this)


        //ViewModel update
        mMainViewModel.getNews(this, this).observe(this) { list: List<Newsitem> ->
            run {
                data.clear()
                data.addAll(list.sortedByDescending { it.date })
                rv.adapter!!.notifyDataSetChanged()

            }
        }

        //Toolbar
        setSupportActionBar(findViewById(R.id.main_toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =

        when (item.itemId) {

            R.id.toolbar_login -> {
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
                true
            }

            R.id.toolbar_config -> {
                val configIntent = Intent(this, ConfigActivity::class.java)
                startActivity(configIntent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

}

class NewsAdapter(private val pData: List<Newsitem>, private val pContext: Context) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.news_cell, parent, false)
        return NewsViewHolder(view, pContext)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.display(pData[position])
    }

    override fun getItemCount(): Int {
        return pData.size
    }


    class NewsViewHolder(pView: View, private val pContext: Context) :
        RecyclerView.ViewHolder(pView) {

        private val newsGameNews: TextView = pView.findViewById(R.id.main_game)
        private val newsTitleNews: TextView = pView.findViewById(R.id.main_title)
        private val newsLayout: LinearLayout = pView.findViewById(R.id.main_layout)

        fun display(pNews: Newsitem) {
            Log.d("AdapterHolder", "pNews: " + pNews.title+" date "+pNews.date)
            newsGameNews.text = pNews.feedlabel
            newsTitleNews.text = pNews.title

            newsLayout.setOnClickListener {
                val tabActivity = Intent(pContext, TabActivity::class.java)
                 tabActivity.putExtra("newsUrl",pNews.url)
                tabActivity.putExtra("newsId",pNews.gid)
                pContext.startActivity(tabActivity)
            }
        }
    }
}

