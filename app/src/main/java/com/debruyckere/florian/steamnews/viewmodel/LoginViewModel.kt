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

    fun getUser(pEmail: String, pPassword : String,pContext: Context): LiveData<FirebaseUser?>{
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
                                Log.d("FIRESTORE ","recherche complete: "+document.id +" "+ document.data["steamId"])
                                val sharedPreferences = pContext.getSharedPreferences("steam",Context.MODE_PRIVATE)
                                sharedPreferences.edit().putString("id",document.data["steamId"].toString()).apply()
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

    fun createUser(pEmail: String, pPassword: String,pUsername: String ,pContext: Context,pLifecycleOwner: LifecycleOwner): LiveData<FirebaseUser?>{
        mAuth.createUserWithEmailAndPassword(pEmail,pPassword)
            .addOnCompleteListener{task ->
                if(task.isSuccessful){
                    Log.d("SUBSCRIPTION","SUBSCRIBE SUCCESS")
                    mUser.postValue(mAuth.currentUser)

                    lateinit var steamId : String
                    lateinit var data : HashMap<String,String>
                    val apiTalker = ApiTalker()
                    apiTalker.login(pUsername,pContext).observe(pLifecycleOwner){ result ->
                        steamId = result
                            data = hashMapOf(
                            "firebaseUser" to mAuth.currentUser!!.uid,
                            "steamId" to steamId
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