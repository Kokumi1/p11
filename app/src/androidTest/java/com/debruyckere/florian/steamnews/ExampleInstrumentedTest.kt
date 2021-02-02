package com.debruyckere.florian.steamnews

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.debruyckere.florian.steamnews.services.ApiTalker
import com.debruyckere.florian.steamnews.services.ServerTalker

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var appContext: Context
    @Test
    fun useAppContext() {
        // Context of the app under test.
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.debruyckere.florian.steamnews", appContext.packageName)
    }

    @Test
    fun loginTest(){
        appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val apiTalker = ApiTalker()
        assertEquals("76561198358887469",apiTalker.login("kokumi",appContext))
    }

    @Test
    fun getGamesTest(){
        appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val apiTalker = ApiTalker()
        assert(apiTalker.getGames("76561198358887469",appContext).value!!.isNotEmpty())
    }

    @Test
    fun getFirebaseUser(){
        appContext = InstrumentationRegistry.getInstrumentation().targetContext

        assert(ServerTalker().getSteamId("fldebruyck@gmail.com","Punigi",appContext).value != null)
    }
}