package com.example.razryadapi

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.razryadapi.Adapter_and_stuff.performClickAnimation
import com.example.razryadapi.map.Loft_Map

class Where_loft : AppCompatActivity() {
    private lateinit var imageView: CustomImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_where_loft)
        imageView = findViewById(R.id.where_loft_map)

        val map: ImageButton = findViewById(R.id.imageButton)

        map.setOnClickListener {
            performClickAnimation(it) {
                val intent = Intent(this, Loft_Map::class.java)
                startActivity(intent)
            }
        }

    }
}