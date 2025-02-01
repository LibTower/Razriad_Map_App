// Обновленный класс Author
package com.example.razryadapi.filter

import android.os.Parcelable
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
) : Parcelable {
    companion object {
        fun getVisibleTableNumbers(authors: List<Author>, swap: Boolean? = null, commission: Boolean? = null, interactiveQuest: Boolean? = null, moneyQuest: Boolean? = null): List<Int> {
            return authors.filter { author ->
                (swap == null || author.swap == swap) &&
                        (commission == null || author.commission == commission) &&
                        (interactiveQuest == null || author.interactiveQuest == interactiveQuest) &&
                        (moneyQuest == null || author.moneyQuest == moneyQuest)
            }.map { author -> author.tableNumber }
        }
    }
}