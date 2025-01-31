package com.example.razryadapi.map

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.razryadapi.R
import com.example.razryadapi.filter.Author
import com.example.razryadapi.filter.authors

class Loft_Map : AppCompatActivity() {

    private lateinit var mapImage: ImageView
    private lateinit var mapSwitch: SwitchCompat
    private lateinit var bigRoom: Button
    private lateinit var smallRoom: Button
    private lateinit var secondFloor: Button
    private lateinit var tables: List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_activity)

        mapImage = findViewById(R.id.map_image)
        mapSwitch = findViewById(R.id.map_switch)
        bigRoom = findViewById(R.id.Big_room)
        smallRoom = findViewById(R.id.Small_room)
        secondFloor = findViewById(R.id.Second_floor)

        // Инициализация списка изображений столов
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

        // Изначально скрываем все таблицы, устанавливаем прозрачность 100%
        resetTableVisibility()

        // Устанавливаем слушатели изменений для переключателей
        mapSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showAllTables()
                mapImage.setImageResource(R.drawable.full_map) // Устанавливаем новое изображение карты
            } else {
                resetTableVisibility()
                mapImage.setImageResource(R.drawable.zone) // Возвращаем исходное изображение карты
            }
        }

        findViewById<SwitchCompat>(R.id.swap).setOnCheckedChangeListener { _, _ ->
            updateAllTableVisibilities()
        }

        findViewById<SwitchCompat>(R.id.comission).setOnCheckedChangeListener { _, _ ->
            updateAllTableVisibilities()
        }

        findViewById<SwitchCompat>(R.id.interactiveQuest).setOnCheckedChangeListener { _, _ ->
            updateAllTableVisibilities()
        }

        findViewById<SwitchCompat>(R.id.moneyQuest).setOnCheckedChangeListener { _, _ ->
            updateAllTableVisibilities()
        }
        smallRoom.setOnClickListener {
            val intent = Intent(this, small_room::class.java).apply {
                putExtra("swapState", findViewById<SwitchCompat>(R.id.swap).isChecked)
                putExtra("comissionState", findViewById<SwitchCompat>(R.id.comission).isChecked)
                putExtra("interactiveQuestState", findViewById<SwitchCompat>(R.id.interactiveQuest).isChecked)
                putExtra("moneyQuestState", findViewById<SwitchCompat>(R.id.moneyQuest).isChecked)
            }
            startActivity(intent)
        }

        bigRoom.setOnClickListener {
            val intent = Intent(this, big_room::class.java).apply {
                putExtra("swapState", findViewById<SwitchCompat>(R.id.swap).isChecked)
                putExtra("comissionState", findViewById<SwitchCompat>(R.id.comission).isChecked)
                putExtra("interactiveQuestState", findViewById<SwitchCompat>(R.id.interactiveQuest).isChecked)
                putExtra("moneyQuestState", findViewById<SwitchCompat>(R.id.moneyQuest).isChecked)
            }
            startActivity(intent)
        }
        
    }

    private fun resetTableVisibility() {
        tables.forEach { table ->
            table.visibility = View.INVISIBLE
        }
    }

    private fun showAllTables() {
        authors.forEach { author ->
            displayTableForAuthor(author)
        }
    }

    private fun displayTableForAuthor(author: Author) {
        val tableIndex = author.tableNumber - 1
        if (tableIndex in tables.indices) {
            tables[tableIndex].visibility = View.VISIBLE
        }
    }

    private fun updateAllTableVisibilities() {
        // Если mapSwitch установлен на "zoone", скрываем все столы
        if (!mapSwitch.isChecked) {
            resetTableVisibility()
            return
        }

        tables.forEach { table ->
            table.visibility = View.INVISIBLE // Сначала скрываем все столы
        }

        authors.forEach { author ->
            val tableIndex = author.tableNumber - 1
            if (tableIndex in tables.indices) {
                var allConditionsMet = true
                var anyConditionActive = false

                // Проверяем все переключатели и соответствие условий
                if (findViewById<SwitchCompat>(R.id.swap).isChecked) {
                    anyConditionActive = true
                    if (!author.swap) allConditionsMet = false
                }
                if (findViewById<SwitchCompat>(R.id.comission).isChecked) {
                    anyConditionActive = true
                    if (!author.commission) allConditionsMet = false
                }
                if (findViewById<SwitchCompat>(R.id.interactiveQuest).isChecked) {
                    anyConditionActive = true
                    if (!author.interactiveQuest) allConditionsMet = false
                }
                if (findViewById<SwitchCompat>(R.id.moneyQuest).isChecked) {
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