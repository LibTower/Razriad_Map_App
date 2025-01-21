package com.example.razryadapi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val map: Button = findViewById(R.id.map)
        val authors: Button = findViewById(R.id.authors)


        map.text = getString(R.string.b_map_text)
        authors.text = getString(R.string.b_authors_text)



        authors.setOnClickListener {
            val intent = Intent(this, go_to_authors::class.java)
            startActivity(intent)
        }

        map.setOnClickListener {
            val intent = Intent(this, go_to_map::class.java)
            startActivity(intent)
        }

    }

}