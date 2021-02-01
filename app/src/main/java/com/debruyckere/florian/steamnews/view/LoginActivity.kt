package com.debruyckere.florian.steamnews.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
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
    private lateinit var mLoadingBar: ProgressBar

    private lateinit var mLoginViewModel : LoginViewModel

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //toolbar
        setSupportActionBar(findViewById(R.id.login_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "SteamNews"

        //viewModel
        var userData: FirebaseUser?
        mLoginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        userText = findViewById(R.id.login_username_edit)
        validButton = findViewById(R.id.login_validation_button)
        passwordText = findViewById(R.id.login_password_edit)
        subscribeButton = findViewById(R.id.login_subscribe_button)
        mLoadingBar= findViewById(R.id.login_loading)

        //login button
        validButton.setOnClickListener{
            val email = userText.text.toString()
            val password = passwordText.text.toString()
            Log.d("AUTHENTICATION","Mission Start")

            mLoadingBar.visibility = View.VISIBLE

            //do log in
            mLoginViewModel.getUser(email,password,this,this).observe(this){ user ->
                if(user != null){
                    //show popup & return to main

                    userData = user
                    Log.d("AUTHENTICATION: ", "success welcome back "+ userData!!.uid)

                    startActivity(Intent(this,MainActivity::class.java))
                }
                else{
                    Log.d("AUTHENTICATION: ","Mission Failed !!")
                    Toast.makeText(this,"authentification failed",Toast.LENGTH_LONG).show()
                    mLoadingBar.visibility = View.GONE
                }
            }
        }

        //subscribe button
        subscribeButton.setOnClickListener{
            val email = userText.text.toString()
            val password = passwordText.text.toString()

            //alert dialog
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Steam Username")
            val alertView : View = layoutInflater.inflate(R.layout.login_pop,null)
            val alertEdit = alertView.findViewById<EditText>(R.id.login_edit)
            val alertButton = alertView.findViewById<Button>(R.id.login_button)

            //do the subscribe
            alertButton.setOnClickListener{
                mLoadingBar.visibility = View.VISIBLE
                mLoginViewModel.createUser(email , password , alertEdit.text.toString() ,this,
                    this).observe(this){user ->
                    if(user != null){
                        Log.d("SUBSCRIPTION","success welcome new user")
                        Toast.makeText(this,"Subscription finish: welcome",Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,MainActivity::class.java))
                    }else{
                        Log.d("SUBSCRIPTION","No Don't enter. Failure")
                    }
                }
            }


        }

    }
}