package com.debruyckere.florian.steamnews.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.debruyckere.florian.steamnews.model.Comment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * Created by Debruyckère Florian on 07/10/2020.
 */
class CommentViewModel : ViewModel() {

    private val db = Firebase.firestore

    private var mComment : MutableLiveData<List<Comment>> = MutableLiveData()
    
    fun getComment(pArticleId: String) : LiveData<List<Comment>>{

        db.collection(pArticleId)
            .get()
            .addOnSuccessListener { result->
                Log.d("COMMENT FIRESTORE","comment found: "+result.documents.size)
                val list = ArrayList<Comment>()

                for (document in result.documents)
                    list.add(Comment( document["user"].toString(), document["content"].toString() ))


                mComment.postValue(list)
            }
            .addOnFailureListener{
                Log.d("COMMENT FIRESTORE","That a fail! "+it.stackTrace)
            }

        return mComment
    }

    fun addComment(pComment : Comment,newsId : String){

        val data = hashMapOf(
            "user" to pComment.user,
            "content" to pComment.content
        )

        db.collection(newsId).document()
            .set(data)
            .addOnCompleteListener{
                if(it.isSuccessful) Log.d("COMMENT FIRESTORE","Comment created with success")
                else Log.d("COMMENT FIRESTORE",it.exception!!.stackTraceToString())
            }

    }

}