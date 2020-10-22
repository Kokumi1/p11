package com.debruyckere.florian.steamnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.w3c.dom.Comment

/**
 * Created by Debruyckère Florian on 07/10/2020.
 */
class CommentDownloader : ViewModel() {

    private var mComment : MutableLiveData<List<Comment>>? = null
    
    fun getComment() : LiveData<List<Comment>>{
        return mComment!!
    }

}