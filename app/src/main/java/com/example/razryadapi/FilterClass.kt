package com.example.razryadapi

    data class Author(
        val nickname: String,
        val tableNumber: Int,
        val commission: Boolean,
        val swap: Boolean,
        val interactiveQuest: Boolean,
        val moneyQuest: Boolean
    )
    fun filterAuthors(authors: List<Author>, commission: Boolean? = null, swap: Boolean? = null, interactiveQuest: Boolean? = null, moneyQuest: Boolean? = null): List<Author> {
        return authors.filter { author ->
            (commission == null || author.commission == commission) &&
                    (swap == null || author.swap == swap) &&
                    (interactiveQuest == null || author.interactiveQuest == interactiveQuest) &&
                    (moneyQuest == null || author.moneyQuest == moneyQuest)
        }
    }
    fun getTable(authors: List<Author>): Set<Int> {
       return authors.map { it.tableNumber }.toSet()
    }