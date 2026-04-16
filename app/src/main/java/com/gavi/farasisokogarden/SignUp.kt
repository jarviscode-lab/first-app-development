package com.gavi.farasisokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signup = findViewById<TextView>(R.id.signup_link)

        signup.setOnClickListener {

            val api = "https://gavi.alwaysdata.net/api/signup"

            val signUpLink = Intent(applicationContext, SignIn::class.java)

            startActivity(signUpLink)
        }

        //        find the edittext buttons by id

        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email)
        val phone = findViewById<EditText>(R.id.phone)
        val password = findViewById<EditText>(R.id.password)

        val signupclicker = findViewById<Button>(R.id.signup)

        signupclicker.setOnClickListener {
            val api = "https://gavi.alwaysdata.net/api/signup"
//            request params is the container used to collect user details in form of key and value pairs to database like formdata in js
            val data = RequestParams()

            data.put("username",username.text.toString())
            data.put("email",email.text.toString())
            data.put("phone",phone.text.toString())
            data.put("password",password.text.toString().trim()) // .trim - removes spaces

//            Api helper - it delivers our data to api

            val helper = ApiHelper(applicationContext) // application context calls the activity
            helper.post_login(api,data) // post_login - logs the user in the app

        }


    }
}