package com.debruyckere.florian.steamnews.services

import android.content.Context
import android.util.Log
import com.debruyckere.florian.steamnews.BuildConfig
import com.debruyckere.florian.steamnews.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Debruyckère Florian on 07/10/2020.
 */
class ApiTalker {

    //private val apiServe by lazy { create() }

    private var disposable : Disposable? = null

    companion object{
        fun create(pContext: Context) : ApiService{
            val retrofit = Retrofit.Builder().addCallAdapterFactory(
                RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(pContext.resources.getString(R.string.steamId_url))
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }

    fun login(pUsername : String, pPassword : String, pContext: Context): String{

        val apiServe by lazy { create(pContext) }
        var retour = ""

        disposable = apiServe.steamIdGetter(BuildConfig.API_KEY,"dflorian")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> retour = result.responseId.steamid },
                { error -> Log.e("retrofit ID: ", error.stackTraceToString()) })

        return retour
    }

    fun getGames(pUserId : String, pContext: Context){
        //id 76561198358887469

        val apiServe by lazy { create(pContext) }

        disposable = apiServe.steamGamesGetter(BuildConfig.API_KEY,pUserId,true)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> Log.d("retrofit GAME", "GameCount: "+result.response.games.size) },
                { error -> Log.e("retrofit GAME: ", error.stackTraceToString()) })

    }

    fun getNews(pGameId : Int){
        //TODO: get news from game (id)
    }
}