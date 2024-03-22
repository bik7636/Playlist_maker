package com.example.playlist_maker

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val backButton = findViewById<ImageButton>(R.id.backArrow)
        val shareButton = findViewById<TextView>(R.id.share_button)
        val supportButton = findViewById<TextView>(R.id.support_button)
        val agreementButton = findViewById<TextView>(R.id.agreement_button)
        val switchTheme = findViewById<Switch>(R.id.button_theme_switch)

        shareButton.setOnClickListener {
            shareApp()
        }

        supportButton.setOnClickListener {
            sendMail()
        }

        agreementButton.setOnClickListener {
            val linkOffer = resources.getString(R.string.link_offer)
            openBrowserUserAgreement(linkOffer)
        }

        backButton.setOnClickListener {
            finish()
        }

        switchTheme.setOnClickListener {

        }

        switchTheme.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            recreate()
        }
    }

    private fun shareApp() {
        val courseUrl = resources.getString(R.string.link_cours)
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, courseUrl)
        startActivity(Intent.createChooser(intent, "Выберите приложение:"))
    }

    private fun sendMail() {
        val recipientEmail = resources.getString(R.string.mail_to_send)
        val subject = resources.getString(R.string.subject_message)
        val messageBody = resources.getString(R.string.text_mail)

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, recipientEmail)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, messageBody)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            val errorMessage = resources.getString(R.string.application_missing_error)
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun openBrowserUserAgreement(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}