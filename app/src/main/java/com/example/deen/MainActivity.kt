package com.example.deen

import android.content.ClipData
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val copyButton = findViewById<Button>(R.id.copyButton)
        val encryptButton = findViewById<Button>(R.id.encryptButton)
        val decryptButton = findViewById<Button>(R.id.decryptButton)
        val inputText = findViewById<EditText>(R.id.inputText)
        val outputText = findViewById<TextView>(R.id.convertedText)
        fun encrypt(s: String, key: Int): String {
            val offset = key % 26
            if (offset == 0) return s
            var d: Char
            val chars = CharArray(s.length)
            for ((index, c) in s.withIndex()) {
                if (c in 'A'..'Z') {
                    d = c + offset
                    if (d > 'Z') d -= 26
                }
                else if (c in 'a'..'z') {
                    d = c + offset
                    if (d > 'z') d -= 26
                }
                else
                    d = c
                chars[index] = d
            }
            return chars.joinToString("")
        }

        fun decrypt(s: String, key: Int): String {
            return encrypt(s, 26 - key)
        }

        copyButton.setOnClickListener {
            val textToCopy = outputText.text
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
            val clipData = ClipData.newPlainText("text", textToCopy)
            clipboardManager.setPrimaryClip(clipData)
        }
        encryptButton.setOnClickListener {
            var string = inputText.text.toString()
            string = encrypt(string,7)
            outputText.text = string
        }
        decryptButton.setOnClickListener {
            var string = inputText.text.toString()
            string = decrypt(string,7)
            outputText.text = string
        }

    }
}