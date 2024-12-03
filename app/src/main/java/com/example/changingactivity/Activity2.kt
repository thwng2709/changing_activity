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
		val edtUrl = findViewById<EditText>(R.id.edit_text_url)

		btnOK.setOnClickListener{
			val result = edtUrl.text.toString()
			intent.also {
				it.putExtra(KEY, result)
				setResult(RESULT_CODE, it)
				finish()
			}
		}

	}
}