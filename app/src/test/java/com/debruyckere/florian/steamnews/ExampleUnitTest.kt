package com.debruyckere.florian.steamnews

import com.debruyckere.florian.steamnews.model.Comment
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun commentTest(){
        val comment = Comment("user","comment")

        assertEquals("comment",comment.content)
    }
}