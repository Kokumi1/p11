package com.debruyckere.florian.steamnews.services

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * Created by Debruyckère Florian on 29/01/2021.
 */
class ServerTalker {

    private var mAuth : FirebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore

    /**
     * send query to firebase to login
     *
     * @param pEmail Email of the account
     * @param pPassword password of the account
     */
    fun getSteamId(pEmail: String, pPassword: String, pContext: Context): LiveData<FirebaseUser?> {

         val user : MutableLiveData<FirebaseUser?> = MutableLiveData()

        mAuth.signInWithEmailAndPassword(pEmail,pPassword)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful) {
                    user.postValue(mAuth.currentUser)

                    //get the stored steamID in Firestore
                    db.collection("userId")
                        .whereEqualTo("firebaseUser",mAuth.currentUser!!.uid)
                        .get()
                        .addOnSuccessListener { result->
                            for(document in result){
                                Log.d("FIRESTORE ","recherche complete: "+document.id +" "+ document.data["steamId"])
                                //save the steamID in sharedPreferences
                                val sharedPreferences = pContext.getSharedPreferences("steam",
                                    Context.MODE_PRIVATE)
                                sharedPreferences.edit().putString("id",document.data["steamId"].toString()).apply()
                            }
                        }
                        .addOnFailureListener{exception -> Log.d("FIRESTORE",
                            "Recheche Failed! $exception"
                        ) }


                }

                else user.postValue(null)
            }

        return user

    }

    /**
     * send query to firebase to subscribe
     *
     * @param pEmail Email of the account
     * @param pPassword password of the account
     * @param pUsername Steam Username
     */
    fun setSteamId(pEmail: String,pPassword: String,pUsername: String,pLifecycleOwner: LifecycleOwner, pContext: Context): LiveData<FirebaseUser?>{

        val user : MutableLiveData<FirebaseUser?> = MutableLiveData()

        mAuth.createUserWithEmailAndPassword(pEmail,pPassword)
            .addOnCompleteListener{task ->
                if(task.isSuccessful){
                    Log.d("SUBSCRIPTION","SUBSCRIBE SUCCESS")
                    user.postValue(mAuth.currentUser)

                    lateinit var steamId : String
                    lateinit var data : HashMap<String,String>
                    val apiTalker = ApiTalker()
                    //get SteamID from SteamAPI
                    apiTalker.login(pUsername,pContext).observe(pLifecycleOwner){ result ->
                        steamId = result
                        data = hashMapOf(
                            "firebaseUser" to mAuth.currentUser!!.uid,
                            "steamId" to steamId
                        )
                    }

                    //save SteamID in Firestore
                    db.collection("userId").document()
                        .set(data)
                        .addOnCompleteListener{fireTask ->
                            if(fireTask.isSuccessful) Log.d("FIRESTORE","data created with success")
                            else Log.d("FIRESTORE","Huge fail during subscription")
                        }
                }
                else {
                    Log.d("SUBSCRIPTION","SUBSCRIBE FAILED!")
                    user.postValue(null)


                }
            }
        return user
    }
}