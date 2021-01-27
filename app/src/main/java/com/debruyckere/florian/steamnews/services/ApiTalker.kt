package com.debruyckere.florian.steamnews.services

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.debruyckere.florian.steamnews.BuildConfig
import com.debruyckere.florian.steamnews.R
import com.debruyckere.florian.steamnews.model.generatedclass.Game
import com.debruyckere.florian.steamnews.model.generatedclass.Newsitem
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

            //create Retrofit
            val retrofit = Retrofit.Builder().addCallAdapterFactory(
                RxJava2CallAdapterFactory.create()
            )   .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(pContext.resources.getString(R.string.steamId_url))
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }

    /**
     * Search steamId in the steamAPI
     *
     * @param pUsername steam Username use for the research
     */
    fun login(pUsername: String, pContext: Context): LiveData<String>{

        val apiServe by lazy { create(pContext) }
        val toReturn : MutableLiveData<String> = MutableLiveData()

        disposable = apiServe.steamIdGetter(BuildConfig.API_KEY, pUsername)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> toReturn.postValue(result.response.steamid) },
                { error -> Log.e("retrofit ID: ", error.stackTraceToString()) })

        return toReturn
    }

    /**
     * get the list of games in Steam API
     *
     * @param pUserId steamId of the current user
     */
    fun getGames(pUserId: String, pContext: Context): LiveData<List<Game>>{

        val apiServe by lazy { create(pContext) }
        val toReturn : MutableLiveData<List<Game>> = MutableLiveData()

        disposable = apiServe.steamGamesGetter(BuildConfig.API_KEY, pUserId, "true")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> toReturn.postValue(result.response.games)},

                { error -> Log.e("retrofit GAME: ", error.stackTraceToString()) })

        return toReturn
    }

    /**
     * get a list of 5 news for one games
     *
     * @param pGameId id of the games
     */
    fun getNews(pGameId: Int, pContext: Context): LiveData<List<Newsitem>>{

        val apiServe by lazy{ create(pContext)}
        val toReturn : MutableLiveData<List<Newsitem>> = MutableLiveData()

        disposable = apiServe.steamNewsGetter(pGameId, 5, 300)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> toReturn.postValue(result.appnews.newsitems) },
                { error -> Log.e("retrofit NEWS", error.stackTraceToString()) })

        return toReturn
    }
}
