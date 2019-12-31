package com.example.exercies5

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.Preference
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var counterViewModel: CounterViewModel
    lateinit var sharedPreferences : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "onCreate")
        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        imageViewDislike.setOnClickListener {
            counterViewModel.plusDislike()
            textViewDislike.text = counterViewModel.dislikeCount.toString()
        }

        imageViewLike.setOnClickListener {
            counterViewModel.plusLike()
            textViewLike.text = counterViewModel.likeCount.toString()
        }
    }

    override fun onStart() {
        Log.d("MainActivity", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("MainActivity", "onResume")
        val dislike = sharedPreferences.getInt(getString(R.string.dislike), 0)
        val like = sharedPreferences.getInt(getString(R.string.like), 0)

        counterViewModel.dislikeCount = dislike
        counterViewModel.likeCount = like

        textViewDislike.text = counterViewModel.dislikeCount.toString()
        textViewLike.text = counterViewModel.likeCount.toString()

        super.onResume()
    }

    override fun onPause() {
        Log.d("MainActivity", "onPause")

        with(sharedPreferences.edit()) {
            putInt(getString(R.string.dislike), counterViewModel.dislikeCount)
            putInt(getString(R.string.like), counterViewModel.likeCount)
            commit()
        }
        super.onPause()
    }

    override fun onStop() {
        Log.d("MainActivity", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MainActivity", "onDestroy")
        super.onDestroy()
    }
}
