package com.debruyckere.florian.steamnews.services

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.debruyckere.florian.steamnews.BuildConfig
import com.debruyckere.florian.steamnews.R
import com.debruyckere.florian.steamnews.model.generatedclass.Game
import com.debruyckere.florian.steamnews.model.generatedclass.Newsitem
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder().addCallAdapterFactory(
                RxJava2CallAdapterFactory.create()
            )   .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(pContext.resources.getString(R.string.steamId_url))
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }

    fun login(pUsername: String, pPassword: String, pContext: Context): LiveData<String>{

        val apiServe by lazy { create(pContext) }
        val retour : MutableLiveData<String> = MutableLiveData()

        disposable = apiServe.steamIdGetter(BuildConfig.API_KEY, "dflorian")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> retour.postValue(result.steamid) },
                { error -> Log.e("retrofit ID: ", error.stackTraceToString()) })

        return retour
    }

    fun getGames(pUserId: String, pContext: Context): LiveData<List<Game>>{

        val apiServe by lazy { create(pContext) }
        val retour : MutableLiveData<List<Game>> = MutableLiveData()

        disposable = apiServe.steamGamesGetter(BuildConfig.API_KEY, pUserId, "true")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    run {
                        val gson = Gson().toJson(result)
                        println(gson)
                        retour.postValue(result.responseGame.games!!)
                    }
                },
                { error -> Log.e("retrofit GAME: ", error.stackTraceToString()) })

        return retour
    }

    fun getNews(pGameId: Int, pContext: Context): LiveData<List<Newsitem>>{

        val apiServe by lazy{ create(pContext)}
        val retour : MutableLiveData<List<Newsitem>> = MutableLiveData()

        disposable = apiServe.steamNewsGetter(pGameId, 5, 300)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> retour.postValue(result.appnews.newsitems) },
                { error -> Log.e("retrofit NEWS", error.stackTraceToString()) })

        return retour
    }
}