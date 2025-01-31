package com.example.razryadapi

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.razryadapi.filter.authors
import com.example.razryadapi.filter.all_fandoms

class GoToFandoms : AppCompatActivity() {
    private lateinit var fandomButtonsContainer: LinearLayout
    private lateinit var filterEdit: EditText
    private val checkBoxes = mutableListOf<CheckBox>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_to_fandoms)

        fandomButtonsContainer = findViewById(R.id.fandomButtonsContainer)
        filterEdit = findViewById(R.id.fandomsfilterEdit)

        all_fandoms.forEach { fandom ->
            val checkBox = CheckBox(this).apply {
                text = fandom
                setTextColor(ResourcesCompat.getColor(resources, android.R.color.white, null))
                // Устанавливаем параметры для чекбокса
                val params = LinearLayout.LayoutParams(
                    (300 * resources.displayMetrics.density).toInt(),
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    topMargin = 16
                    bottomMargin = 8
                    gravity = Gravity.CENTER
                }

                layoutParams = params
                background = ResourcesCompat.getDrawable(resources, R.drawable.button_background, null)
                setPadding(18, 16, 18, 16)
            }
            checkBoxes.add(checkBox)
            fandomButtonsContainer.addView(checkBox)
        }

        filterEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterCheckBoxes(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        findViewById<ImageButton>(R.id.button_start).setOnClickListener {
            filterAuthorsAndGoToNextScreen()
        }
    }

    private fun filterCheckBoxes(query: String) {
        for (i in 0 until fandomButtonsContainer.childCount) {
            val checkBoxView = fandomButtonsContainer.getChildAt(i)
            if (checkBoxView is CheckBox) {
                checkBoxView.visibility =
                    if (checkBoxView.text.toString().lowercase().contains(query.lowercase())) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
            }
        }
    }

    private fun filterAuthorsAndGoToNextScreen() {
        val selectedFandoms = checkBoxes.filter { it.isChecked }.map { it.text.toString() }
        val filteredAuthors = authors.filter { author ->
            selectedFandoms.any { fandom -> author.fandoms?.contains(fandom) == true }
        }

        val intent = Intent(this, GoToAuthors::class.java).apply {
            putParcelableArrayListExtra("filtered_authors", ArrayList(filteredAuthors))
        }
        startActivity(intent)
    }
}