package com.example.mytvapp

import SearchFragment
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.util.trace
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() , FocusInterface {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, MainFragment())
            }
        }

        val userProfile: View = findViewById(R.id.user_profile)
        val homeButton: View = findViewById(R.id.home)
        val searchB: View = findViewById(R.id.search_button)
        val settingsButton: View = findViewById(R.id.settings_button)

        userProfile.setOnKeyListener(focusKeyListener)
        homeButton.setOnKeyListener(focusKeyListener)
        searchB.setOnKeyListener(focusKeyListener)
        settingsButton.setOnKeyListener(focusKeyListener)


        homeButton.requestFocus()


        val searchButton: View = findViewById(R.id.search_button)
        searchButton.setOnClickListener {
            Log.d("searchf", "Opened")
            supportFragmentManager.commit {
                replace(R.id.fragment_container, SearchFragment())
                addToBackStack(null)
            }
        }

        val apps: View = findViewById(R.id.apps)
        apps.setOnClickListener {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, InstalledAppsFragment())
                addToBackStack(null)
            }
        }

        fun navigateToHomeFragment(){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,MainFragment())
                .addToBackStack(null)
                .commit()
        }

        homeButton.setOnKeyListener { view, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
                navigateToHomeFragment()
                true
            } else {
                false
            }
        }


    }

    private val focusKeyListener = View.OnKeyListener { view, keyCode, event ->
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (view.id) {
                R.id.user_profile -> {
                    when (keyCode) {
                        KeyEvent.KEYCODE_DPAD_LEFT -> {
                            // Do nothing
                            true
                        }

                        KeyEvent.KEYCODE_DPAD_DOWN -> {
                            // Do nothing
                            true
                        }

                        KeyEvent.KEYCODE_DPAD_RIGHT -> {
                            findViewById<View>(R.id.home).requestFocus()
                            true
                        }

                        else -> false
                    }
                }

                R.id.settings_button -> {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                        true
                    } else {
                        false
                    }
                }

                else -> false
            }
        } else {
            true
        }
    }


    override fun onFocusUp(view: View) {
        when (view.id) {
            R.id.search_button -> findViewById<View>(R.id.home).requestFocus()
            R.id.recycler_view_installed_apps ->findViewById<View>(R.id.home).requestFocus()
        }
    }

    override fun onFocusDown(view: View) {}
    override fun onFocusLeft(view: View) {}
    override fun onFocusRight(view: View) {}
}




