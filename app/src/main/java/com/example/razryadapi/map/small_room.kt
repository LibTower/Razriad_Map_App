package com.example.razryadapi.map

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.razryadapi.Adapter_and_stuff.performClickAnimation
import com.example.razryadapi.GoToAuthors
import com.example.razryadapi.R
import com.example.razryadapi.filter.Author
import com.example.razryadapi.filter.authors

class small_room : AppCompatActivity() {

    private lateinit var tables: List<ImageButton>
    private lateinit var swapS: SwitchCompat
    private lateinit var comissionS: SwitchCompat
    private lateinit var interactiveQuestS: SwitchCompat
    private lateinit var moneyQuestS: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.small_room_activity)

        // Работа с переключателями
        swapS = findViewById(R.id.swapS)
        comissionS = findViewById(R.id.comissionS)
        interactiveQuestS = findViewById(R.id.interactiveQuestS)
        moneyQuestS = findViewById(R.id.moneyQuestS)

        tables = listOf(
            findViewById(R.id.table1S),
            findViewById(R.id.table2S),
            findViewById(R.id.table3S),
            findViewById(R.id.table4S),
            findViewById(R.id.table5S),
            findViewById(R.id.table6S),
            findViewById(R.id.table7S),
            findViewById(R.id.table8S),
            findViewById(R.id.table9S),
            findViewById(R.id.table10S)
        )

        // Установка значений переключателей из интента
        val swapState = intent.getBooleanExtra("swapState", false)
        swapS.isChecked = swapState
        comissionS.isChecked = intent.getBooleanExtra("comissionState", false)
        interactiveQuestS.isChecked = intent.getBooleanExtra("interactiveQuestState", false)
        moneyQuestS.isChecked = intent.getBooleanExtra("moneyQuestState", false)


        updateAllTableVisibilities()

        val switchListener: (CompoundButton, Boolean) -> Unit = { _, _ -> updateAllTableVisibilities() }
        swapS.setOnCheckedChangeListener(switchListener)
        comissionS.setOnCheckedChangeListener(switchListener)
        interactiveQuestS.setOnCheckedChangeListener(switchListener)
        moneyQuestS.setOnCheckedChangeListener(switchListener)
        tables.forEach { table ->
            table.setOnClickListener {
                performClickAnimation(it) {
                    val tableIndex =
                        tables.indexOf(table) + 1
                    navigateToAuthors(tableIndex)
                }
            }
        }
    }

    private fun navigateToAuthors(tableNumber: Int) {
        val intent = Intent(this, GoToAuthors::class.java)
        intent.putExtra("tableNumber", tableNumber)
        startActivity(intent)
    }

    private fun updateAllTableVisibilities() {
        // Получаем номера видимых столов
        val visibleTableNumbers = Author.getVisibleTableNumbers(
            authors,
            if (swapS.isChecked) true else null,
            if (comissionS.isChecked) true else null,
            if (interactiveQuestS.isChecked) true else null,
            if (moneyQuestS.isChecked) true else null
        ).toSet() // Преобразуем в множество для быстрого поиска

        // Обновляем видимость и непрозрачность столов на основе множества
        tables.forEachIndexed { index, table ->
            val tableNumber = index + 1 // Номера столов начинаются с 1
            if (visibleTableNumbers.contains(tableNumber)) {
                table.visibility = View.VISIBLE
                table.alpha = 1.0f // Полная непрозрачность для видимых столов
            } else {
                table.visibility = View.VISIBLE // Все столы будут видимы
                table.alpha = 0.5f // Полупрозрачные для несоответствующих условий
            }
        }
    }
}