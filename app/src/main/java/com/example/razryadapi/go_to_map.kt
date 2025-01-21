package com.example.razryadapi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class go_to_map : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map)

        val smallRoom: Button = findViewById(R.id.Small_room)
        val bigRoom: Button = findViewById(R.id.big_room)
        val secondFloor: Button = findViewById(R.id.Second_floor)

        smallRoom.setOnClickListener {
            val intent = Intent(this, small_room::class.java)
            startActivity(intent)
        }

        bigRoom.setOnClickListener {
            val intent = Intent(this, big_room::class.java)
            startActivity(intent)
        }

        secondFloor.setOnClickListener {
            val intent = Intent(this, second_floor::class.java)
            startActivity(intent)
        }
    }
}