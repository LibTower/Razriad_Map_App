package com.example.razryadapi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import android.widget.Toast

class go_to_map : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map)

        val smallRoom: Button = findViewById(R.id.Small_room)
        val bigRoom: Button = findViewById(R.id.big_room)
        val secondFloor: Button = findViewById(R.id.Second_floor)
        val swapFilter: SwitchCompat = findViewById(R.id.Swap)

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

        swapFilter.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Включено", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Выключено", Toast.LENGTH_SHORT).show()
            }
        }
    }
}