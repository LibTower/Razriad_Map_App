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
    private lateinit var swap: SwitchCompat
    private lateinit var comission: SwitchCompat
    private lateinit var interactiveQuest: SwitchCompat
    private lateinit var moneyQuest: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_activity)

        mapImage = findViewById(R.id.map_image)
        mapSwitch = findViewById(R.id.map_switch)
        bigRoom = findViewById(R.id.Big_room)
        smallRoom = findViewById(R.id.Small_room)
        secondFloor = findViewById(R.id.Second_floor)
        swap = findViewById(R.id.swap)
        comission = findViewById(R.id.comission)
        interactiveQuest = findViewById(R.id.interactiveQuest)
        moneyQuest = findViewById(R.id.moneyQuest)

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

        swap.setOnCheckedChangeListener { _, _ ->
            updateAllTableVisibilities()
        }

        comission.setOnCheckedChangeListener { _, _ ->
            updateAllTableVisibilities()
        }

        interactiveQuest.setOnCheckedChangeListener { _, _ ->
            updateAllTableVisibilities()
        }

        moneyQuest.setOnCheckedChangeListener { _, _ ->
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

                val visibleTableNumbers = Author.getVisibleTableNumbers(
                    authors,
                    if (swap.isChecked) true else null,
                    if (comission.isChecked) true else null,
                    if (interactiveQuest.isChecked) true else null,
                    if (moneyQuest.isChecked) true else null
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

