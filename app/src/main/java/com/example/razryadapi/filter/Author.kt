package com.example.razryadapi.filter

import android.os.Parcelable
import android.widget.ImageView
import kotlinx.parcelize.Parcelize

@Parcelize
data class Author(
    val nickname: String,
    val tableNumber: Int,
    val commission: Boolean,
    val swap: Boolean,
    val interactiveQuest: Boolean,
    val moneyQuest: Boolean,
    val nick_image: Int,
    val fandoms: List<String>?,
    val images: List<Int>?
) : Parcelable