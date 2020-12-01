package com.debruyckere.florian.steamnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Debruyckère Florian on 07/10/2020.
 */
class NewsViewModel : ViewModel() {

    private var mWeb : MutableLiveData<String> = MutableLiveData()

    fun getWeb() : LiveData<String>{
        return mWeb
    }

    fun setWeb(pUrl: String){
        mWeb.postValue(pUrl)
    }
}