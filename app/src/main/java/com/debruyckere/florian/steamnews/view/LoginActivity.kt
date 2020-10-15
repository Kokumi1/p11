package com.debruyckere.florian.steamnews.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.debruyckere.florian.steamnews.R

class LoginActivity: AppCompatActivity() {

    private val userText : TextView = findViewById(R.id.login_username_edit)
    private val passwordText : TextView = findViewById(R.id.login_password_edit)
    private val validButton : Button = findViewById(R.id.login_validation_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        validButton.setOnClickListener(){
            val username = userText.text.toString()
            val password = passwordText.text.toString()

            //do authentification
        }

    }
}