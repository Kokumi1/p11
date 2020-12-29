package com.debruyckere.florian.steamnews.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.debruyckere.florian.steamnews.R
import com.debruyckere.florian.steamnews.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseUser

class LoginActivity: AppCompatActivity() {

    private lateinit var userText : TextView
    private lateinit var passwordText : TextView
    private lateinit var validButton :  Button
    private lateinit var subscribeButton: Button

    private lateinit var mLoginViewModel : LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setSupportActionBar(findViewById(R.id.login_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var userData: FirebaseUser?
        mLoginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        userText = findViewById(R.id.login_username_edit)
        validButton = findViewById(R.id.login_validation_button)
        passwordText = findViewById(R.id.login_password_edit)
        subscribeButton = findViewById(R.id.login_subscribe_button)

        validButton.setOnClickListener{
            val email = userText.text.toString()
            val password = passwordText.text.toString()
            Log.d("AUTHENTICATION","Mission Start")

            //do authentication
            mLoginViewModel.getUser(email,password).observe(this){ user ->
                if(user != null){
                    //show popup & return to main

                    userData = user
                    Log.d("AUTHENTICATION: ", "success welcome back "+ userData!!.uid)

                    startActivity(Intent(this,MainActivity::class.java))
                }
                else{
                    Log.d("AUTHENTICATION: ","Mission Failed !!")
                }
            }
        }

        subscribeButton.setOnClickListener{
            val email = userText.text.toString()
            val password = passwordText.text.toString()

            mLoginViewModel.createUser(email,password).observe(this){user ->
                if(user != null){
                    Log.d("SUBSCRIPTION","success welcome new user")
                }else{
                    Log.d("SUBSCRIPTION","No Don't enter. Failure")
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =

        when (item.itemId){

            R.id.toolbar_login -> {
                //val loginIntent = Intent(this, LoginActivity::class.java)
                //startActivity(loginIntent)
                Toast.makeText(this,"You already are in that place",Toast.LENGTH_SHORT)
                true
            }

            R.id.toolbar_config-> {
                startActivity(Intent(this, ConfigActivity::class.java))
                true
            }
            android.R.id.home-> {
                //val homeIntent = Intent(this, MainActivity::class.java)
                //startActivity(homeIntent)
                Toast.makeText(this,"You have to log before.",Toast.LENGTH_LONG)
                true
            }

            else-> super.onOptionsItemSelected(item)
        }

}