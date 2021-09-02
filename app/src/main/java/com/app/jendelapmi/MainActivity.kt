package com.app.jendelapmi

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.app.jendelapmi.fragments.*
import com.app.slider.PreferenceHelper.customPreference
import com.app.slider.PreferenceHelper.password
import com.app.slider.PreferenceHelper.tanggalmobileunit
import com.app.slider.PreferenceHelper.userEmail
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // define menu bottomNavigation
        val menu: Menu = bottomNavigation.menu
        // set item for selectedMenu
        selectedMenu(menu.getItem(0))
        // set when bottomNavigation clicked
        bottomNavigation.setOnNavigationItemSelectedListener {
            item: MenuItem -> selectedMenu(item)
            false
        }
        // check latest tanggalmobileunit from shared preference
        val prefs = customPreference(this, "userdata")
        if (prefs.tanggalmobileunit != "") {
            // split date string to array
            val rawDate = prefs.tanggalmobileunit!!.split("-").toTypedArray()
            // create calendar instance
            val calendar = Calendar.getInstance()
            // set calendar with date value
            calendar.set(Calendar.YEAR, rawDate[0].toInt())
            calendar.set(Calendar.MONTH, rawDate[1].toInt() - 1)
            calendar.set(Calendar.DAY_OF_MONTH, rawDate[2].toInt())
            calendar.set(Calendar.HOUR, 7)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.AM_PM, Calendar.AM)
            // create notification channel
            createNotificationChannel()
            // create intent
            val intent = Intent(this, JadwalDonorNotificationBroadcast::class.java )
            // create pending intent to get broadcast
            val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            // create alarm manager to get notification based on date
            val alarmManager = this.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
            alarmManager?.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        }
    }

    /**
     * function selectedMenu
     *
     * handle when user checked or clicked menu item
     * and show fragment.
     *
     */
    private fun selectedMenu(item: MenuItem){
        item.isChecked = true
        val prefs = customPreference(this, "userdata")
        when(item.itemId){
            R.id.homeMenu -> selectedFragment(HomeFragment.getInstance())
            R.id.UDDMenu -> selectedFragment(UDDFragment.getInstance())
            R.id.NotifikasiMenu -> selectedFragment(NotifikasiFragment.getInstance())
            // check if user has logged in or not
            R.id.userMenu ->  if (prefs.userEmail == "" && prefs.password == "") {
                // not logged in, set fragment to Login Fragment
                selectedFragment(LoginFragment.getInstance())
            } else {
                // logged in, set fragment to ProfileFragment
                selectedFragment(ProfileFragment.getInstance())
            }
        }
    }

    /**
     * function selectedFragment
     *
     * handle showing fragment by clicked menu item
     * from bottomNavigation.
     */
    private fun selectedFragment(fragment: Fragment) {
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.rootFragment, fragment)
        transaction.commit()
    }

    /**
     * function createNotificationChannel
     *
     * create notification channel for show the notification
     * notification channel id must SAME with
     * NotificationBroadcast
     */
    private fun createNotificationChannel() {
        val notificationName: CharSequence = "JendelaPMIChannel"
        val notificationDescription = "Channel for JendelaPMI"
        val importance: Int = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("com.app.jendelapmi.notification", notificationName, importance)
        channel.description = notificationDescription
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}