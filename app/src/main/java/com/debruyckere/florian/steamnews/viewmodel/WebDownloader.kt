package com.debruyckere.florian.steamnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import java.net.URL

/**
 * Created by Debruyckère Florian on 07/10/2020.
 */
class WebDownloader : ViewModel() {

    private var mWeb : LiveData<URL>? = null

    fun getWeb() : LiveData<URL>{
        return mWeb!!
    }
}