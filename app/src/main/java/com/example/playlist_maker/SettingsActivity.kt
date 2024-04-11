package com.example.playlist_maker

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val backButton = findViewById<ImageView>(R.id.backArrow)
        val shareButton = findViewById<TextView>(R.id.share_button)
        val supportButton = findViewById<TextView>(R.id.support_button)
        val agreementButton = findViewById<TextView>(R.id.agreement_button)
        val switchTheme = findViewById<Switch>(R.id.button_theme_switch)

        shareButton.setOnClickListener {
            val linkAdress = getString(R.string.link_cours)
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, linkAdress)
            }

            val shareChooser = Intent.createChooser(shareIntent, getString(R.string.send))

            try {
                startActivity(shareChooser)
            } catch (_: ActivityNotFoundException) {
                Toast.makeText(
                    this@SettingsActivity,
                    getString(R.string.application_missing_error),
                    Toast.LENGTH_LONG
                ).show()
            }
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

        /* это для себя, в следующиий раз удалю
         switchTheme.setOnCheckedChangeListener { buttonView, isChecked ->

             if (isChecked) {
                 AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
             } else {
                 AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
             }
             recreate()
         } */
    }

    private fun sendMail() {
        val recipientEmail = resources.getString(R.string.mail_to_send)
        val subject = resources.getString(R.string.subject_message)
        val messageBody = resources.getString(R.string.text_mail)

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_EMAIL, recipientEmail)
        intent.putExtra(Intent.EXTRA_TEXT, messageBody)

        try {
            startActivity(intent)
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(
                this@SettingsActivity,
                getString(R.string.application_missing_error),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun openBrowserUserAgreement(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}