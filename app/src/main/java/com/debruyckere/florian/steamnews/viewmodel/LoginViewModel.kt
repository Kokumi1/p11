package com.debruyckere.florian.steamnews.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.debruyckere.florian.steamnews.services.ApiTalker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * Created by Debruyckère Florian on 07/10/2020.
 */
class LoginViewModel : ViewModel() {

    private var mAuth : FirebaseAuth = FirebaseAuth.getInstance()
    private var mUser : MutableLiveData<FirebaseUser?> = MutableLiveData()
    private val db = Firebase.firestore

    fun getUser(pEmail: String, pPassword : String): LiveData<FirebaseUser?>{
        //auth = FirebaseAuth.getInstance()

        if(mAuth.currentUser != null){ val currentUser = mAuth.currentUser }

        mAuth.signInWithEmailAndPassword(pEmail,pPassword)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful) {
                    mUser.postValue(mAuth.currentUser)

                    db.collection("userId")
                        .whereEqualTo("firebaseUser",mAuth.currentUser!!.uid)
                        .get()
                        .addOnSuccessListener { result->
                            for(document in result){
                                Log.d("FIRESTORE ","recherche complete: "+document.id +" "+ document.data)
                                //TODO: save somewhere steam id for apply
                            }
                        }
                        .addOnFailureListener{exception -> Log.d("FIRESTORE",
                            "Recheche Failed! $exception"
                        ) }


                }

                else mUser.postValue(null)
            }
        return mUser
    }

    fun createUser(pEmail: String, pPassword: String,pContext: Context,pLifecycleOwner: LifecycleOwner): LiveData<FirebaseUser?>{
        mAuth.createUserWithEmailAndPassword(pEmail,pPassword)
            .addOnCompleteListener{task ->
                if(task.isSuccessful){
                    Log.d("SUBSCRIPTION","SUBSCRIBE SUCCESS")
                    mUser.postValue(mAuth.currentUser)

                    var steamId = ""
                    lateinit var data : HashMap<String,String>
                    val apiTalker = ApiTalker()
                    apiTalker.login("steamUser","",pContext).observe(pLifecycleOwner){ result ->
                        steamId = result
                            data = hashMapOf(
                            "firebaseUser" to mAuth.currentUser!!.uid,
                            "steamId" to steamId       //TODO: Add edit pop up for get steam user name and search
                        )
                    }

                    db.collection("userId").document()
                        .set(data)
                        .addOnCompleteListener{fireTask ->
                            if(fireTask.isSuccessful) Log.d("FIRESTORE","data created with success")
                            else Log.d("FIRESTORE","Huge fail during subscription")
                        }
                }
                else {
                    Log.d("SUBSCRIPTION","SUBSCRIBE FAILED!")
                    mUser.postValue(null)


                }
            }

        return mUser
    }


}