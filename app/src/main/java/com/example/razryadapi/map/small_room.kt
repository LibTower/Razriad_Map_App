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
        tables.forEach { table ->
            table.visibility = View.INVISIBLE
        }

        authors.forEach { author ->
            val tableIndex = author.tableNumber - 1

            if (tableIndex in tables.indices) {
                var allConditionsMet = true
                var anyConditionActive = false

                // Проверяем все переключатели и соответствие условий
                if (swapS.isChecked) {
                    anyConditionActive = true
                    if (!author.swap) allConditionsMet = false
                }
                if (comissionS.isChecked) {
                    anyConditionActive = true
                    if (!author.commission) allConditionsMet = false
                }
                if (interactiveQuestS.isChecked) {
                    anyConditionActive = true
                    if (!author.interactiveQuest) allConditionsMet = false
                }
                if (moneyQuestS.isChecked) {
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