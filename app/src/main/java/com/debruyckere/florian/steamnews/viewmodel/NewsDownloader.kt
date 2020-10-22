package com.debruyckere.florian.steamnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.debruyckere.florian.steamnews.model.News

/**
 * Created by Debruyckère Florian on 07/10/2020.
 */
class NewsDownloader : ViewModel() {

    private var mNews : MutableLiveData<List<News>> = MutableLiveData()

    init {
        mNews.value = createNews()
    }

    fun getNews() : LiveData<List<News>>{
        return mNews
    }

    private fun createNews() : ArrayList<News>{
        val data = ArrayList<News>()

        data.add(News(1,"url","title","Game"))

        return data
    }
}