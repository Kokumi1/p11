package com.debruyckere.florian.steamnews.view

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.debruyckere.florian.steamnews.BuildConfig
import com.debruyckere.florian.steamnews.R
import com.debruyckere.florian.steamnews.model.News
import com.debruyckere.florian.steamnews.services.ApiTalker
import com.debruyckere.florian.steamnews.viewmodel.NewsDownloader

private var mNewsDownloader : NewsDownloader? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNewsDownloader = ViewModelProvider(this).get(NewsDownloader::class.java)

        val data : List<News> = mNewsDownloader!!.getNews().value!!

        //RecyclerView
        val rv : RecyclerView = findViewById(R.id.main_recycler)
        rv.layoutManager = LinearLayoutManager(baseContext)
        rv.adapter = NewsAdapter(data,this)

        //Toolbar
        setSupportActionBar(findViewById(R.id.main_toolbar))

        //ViewModel update
        mNewsDownloader!!.getNews().observe(this, {
            rv.swapAdapter(NewsAdapter(data,this), false)
        })

        val async = Async(this)
        async.execute("")

    }

    class Async(private val pContext: Context) : AsyncTask<String,Void,String?>(){
        val apiTalker = ApiTalker()

        override fun doInBackground(vararg params: String?): String? {
            val steamId =apiTalker.login("dflorian","",pContext)
            Log.d("steamId : ",steamId)
            return null
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun onPostExecute(result: String?) {
            //if(result!= null && result!="") apiTalker.getGames(result, pContext)
            //else Log.d("retrofit GAME","ID Null")
            apiTalker.getGames("76561198358887469",pContext)
            super.onPostExecute(result)
        }
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

            else-> super.onOptionsItemSelected(item)
        }

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
                Log.d("AdapterHolder","pNews: "+ pNews.gameName)

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

