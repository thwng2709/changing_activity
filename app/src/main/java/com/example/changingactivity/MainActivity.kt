package com.example.changingactivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

const val RESULT_CODE = 111123114
const val KEY = "Key"

class MainActivity : AppCompatActivity() {

	private lateinit var btnInput: Button
	private lateinit var btnOpen: Button
	private lateinit var tvUrl: TextView

	@SuppressLint("QueryPermissionsNeeded")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		btnInput = findViewById(R.id.btn_input_URL)
		btnOpen = findViewById(R.id.btn_open_url)
		tvUrl = findViewById(R.id.tv_url)



		btnInput.setOnClickListener {
			val intent = Intent(this@MainActivity, Activity2::class.java)


			// Receive result from Activity2
			val getResult = registerForActivityResult(
				ActivityResultContracts.StartActivityForResult()
			) {
				if (it.resultCode == RESULT_CODE) {
					val message = it.data!!.getStringExtra(KEY)
					tvUrl.text = message
				}
			}
			getResult.launch(intent)
		}

		//Open url - using implicit intent
		btnOpen.setOnClickListener {
			val intent = Intent()
			intent.setAction(Intent.ACTION_VIEW)

			if (intent.resolveActivity(packageManager) != null) {
				startActivity(intent)
			}
		}
	}
}