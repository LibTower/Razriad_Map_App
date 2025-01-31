package com.example.razryadapi

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.razryadapi.Adapter_and_stuff.performClickAnimation
import com.example.razryadapi.filter.Author
import com.example.razryadapi.filter.authors

class GoToAuthors : AppCompatActivity() {

    private lateinit var buttonContainer: LinearLayout
    private val authorButtons = mutableListOf<Button>() // Список для хранения кнопок авторов

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authors)

        val editText = findViewById<EditText>(R.id.filterEdit)
        buttonContainer = findViewById(R.id.buttonContainer) // Ваш контейнер для кнопок

        // Получаем номер стола из Intent
        val tableNumber = intent.getIntExtra("tableNumber", -1)

        // Получаем отфильтрованных авторов из GoToFandoms
        val filteredAuthors = intent.getParcelableArrayListExtra<Author>("filtered_authors") ?: authors

        // Если номер стола валиден, добавляем авторов по этому столу
        val authorsToDisplay: List<Author>
        authorsToDisplay = if (tableNumber != -1) {
            authors.filter { it.tableNumber == tableNumber }
        } else {
            filteredAuthors
        }

        authorsToDisplay.forEach { author ->
            addAuthorButton(author)
        }

        // Добавляем TextWatcher для EditText
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Получаем введенный текст и фильтруем кнопки
                val inputText = s.toString().lowercase()
                filterButtons(inputText)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun addAuthorButton(author: Author) {
        val button = Button(this)
        button.text = author.nickname
        button.setBackgroundResource(R.drawable.button_background)
        button.setTextColor(resources.getColor(R.color.white, null))

        val params = LinearLayout.LayoutParams(
            (300 * resources.displayMetrics.density).toInt(),
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            topMargin = 16
            bottomMargin = 8
            gravity = Gravity.CENTER
        }
        button.layoutParams = params

        button.setOnClickListener {
            performClickAnimation(it) {
                val intent = Intent(this, Author_Activity::class.java)
                intent.putExtra("AUTHOR_NICKNAME", author.nickname)
                startActivity(intent)
            }
        }

        authorButtons.add(button)
        buttonContainer.gravity = Gravity.CENTER
        buttonContainer.addView(button)
    }

    private fun filterButtons(query: String) {
        for (button in authorButtons) {
            button.visibility = if (button.text.toString().lowercase().contains(query)) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}