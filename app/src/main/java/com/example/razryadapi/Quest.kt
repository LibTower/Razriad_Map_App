package com.example.razryadapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.razryadapi.filter.quest

class Quest : ComponentActivity() {
    private lateinit var viewPager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interactive_quest)


        viewPager = findViewById(R.id.inter_pager)
        val adapter = ViewPagerAdapter(viewPager, quest)
        viewPager.adapter = adapter
    }
}