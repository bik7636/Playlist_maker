package com.example.playlist_maker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val backArrow = findViewById<ImageButton>(R.id.backArrow)

        backArrow.setOnClickListener {
            val backArrowIntent = Intent(this, MainActivity::class.java)
            startActivity(backArrowIntent)
        }
    }
}