package com.example.razryadapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class ViewPagerAdapter(
    private val viewPager: ViewPager2,
    private val imageResIds: List<Int>
) : RecyclerView.Adapter<ViewPagerAdapter.PagerVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pager, parent, false)
        return PagerVH(view)
    }

    override fun getItemCount(): Int = imageResIds.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        val customImageView = holder.itemView.findViewById<CustomImageView>(R.id.monliroMerch)
        customImageView.setImageResource(imageResIds[position])
    }

    class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)
}