package com.debruyckere.florian.steamnews.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.debruyckere.florian.steamnews.model.News
import com.debruyckere.florian.steamnews.model.generatedclass.Game
import com.debruyckere.florian.steamnews.services.ApiTalker

/**
 * Created by Debruyckère Florian on 07/10/2020.
 */
class MainViewModel(private val pContext: Context) : ViewModel() {

    private var mNews : MutableLiveData<List<News>> = MutableLiveData()

    fun getNews(pCycle: LifecycleOwner) : LiveData<List<News>>{
        val tmp = createNews(pCycle)
        mNews.postValue(tmp)
        return mNews
    }

    private fun createNews(pCycle : LifecycleOwner) : ArrayList<News>{

        val apiTalker = ApiTalker()

        apiTalker.getGames("76561198358887469",pContext).observe(pCycle){
            list : List<Game> -> kotlin.run {
                Log.d("GetGames",list.size.toString())

            }
        }


        val data = ArrayList<News>()

        data.add(News(1,"url","title","Game"))

        return data
    }
}