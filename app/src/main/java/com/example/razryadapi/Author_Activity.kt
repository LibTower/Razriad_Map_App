package com.example.razryadapi

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.razryadapi.filter.authors

class Author_Activity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var nickImageView: ImageView
    private lateinit var tables: List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author)

        val nickname = intent.getStringExtra("AUTHOR_NICKNAME")
        val author = authors.find { it.nickname == nickname }

        viewPager = findViewById(R.id.author_pager)
        nickImageView = findViewById(R.id.nick)

        tables = listOf(
            findViewById(R.id.table1),
            findViewById(R.id.table2),
            findViewById(R.id.table3),
            findViewById(R.id.table4),
            findViewById(R.id.table5),
            findViewById(R.id.table6),
            findViewById(R.id.table7),
            findViewById(R.id.table8),
            findViewById(R.id.table9),
            findViewById(R.id.table10),
            findViewById(R.id.table11),
            findViewById(R.id.table12),
            findViewById(R.id.table13),
            findViewById(R.id.table14),
            findViewById(R.id.table15),
            findViewById(R.id.table16),
            findViewById(R.id.table17),
            findViewById(R.id.table18),
            findViewById(R.id.table19),
            findViewById(R.id.table20),
            findViewById(R.id.table21),
            findViewById(R.id.table22),
            findViewById(R.id.table23),
            findViewById(R.id.table24),
            findViewById(R.id.table25),
            findViewById(R.id.table26),
            findViewById(R.id.table27),
            findViewById(R.id.table28),
            findViewById(R.id.table29),
            findViewById(R.id.table30),
            findViewById(R.id.table31),
            findViewById(R.id.table32),
            findViewById(R.id.table33)
        )

        author?.let {
            nickImageView.setImageResource(it.nick_image)
            it.images?.let { imageResources ->
                adapter = ViewPagerAdapter(viewPager, imageResources)
                viewPager.adapter = adapter
            }

            val tableNumber = it.tableNumber
            updateTableVisibility(tableNumber)
        }
    }

    private fun updateTableVisibility(selectedTableNumber: Int) {
        tables.forEach { tableImage ->
            if (tableImage.id == getTableIdByNumber(selectedTableNumber)) {
                tableImage.alpha = 1.0f
            } else {
                tableImage.alpha = 0.3f
            }
        }
    }

    private fun getTableIdByNumber(tableNumber: Int): Int {
        return when (tableNumber) {
            1 -> R.id.table1
            2 -> R.id.table2
            3 -> R.id.table3
            4 -> R.id.table4
            5 -> R.id.table5
            6 -> R.id.table6
            7 -> R.id.table7
            8 -> R.id.table8
            9 -> R.id.table9
            10 -> R.id.table10
            11 -> R.id.table11
            12 -> R.id.table12
            13 -> R.id.table13
            14 -> R.id.table14
            15 -> R.id.table15
            16 -> R.id.table16
            17 -> R.id.table17
            18 -> R.id.table18
            19 -> R.id.table19
            20 -> R.id.table20
            21 -> R.id.table21
            22 -> R.id.table22
            23 -> R.id.table23
            24 -> R.id.table24
            25 -> R.id.table25
            26 -> R.id.table26
            27 -> R.id.table27
            28 -> R.id.table28
            29 -> R.id.table29
            30 -> R.id.table30
            31 -> R.id.table31
            32 -> R.id.table32
            33 -> R.id.table33
            else -> R.id.table1
        }
    }
}