package com.example.razryadapi

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.razryadapi.Adapter_and_stuff.performClickAnimation
import com.example.razryadapi.map.Loft_Map

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val authors: ImageButton = findViewById(R.id.authors)
        val fandoms: ImageButton = findViewById(R.id.fandoms)
        val map: ImageButton = findViewById(R.id.map)
        val whereLoft: ImageButton = findViewById(R.id.Where)
        val quest: ImageButton = findViewById(R.id.go_quest)

        authors.setOnClickListener {
            performClickAnimation(it) {
                val intent = Intent(this, GoToAuthors::class.java)
                startActivity(intent)
            }
        }

        fandoms.setOnClickListener {
            performClickAnimation(it) {
                val intent = Intent(this, GoToFandoms::class.java)
                startActivity(intent)
            }
        }

        map.setOnClickListener {
            performClickAnimation(it) {
                val intent = Intent(this, Loft_Map::class.java)
                startActivity(intent)
            }
        }
        quest.setOnClickListener {
            performClickAnimation(it) {
                val intent = Intent(this, Quest::class.java)
                startActivity(intent)
            }
        }
        whereLoft.setOnClickListener {
            performClickAnimation(it) {
                val intent = Intent(this, Where_loft::class.java)
                startActivity(intent)
            }
        }
    }

}