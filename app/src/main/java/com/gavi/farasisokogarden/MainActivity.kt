package com.gavi.farasisokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable full screen layout
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Handle system UI spacing (status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //  BUTTONS

        // Sign Up Button / intent
        val signup = findViewById<Button>(R.id.signup)
        signup.setOnClickListener {
            startActivity(Intent(applicationContext, SignUp::class.java))
        }

        // Sign In Button
        val signin = findViewById<Button>(R.id.signin)
        signin.setOnClickListener {
            startActivity(Intent(applicationContext, SignIn::class.java))
        }

        // About Button
        val about = findViewById<Button>(R.id.about)
        about.setOnClickListener {
            startActivity(Intent(applicationContext, About::class.java))
        }

        // Speech-to-Text Button
        val speech = findViewById<Button>(R.id.btnSpeak)
        speech.setOnClickListener {
            startActivity(Intent(applicationContext, SpeechToTextActivity::class.java))
        }

        // DATA LOADING

        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        // API Endpoint
        val api = "https://gavi.alwaysdata.net/api/getproductsdetails"

        // Helper class to fetch and display products
        val helper = ApiHelper(applicationContext)
        helper.loadProducts(api, recyclerView, progressBar)

        // Demo Toast (can be changed later)
        Toast.makeText(this, "Welcome to Fruitopia", Toast.LENGTH_SHORT).show()
    }
}