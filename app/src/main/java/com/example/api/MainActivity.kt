package com.example.api

import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.api.databinding.ActivityMainBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.fetch.setOnClickListener {

            //makeRequest()

            makeRequestOKHttp()
        }

    }


    // Request by using OKHTTP library

    private fun makeRequestOKHttp() {

        val request = Request.Builder().url("https://v2.jokeapi.dev/joke/Any").build()
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "onFailure: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    binding.getText.text = response.body?.string()
                }
            }

        })
    }


//    private fun makeRequest() {
//
//        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
//        StrictMode.setThreadPolicy(policy)
//
//        // TO get dataa from URL and put it into Hello World!
//
//        val url = URL("https://v2.jokeapi.dev/joke/Any")
//        val connection = url.openConnection()
//        val inputStream = connection.getInputStream()
//        val inputStreamReader = InputStreamReader(inputStream)
//        val result = inputStreamReader.readText()
//        binding.getText.text = result
//
//    }

}