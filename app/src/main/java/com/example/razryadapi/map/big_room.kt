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
import com.example.razryadapi.filter.authors

class big_room : AppCompatActivity() {

    private lateinit var tables: List<ImageButton>
    private lateinit var swapB: SwitchCompat
    private lateinit var comissionB: SwitchCompat
    private lateinit var interactiveQuestB: SwitchCompat
    private lateinit var moneyQuestB: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.big_room_activity)

        // Работа с переключателями
        swapB = findViewById(R.id.swapB)
        comissionB = findViewById(R.id.comissionB)
        interactiveQuestB = findViewById(R.id.interactiveQuestB)
        moneyQuestB = findViewById(R.id.moneyQuestB)

        // Инициализация столов
        tables = listOf(
            findViewById(R.id.table11B),
            findViewById(R.id.table12B),
            findViewById(R.id.table13B),
            findViewById(R.id.table14B),
            findViewById(R.id.table15B),
            findViewById(R.id.table16B),
            findViewById(R.id.table17B),
            findViewById(R.id.table18B),
            findViewById(R.id.table19B),
            findViewById(R.id.table20B),
            findViewById(R.id.table21B),
            findViewById(R.id.table22B),
            findViewById(R.id.table23B),
            findViewById(R.id.table24B),
            findViewById(R.id.table25B),
            findViewById(R.id.table26B),
            findViewById(R.id.table27B),
            findViewById(R.id.table28B),
            findViewById(R.id.table29B),
            findViewById(R.id.table30B),
            findViewById(R.id.table31B),
            findViewById(R.id.table32B),
            findViewById(R.id.table33B)
        )

        // Установка значений переключателей из интента
        val swapState = intent.getBooleanExtra("swapState", false)
        swapB.isChecked = swapState
        comissionB.isChecked = intent.getBooleanExtra("comissionState", false)
        interactiveQuestB.isChecked = intent.getBooleanExtra("interactiveQuestState", false)
        moneyQuestB.isChecked = intent.getBooleanExtra("moneyQuestState", false)

        // Обновление видимости при инициализации
        updateAllTableVisibilities()

        // Установка слушателей для переключателей
        val switchListener: (CompoundButton, Boolean) -> Unit = { _, _ -> updateAllTableVisibilities() }
        swapB.setOnCheckedChangeListener(switchListener)
        comissionB.setOnCheckedChangeListener(switchListener)
        interactiveQuestB.setOnCheckedChangeListener(switchListener)
        moneyQuestB.setOnCheckedChangeListener(switchListener)

        // Установка слушателей для кнопок столов
        tables.forEach { table ->
            table.setOnClickListener {
                performClickAnimation(it) {
                    val tableIndex =
                        tables.indexOf(table) + 11  // индексы начинаются с 0, номера столов начинаются с 1
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
        tables.forEach { table ->
            table.visibility = View.INVISIBLE // Сначала скрываем все столы
        }

        authors.forEach { author ->
            val tableIndex = author.tableNumber - 1

            if (tableIndex in tables.indices) {
                var allConditionsMet = true
                var anyConditionActive = false

                // Проверяем все переключатели и соответствие условий
                if (swapB.isChecked) {
                    anyConditionActive = true
                    if (!author.swap) allConditionsMet = false
                }
                if (comissionB.isChecked) {
                    anyConditionActive = true
                    if (!author.commission) allConditionsMet = false
                }
                if (interactiveQuestB.isChecked) {
                    anyConditionActive = true
                    if (!author.interactiveQuest) allConditionsMet = false
                }
                if (moneyQuestB.isChecked) {
                    anyConditionActive = true
                    if (!author.moneyQuest) allConditionsMet = false
                }

                // Если все условия соблюдены, показываем стол
                if (allConditionsMet) {
                    tables[tableIndex].visibility = View.VISIBLE
                    tables[tableIndex].alpha = 1.0f // Полная непрозрачность
                } else if (anyConditionActive) {
                    tables[tableIndex].visibility = View.VISIBLE
                    tables[tableIndex].alpha = 0.5f // Полупрозрачная для несоответствующих условий
                }
            }
        }
    }
}