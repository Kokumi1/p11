package com.debruyckere.florian.steamnews.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.debruyckere.florian.steamnews.model.generatedclass.Game
import com.debruyckere.florian.steamnews.model.generatedclass.Newsitem
import com.debruyckere.florian.steamnews.services.ApiTalker
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * Created by Debruyckère Florian on 07/10/2020.
 */
class MainViewModel() : ViewModel() {

    var mContext : Context? = null
    private var mNews : MutableLiveData<List<Newsitem>> = MutableLiveData()

    fun getNews(pCycle: LifecycleOwner, pContext: Context) : LiveData<List<Newsitem>>{
        mContext = pContext
        createNews(pCycle)
        //mNews.postValue(tmp)
        return mNews
    }

    private fun createNews(pCycle : LifecycleOwner){

        val apiTalker = ApiTalker()

        apiTalker.getGames("76561198358887469",mContext!!).observe(pCycle){
            listGame : List<Game> -> kotlin.run {
                Log.d("GetGames",listGame.size.toString())

            val listNews = ArrayList<Newsitem>()
            for(game in listGame){
                apiTalker.getNews(game.appid,mContext!!).observe(pCycle){
                    listNewsGame : List<Newsitem> -> kotlin.run {
                    listNews.addAll(listNewsGame)
                    mNews.postValue(listNews)

                }}
            }


            }
        }
    }
}