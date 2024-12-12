package com.example.changingactivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

const val RESULT_CODE = 112543
const val KEY = "Key"

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val btnOpenUrl = findViewById<Button>(R.id.btn_open_url)
		val btnInputUrl = findViewById<Button>(R.id.btn_input_URL)
		val tvShowUrl = findViewById<TextView>(R.id.tv_show_url)

		val getResult = registerForActivityResult(
			ActivityResultContracts.StartActivityForResult()
		) {
			if (it.resultCode == RESULT_CODE) {
				val message = it.data!!.getStringExtra(KEY)
				tvShowUrl.text = message
			}
		}

		btnInputUrl.setOnClickListener {
			val intent = Intent(this@MainActivity, Activity2::class.java)
			getResult.launch(intent)
		}

		btnOpenUrl.setOnClickListener {
			val result = tvShowUrl.text.toString()
			if (result.isBlank()) {
				Toast.makeText(this, "Uri unavailable!", Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}

			val uri = Uri.parse(result)
			if (uri.scheme.isNullOrEmpty() || uri.host.isNullOrEmpty()) {
				Toast.makeText(this, "Invalid URL!", Toast.LENGTH_SHORT).show()
			} else {
				val intent = Intent(Intent.ACTION_VIEW, uri)
				try {
					startActivity(intent)
				} catch (e: Exception) {
					Toast.makeText(this, "Unable to open URL!", Toast.LENGTH_SHORT).show()
				}
			}
		}
	}
}