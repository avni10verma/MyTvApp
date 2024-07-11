package com.example.mytvapp

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class InstalledAppsFragment : Fragment() , FocusInterface {


    private lateinit var recyclerView: RecyclerView
    private lateinit var installedAppsAdapter: InstalledAppsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_installed_apps, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_installed_apps)
        recyclerView.layoutManager = GridLayoutManager(context,5)
        installedAppsAdapter = InstalledAppsAdapter(requireContext().packageManager.getInstalledApplications(0), this)
        recyclerView.adapter = installedAppsAdapter

        view.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                when (keyCode) {
                    KeyEvent.KEYCODE_DPAD_DOWN -> {
                        recyclerView.post{
                            recyclerView.findViewHolderForAdapterPosition(0)?.itemView?.requestFocus()
                        }
                        true
                    }
                    else -> false
                }
            } else {
                false
            }
        }

        return view
    }

    override fun onFocusUp(view: View) {
        // Navigate to the home button
        requireActivity().findViewById<View>(R.id.home).requestFocus()
    }
    override fun onFocusDown(view: View) {}
    override fun onFocusLeft(view: View) {}
    override fun onFocusRight(view: View) {}
}