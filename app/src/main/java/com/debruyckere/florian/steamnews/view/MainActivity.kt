package com.debruyckere.florian.steamnews.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.debruyckere.florian.steamnews.R
import com.debruyckere.florian.steamnews.model.News

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data : ArrayList<News> = ArrayList()

        val rv : RecyclerView = findViewById(R.id.main_recycler)
        rv.layoutManager = LinearLayoutManager(baseContext)
        rv.adapter = NewsAdapter(data,this)

    }


    class NewsAdapter(private val pData : List<News>,private val pContext: Context) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
            val inflater : LayoutInflater = LayoutInflater.from(parent.context)
            val view : View = inflater.inflate( R.layout.news_cell, parent, false)
            return NewsViewHolder(view,pContext)
        }

        override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            holder.display(pData[position])
        }

        override fun getItemCount(): Int {
            return pData.size
        }


        class NewsViewHolder(pView : View, private val pContext: Context): RecyclerView.ViewHolder(pView){

            private val newsGameNews : TextView = pView.findViewById(R.id.main_game)
            private val newsTitleNews : TextView = pView.findViewById(R.id.main_title)
            private val newsLayout : LinearLayout = pView.findViewById(R.id.main_layout)

            fun display(pNews: News){
                newsGameNews.text = pNews.gameName
                newsTitleNews.text = pNews.title

                newsLayout.setOnClickListener{
                    val tabActivity = Intent(pContext, TabActivity::class.java)
                    // tabActivity.putExtra("news",pNews)
                    pContext.startActivity(tabActivity)
                }
            }
        }
    }
}