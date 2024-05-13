package edu.towson.outfitapp.MainPage

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
//import edu.towson.outfitapp.Manifest
import edu.towson.outfitapp.R
import android.Manifest
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED


fun createNotificationChanel(context: Context){
    val name = "Comments/Likes Notification"
    val descriptionText = "Notification channel for Comments/Likes"

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(name,descriptionText, importance)
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    val builder = NotificationCompat.Builder(context, name)
        .setSmallIcon(R.drawable.app_logo)
        .setContentTitle("Outfitify")
        .setContentText("Post Liked!")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_NOTIFICATION_POLICY)
        != PERMISSION_GRANTED){

        ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.ACCESS_NOTIFICATION_POLICY), 1)
    }

    with(NotificationManagerCompat.from(context)){
        notify(1, builder.build())
    }

}
