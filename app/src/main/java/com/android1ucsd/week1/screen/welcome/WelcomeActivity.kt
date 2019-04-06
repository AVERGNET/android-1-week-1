package com.android1ucsd.week1.screen.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.android1ucsd.week1.R

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        title = "Welcome"

        val button: Button = findViewById(R.id.aw_button)
        val editText: EditText = findViewById(R.id.aw_edit_text)

        button.setOnClickListener {
            //TODO | add code to pass the text of the edit text to the ListActivity through an intent. If you don't know
            //TODO | how to write the code to create an intent in Kotlin, do it in a Java file and copy paste it back
            //TODO | into this file. Android Studio will automatically convert the Java code to Kotlin.
        }
    }

}