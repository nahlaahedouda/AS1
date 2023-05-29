package com.example.as1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    private fun createNotificationChannel() {
        val CHANNEL_ID = "UserLoginChannel"
        var manager: NotificationManager?=null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(CHANNEL_ID, "User Login Channel",
                NotificationManager.IMPORTANCE_DEFAULT)
            manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        } else {
            manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
        if(login.userLogin || signup.userSignup) {
            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("LogIn successful")
                .setContentText("Your account is really valid")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()
            manager!!.notify(1, notification)
        }else{
            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("LogIn Faild")
                .setContentText("Sorry your account is not valid")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()
            manager!!.notify(1, notification)
        }

    }
}