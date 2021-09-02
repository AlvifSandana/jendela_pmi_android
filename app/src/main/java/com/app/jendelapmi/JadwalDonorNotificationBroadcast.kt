package com.app.jendelapmi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class JadwalDonorNotificationBroadcast : BroadcastReceiver() {
    private val channelID = "com.app.jendelapmi.notification"

    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            val builder: NotificationCompat.Builder = NotificationCompat.Builder(context!!, channelID)
                .setSmallIcon(R.drawable.ic_notif)
                .setContentTitle("Pengingat Jadwal Donor Darah")
                .setContentText("Buka aplikasi untuk cek jadwal donor darah hari ini.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(context)
            notificationManager.notify(200, builder.build())
        } catch (e: Exception) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
        }
    }
}
