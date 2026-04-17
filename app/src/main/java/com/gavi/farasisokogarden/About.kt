package com.gavi.farasisokogarden

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class About : AppCompatActivity() {
    lateinit var tts: TextToSpeech // late init - declares variable and initialize later

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        find views and button by their ids
        val about = findViewById<TextView>(R.id.textview)
        val listen = findViewById<Button>(R.id.speakButton)
        val speak = findViewById<EditText>(R.id.inputText)


//        initialize/start the text to speech engine
        tts = TextToSpeech(this) {

//            check if speech is successful
            if (it == TextToSpeech.SUCCESS) {   //
                tts.language = Locale.US
            }

        }

//        set button listener
        speak.setOnClickListener {

            val textt = speak.text.toString()
            tts.speak(textt, TextToSpeech.QUEUE_FLUSH, null, null)
            // speak - function that allows speech to start
            //Queue_Flush - clears what is being spoken in the background
            // null - not a must

        }
    }

//        stop the tts from speaking when app is closed/destroyed/killed
        override fun  onDestroy() {
        tts.stop() // stops tts
        tts.shutdown()
        super.onDestroy()
    }
}