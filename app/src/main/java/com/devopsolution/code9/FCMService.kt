package com.devopsolution.code9

import android.app.Notification
import android.app.NotificationManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavDeepLinkBuilder
import com.devopsolution.code9.common.Constants
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMService : FirebaseMessagingService() {


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)


        remoteMessage.data.isNotEmpty().let {

            val notificationId = 121
            val channelId =  Constants.GLOBAL_NOTIFICATIONS_CHANNEL



            val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                NotificationCompat.Builder(applicationContext, channelId)
                    .setContentTitle(remoteMessage.data["title"])
                    .setContentText(remoteMessage.data["body"])
                    .setPriority(NotificationManager.IMPORTANCE_HIGH)
                    .setSmallIcon(R.drawable.square_icon)
                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.square_icon))
                    .setColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
                    .setColorized(true)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .build()
            } else {
                NotificationCompat.Builder(applicationContext, channelId)
                    .setContentTitle(remoteMessage.data["title"])
                    .setContentText(remoteMessage.data["body"])
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setSmallIcon(R.drawable.square_icon)
                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.square_icon))
                    .setColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
                    .setColorized(true)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .build()
            }

            with(NotificationManagerCompat.from(this)) {
                notify(notificationId, notification)
            }

        }
    }
}