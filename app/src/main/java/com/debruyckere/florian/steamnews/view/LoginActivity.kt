package com.debruyckere.florian.steamnews.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.debruyckere.florian.steamnews.R
import org.w3c.dom.Text

class LoginActivity: AppCompatActivity() {

    private var userText : TextView? =null
    private var passwordText : TextView? = null
    private var validButton :  Button? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userText = findViewById(R.id.login_username_edit)
        validButton = findViewById(R.id.login_validation_button)
        passwordText = findViewById(R.id.login_password_edit)

        validButton!!.setOnClickListener(){
            val username = userText!!.text.toString()
            val password = passwordText!!.text.toString()

            //do authentification
        }

    }
}