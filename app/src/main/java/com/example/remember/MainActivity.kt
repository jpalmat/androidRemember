package com.example.remember

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.remember.`interface`.TodoListener
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), TodoListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        createNotificationChannel()

        val notification = NotificationCompat.Builder(this, "channelID")
            .setContentTitle("Bla")
            .setContentText("ble")
            .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationmanager = NotificationManagerCompat.from(this)

        btnNotification.setOnClickListener {
            notificationmanager.notify(0, notification)
        }

        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, firstFragment)
            commit()
        }

        btnFragment1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment, firstFragment)
                commit()
            }
        }

        btnFragment2.setOnClickListener {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment, secondFragment)
                    commit()
                }

        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun todoFrag(text: String, isCheck: Boolean) {
        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply {
            putString("name", text)
            putBoolean("isCheck", isCheck)
            apply()
        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, SecondFragment())
            commit()
        }
    }

    /**
     * notification
     * https://www.youtube.com/watch?v=urn355_ymNA
     */
    fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val channel = NotificationChannel("channelID", "channelName", NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}