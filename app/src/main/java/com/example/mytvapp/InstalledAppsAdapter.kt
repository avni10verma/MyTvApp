package com.example.mytvapp

import android.content.pm.ApplicationInfo
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class InstalledAppsAdapter(private val appsList: List<ApplicationInfo>,private val focusInterface: FocusInterface) : RecyclerView.Adapter<InstalledAppsAdapter.AppViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_installed_app, parent, false)
        return AppViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val appInfo = appsList[position]
        //holder.appName.text = appInfo.loadLabel(holder.itemView.context.packageManager).toString()
        holder.appIcon.setImageDrawable(appInfo.loadIcon(holder.itemView.context.packageManager))


        holder.itemView.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.animate().scaleX(1.2f).scaleY(1.2f).setDuration(200).start()
            } else {
                view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200).start()
            }
        }

        holder.itemView.setOnKeyListener { view, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                when (keyCode) {
                    KeyEvent.KEYCODE_DPAD_LEFT -> {
                        if (position == 0|| (position+1)%5==1) {
                            true // Consume the event and prevent focus from moving left
                        } else {
                            false
                        }
                    }
                    KeyEvent.KEYCODE_DPAD_RIGHT -> {
                        if (position == appsList.size - 1|| (position+1)%5==0) {
                            true // Consume the event and prevent focus from moving right
                        } else {
                            false
                        }
                    }
                  // KeyEvent.KEYCODE_DPAD_UP -> {
                    //   if(position<5)
                     //  focusInterface.onFocusUp(view)
                      // true
                  // }
                   else -> false

                }
            } else {
                false
            }
        }


        holder.itemView.setOnClickListener {
            // Handle click event to open the app or perform any other action
            Toast.makeText(holder.itemView.context, "Clicked on ${appInfo.loadLabel(holder.itemView.context.packageManager)}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = appsList.size


    class AppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val appName: TextView = itemView.findViewById(R.id.app_name)
        val appIcon: ImageView = itemView.findViewById(R.id.app_icon)
    }
}
