package com.debruyckere.florian.steamnews.viewmodel

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.debruyckere.florian.steamnews.services.ServerTalker
import com.google.firebase.auth.FirebaseUser

/**
 * Created by Debruyckère Florian on 07/10/2020.
 */
class LoginViewModel : ViewModel() {

    private var mUser : MutableLiveData<FirebaseUser?> = MutableLiveData()

    /**
     * sign in with firebase
     *
     * @param pEmail Email of the account
     * @param pPassword password of the account
     */
    fun getUser(pEmail: String, pPassword : String,pContext: Context,pLifecycleOwner: LifecycleOwner): LiveData<FirebaseUser?>{
        ServerTalker().getSteamId(pEmail,pPassword,pContext).observe(pLifecycleOwner){
            mUser.postValue(it)
        }

        return mUser
    }

    /**
     * subscribe and create new account
     *
     * @param pEmail Email of the account
     * @param pPassword password of the account
     * @param pUsername Steam Username
     */
    fun createUser(pEmail: String, pPassword: String,pUsername: String ,pContext: Context,pLifecycleOwner: LifecycleOwner): LiveData<FirebaseUser?>{

        ServerTalker().setSteamId(pEmail,pPassword,pUsername,pLifecycleOwner,pContext).observe(pLifecycleOwner){
            mUser.postValue(it)
        }
        return mUser
    }


}