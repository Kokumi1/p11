package com.debruyckere.florian.steamnews.services

import com.debruyckere.florian.steamnews.model.generatedclass.ApiGame
import com.debruyckere.florian.steamnews.model.generatedclass.ApiID
import com.debruyckere.florian.steamnews.model.generatedclass.ApiNews
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Debruyckère Florian on 09/11/2020.
 */
interface ApiService {

    @GET("ISteamUser/ResolveVanityURL/v0001/")
    fun steamIdGetter(
        @Query("key") key: String,
        @Query("vanityurl") vanityurl: String):
            Observable<ApiID>

    @GET("IPlayerService/GetOwnedGames/v0001/")
    fun steamGamesGetter(
        @Query("key")key: String,
        @Query("steamid")steamid: String,
        @Query("include_played_free_games")includeFreeGames: String
    ): Observable<ApiGame>

    @GET("ISteamNews/GetNewsForApp/v0002/")
    fun steamNewsGetter(
        @Query("appid")appid : Int,
        @Query("count")count : Int,
        @Query("maxlength")maxlength : Int
    ): Observable<ApiNews>
}