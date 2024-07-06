package com.example.mytvapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, MainFragment())
            }
        }

        val searchButton: View = findViewById(R.id.search_button)
        searchButton.setOnClickListener {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, SearchFragment())
                addToBackStack(null)
            }
        }
    }
}
//this is a comment
