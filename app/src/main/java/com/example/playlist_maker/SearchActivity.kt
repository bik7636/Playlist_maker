package com.example.playlist_maker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView

class SearchActivity : AppCompatActivity() {
    private var inputText: String? = null
    private lateinit var inputEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val backButton = findViewById<ImageView>(R.id.backArrow)
        inputEditText = findViewById(R.id.inputEditText)
        val clearButton = findViewById<ImageView>(R.id.clearIcon)

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                inputText = s.toString()
                clearButton.visibility = if (s.isNullOrEmpty()) View.GONE else View.VISIBLE
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        inputEditText.addTextChangedListener(textWatcher)

        backButton.setOnClickListener {
            finish()
        }

        clearButton.setOnClickListener {
            inputEditText.setText("")

            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            inputMethodManager?.hideSoftInputFromWindow(inputEditText.windowToken, 0)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TEXT_VALUE, inputText)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        inputText = savedInstanceState.getString(TEXT_VALUE)
        inputEditText.setText(inputText)
    }

    companion object {
        private const val TEXT_VALUE = "TEXT_VALUE"
    }
}