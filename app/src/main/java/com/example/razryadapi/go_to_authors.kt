package com.example.razryadapi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class go_to_authors : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authors)


        val iris: Button = findViewById(R.id.iris)
        val demi: Button = findViewById(R.id.demi)
        val brit: Button = findViewById(R.id.brit)
        val kayata: Button = findViewById(R.id.kayata)

        iris.text = getString(R.string.iris)
        demi.text = getString(R.string.demi)
        brit.text = getString(R.string.brit)
        kayata.text = getString(R.string.kayata)

        iris.setOnClickListener {
            val intent = Intent(this, Iris::class.java)
            startActivity(intent)
        }

        demi.setOnClickListener {
            val intent = Intent(this, Demi::class.java)
            startActivity(intent)
        }
        brit.setOnClickListener {
            val intent = Intent(this, Brit::class.java)
            startActivity(intent)
        }
        kayata.setOnClickListener {
            val intent = Intent(this, Kayata::class.java)
            startActivity(intent)
        }

    }
}