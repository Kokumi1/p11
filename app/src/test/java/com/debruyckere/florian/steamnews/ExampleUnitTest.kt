package com.debruyckere.florian.steamnews

import android.content.Context
import com.debruyckere.florian.steamnews.model.Comment
import com.debruyckere.florian.steamnews.services.ApiTalker
import com.debruyckere.florian.steamnews.services.ServerTalker
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {
    @Mock
    private lateinit var appContext: Context
    @Test
    fun commentTest(){
        val comment = Comment("user","comment")

        assertEquals("comment",comment.content)
    }

    @Test
    fun loginTest(){
        val apiTalker = ApiTalker()

        assertEquals("76561198358887469", apiTalker.login("kokumi",appContext).value)

    }

    @Test
    fun getGamesTest(){

        val apiTalker = ApiTalker()
        assert(apiTalker.getGames("76561198358887469",appContext).value!!.isNotEmpty())
    }

    @Test
    fun getFirebaseUser(){

        assert(ServerTalker().getSteamId("fldebruyck@gmail.com","Punigi",appContext).value != null)
    }
}
