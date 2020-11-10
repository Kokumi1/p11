package com.debruyckere.florian.steamnews.services

import com.debruyckere.florian.steamnews.model.generatedclass.ExampleGame
import com.debruyckere.florian.steamnews.model.generatedclass.ExampleId
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
            Observable<ExampleId>

    @GET("IPlayerService/GetOwnedGames/v0001/")
    fun steamGamesGetter(
        @Query("key")key: String,
        @Query("steamid")steamid: String,
        @Query("include_played_free_games")includeFreeGames: Boolean
    ): Observable<ExampleGame>

}