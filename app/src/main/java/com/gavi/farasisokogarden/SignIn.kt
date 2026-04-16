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

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signin=findViewById<TextView>(R.id.signin_link)

        signin.setOnClickListener {

            val api = "https://gavi.alwaysdata.net/api/signin"
//            https://gavi.alwaysdata.net/api/signup
//            https://gavi.alwaysdata.net/api/getproductsdetails
//            https://gavi.alwaysdata.net/api/addproducts

            val signInLink = Intent(applicationContext, SignUp::class.java)

            startActivity(signInLink)
        }

//        find the edittext buttons by id

        val email = findViewById<EditText>(R.id.email)

        val password = findViewById<EditText>(R.id.password)

        val signinclicker = findViewById<Button>(R.id.signin)

        signinclicker.setOnClickListener {
            val api = "https://gavi.alwaysdata.net/api/signin"

//            request params is the container used to collect user details in form of key and value pairs to database like formdata in js
            val data = RequestParams()

            data.put("email",email.text.toString())
            data.put("password",password.text.toString().trim()) // .trim - removes spaces

//            Api helper - it delivers our data to api

            val helper = ApiHelper(applicationContext) // application context calls the activity
            helper.post_login(api,data) // post_login - logs the user in the app


        }


    }
}