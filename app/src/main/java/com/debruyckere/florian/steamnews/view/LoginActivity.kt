package com.debruyckere.florian.steamnews.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

        setSupportActionBar(findViewById(R.id.login_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        userText = findViewById(R.id.login_username_edit)
        validButton = findViewById(R.id.login_validation_button)
        passwordText = findViewById(R.id.login_password_edit)

        validButton!!.setOnClickListener(){
            val username = userText!!.text.toString()
            val password = passwordText!!.text.toString()

            //do authentification
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =

        when (item.itemId){

            R.id.toolbar_login -> {
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
                true
            }

            R.id.toolbar_config-> {
                val configIntent = Intent(this, ConfigActivity::class.java)
                startActivity(configIntent)
                true
            }
            android.R.id.home-> {
                val homeIntent = Intent(this, MainActivity::class.java)
                startActivity(homeIntent)
                true
            }

            else-> super.onOptionsItemSelected(item)
        }

}