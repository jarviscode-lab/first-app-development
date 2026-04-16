package com.gavi.farasisokogarden

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams

class Payments : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payments)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        retrieve/receive Extras data the product_name and product_cost
//        this data is passed via intent

        val productname = intent.getStringExtra("product_name")
        val productcost = intent.getIntExtra("product_cost",0)
        val productphoto = intent.getStringExtra("product_photo")
        val productdescription = intent.getStringExtra("product_description")


//        find views by ids
        val photo = findViewById<ImageView>(R.id.product_photo)
        val name = findViewById<TextView>(R.id.product_name)
        val cost = findViewById<TextView>(R.id.product_cost)
        val description = findViewById<TextView>(R.id.product_description)
        val phone = findViewById<EditText>(R.id.phone)
        val buy = findViewById<Button>(R.id.buy)

//        update textviews with the values passed via intent
        name.text = productname
        cost.text = "Ksh $productcost"
        description.text = productdescription

//        glide - loads the photo
        Glide.with(this)
            .load(productphoto)
            .into(photo)

        buy.setOnClickListener {
//            set api endpoint
            val api = "https://gavi.alwaysdata.net/api/mpesa_payment"


//            create data using RequestParams, put phone and cost as keyvalue pairs
            val data = RequestParams()
            data.put("amount",productcost)  // passed via intent
            data.put("phone",phone) // entered by user in phone editor

//            access api helper
            val helper = ApiHelper(applicationContext)
//            post data to api endpoint
            helper.post(api,data)


        }


    }
}