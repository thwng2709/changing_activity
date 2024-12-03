package com.example.changingactivity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Activity2 : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_2)

		val btnOK = findViewById<Button>(R.id.btn_ok)
		val edtInput = findViewById<EditText>(R.id.edit_text_url)

		val result = edtInput.text.toString()

		btnOK.setOnClickListener {
			val intent = intent
			intent.putExtra(KEY, result) // assign data into intent
			setResult(RESULT_CODE, intent) // set result and assign intent into it
			finish()
		}
	}
}