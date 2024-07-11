package com.example.mytvapp

import android.view.View

interface FocusInterface {
    fun onFocusUp(view: View)
    fun onFocusDown(view: View)
    fun onFocusLeft(view: View)
    fun onFocusRight(view: View)
}