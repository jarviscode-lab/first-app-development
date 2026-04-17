package com.gavi.farasisokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ButtonBarLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        sign up intent
        val signup=findViewById<Button>(R.id.signup)

        signup.setOnClickListener {
            val signupIntent= Intent(applicationContext, SignUp::class.java)

            startActivity(signupIntent)

        }

//        sign in intent
        val signin=findViewById<Button>(R.id.signin)

        signin.setOnClickListener {
            val signinIntent = Intent(applicationContext, SignIn::class.java)

            startActivity(signinIntent)

        }

//        about us intent
        val about = findViewById<Button>(R.id.about)

        about.setOnClickListener {

            val aboutIntent = Intent(applicationContext, About::class.java)
            startActivity(aboutIntent)

        }

//       speech intent
        val speech = findViewById<Button>(R.id.btnSpeak)
         speech.setOnClickListener {

             val speechIntent = Intent(applicationContext, SpeechToTextActivity::class.java)
             startActivity(speechIntent)

         }



//        fetch progress bar and recycler view by their ids
        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        val  recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        val api = "https://gavi.alwaysdata.net/api/getproductsdetails"

        val helper = ApiHelper(applicationContext)
        helper.loadProducts(api,recyclerView,progressBar) //progressbar shows the loader

        Toast.makeText(this, "Signing up....",Toast.LENGTH_SHORT).show()

    }
}