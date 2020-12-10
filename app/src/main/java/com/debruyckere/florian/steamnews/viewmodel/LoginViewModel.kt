package com.debruyckere.florian.steamnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * Created by Debruyckère Florian on 07/10/2020.
 */
class LoginViewModel : ViewModel() {

    private var mAuth : FirebaseAuth = FirebaseAuth.getInstance()
    private var mUser : MutableLiveData<FirebaseUser?> = MutableLiveData()

    fun getUser(pEmail: String, pPassword : String): LiveData<FirebaseUser?>{
        //auth = FirebaseAuth.getInstance()

        if(mAuth.currentUser != null){ val currentUser = mAuth.currentUser }

        mAuth.signInWithEmailAndPassword(pEmail,pPassword)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful) mUser.postValue(mAuth.currentUser)

                else mUser.postValue(null)
            }
        return mUser
    }

    fun createUser(pEmail: String, pPassword: String){
        mAuth.createUserWithEmailAndPassword(pEmail,pPassword)
            .addOnCompleteListener{task ->
                if(task.isSuccessful) mUser.postValue(mAuth.currentUser)

                else mUser.postValue(null)
            }

    }


}