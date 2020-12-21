package com.example.aadpracticeproject

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

const val WORKER_KEY = "saucecode"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         val workManager = WorkManager.getInstance(this)
        val button = findViewById<Button>(R.id.btn)

        button.setOnClickListener {

            val request = OneTimeWorkRequestBuilder<BlurWorker>()
                    .setInputData(createInputDataForUri("SUCCESS "))
                    .build()

            workManager.enqueue(request)

        }


    }

    private fun createInputDataForUri(message:String): Data {
        val builder = Data.Builder()
       message?.let {
            builder.putString(WORKER_KEY,it)
        }
        return builder.build()
    }



}