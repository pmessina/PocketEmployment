package com.jobdetailsmanager.pocketemployment

/*
 * Copyright (c) 2016. Patrick Messina
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Icon
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.app.NotificationCompat
import android.widget.RemoteViews

class CallsNotificationManager(val context: Context)
{
    private lateinit var notificationManager: NotificationManager

    private lateinit var inboxStyle: Notification.InboxStyle

    internal lateinit var notificationData: String

    init {
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
    }

//    private constructor(context: Context, notificationData: String)
//    {
//        this.notificationData = notificationData
//        inboxStyle = Notification.InboxStyle()
//        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
//
//    }

    fun setUpNotification(notificationID: Int, contentText: String, pendingIntent: PendingIntent, isForeground: Boolean): Notification
    {
        val notificationBuilder = NotificationCompat.Builder(context).setContentTitle("Recruiter Call Service").setContentText(contentText).setContentIntent(pendingIntent).setAutoCancel(true) //For Testing
                .setGroup("RecruiterCalls").setSmallIcon(R.mipmap.ic_launcher).setTicker(contentText) as NotificationCompat.Builder

        val notification = notificationBuilder.build()

        notificationManager!!.notify(notificationID, notification)

        if (isForeground)
        {
            if (context is Service)
            {
                context.startForeground(notificationID, notificationBuilder.build())
            }
        }

        return notification

    }

    @RequiresApi(Build.VERSION_CODES.M)
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun setUpCustomNotification(notificationID: Int, contentText: String, pendingIntent: PendingIntent): Notification
    {
        val deleteViewBitmap = BitmapFactory.decodeResource(context.resources, android.R.drawable.ic_delete)

        val remoteViews = RemoteViews(context.packageName, R.layout.custom_notification_view)
        remoteViews.setTextViewText(R.id.tvPhoneNumberFrom, contentText)
        remoteViews.setTextColor(R.id.tvPhoneNumberFrom, Color.BLUE)
        remoteViews.setImageViewBitmap(R.layout.custom_notification_view, deleteViewBitmap)

        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.interview)

        val iconAdd = Icon.createWithResource(context, R.drawable.interview)
        val actionAdd = Notification.Action.Builder(iconAdd, "Add interview", pendingIntent).build()

        val iconDelete = Icon.createWithResource(context, android.R.drawable.ic_delete)
        val actionDelete = Notification.Action.Builder(iconDelete, "Delete", pendingIntent).build()

        val notificationBuilder = Notification.Builder(context)
                .setContentTitle("Recruiter Call Service")
                .setContentText(contentText)
                .setContentIntent(pendingIntent)
                .setAutoCancel(false)
                .setStyle(Notification.BigPictureStyle().bigPicture(bitmap)
                        .setSummaryText("Some Summary text"))
                .addAction(actionAdd)
                //.addAction(R.drawable.logbook, "Add job position", pendingIntent)
                .addAction(actionDelete).setGroup("RecruiterCalls").setSmallIcon(R.mipmap.ic_launcher).setTicker(contentText) as Notification.Builder

        val notification = notificationBuilder.build()
        notification.contentView = remoteViews
        notification.flags = notification.flags or Notification.FLAG_NO_CLEAR

        notificationManager!!.notify(notificationID, notification)

        return notification

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setDialogNotification(notificationID: Int, contentText: String, yesPendingIntent: PendingIntent, noPendingIntent: PendingIntent): Notification
    {
        //        Bitmap deleteViewBitmap = BitmapFactory.decodeResource(context.getResources(), android.R.drawable.ic_delete);

        val remoteViews = RemoteViews(context.packageName, R.layout.custom_notification_view)
        remoteViews.setTextViewText(R.id.tvPhoneNumberFrom, contentText)
        remoteViews.setTextColor(R.id.tvPhoneNumberFrom, Color.BLUE)
        //        remoteViews.setImageViewBitmap(R.layout.custom_notification_view, deleteViewBitmap);

        val notificationBuilder = Notification.Builder(context)
                .setContentTitle("Recruiter Call Service")
                .setContentText(contentText)
                //                .setContentIntent(pendingIntent)
                .setAutoCancel(false)
                .addAction(R.drawable.ic_checkmark, "Yes", yesPendingIntent)
                .addAction(R.drawable.ic_xmark, "No", noPendingIntent)
                .setStyle(Notification.MediaStyle()
                        .setShowActionsInCompactView(0, 1))
                .setGroup("RecruiterCalls").setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(contentText) as Notification.Builder

        val notification = notificationBuilder.build()
        notification.contentView = remoteViews
        notification.flags = notification.flags or Notification.FLAG_NO_CLEAR

        notificationManager!!.notify(notificationID, notification)

        return notification

    }


    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    fun setUpNotification(context: Context, notificationID: Int, contentText: String, contentLine: String, pendingIntent: PendingIntent, isForeground: Boolean)
    {
        notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager

        val notification = Notification.Builder(context)
                .setContentTitle("Recruiter Call Service")
                .setContentText(contentText)
                .setContentIntent(pendingIntent)
                .setGroup("RecruiterCalls")
                .setStyle(Notification.InboxStyle().addLine(contentLine))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(contentText) as Notification.Builder

        notificationManager!!.notify(notificationID, notification.build())

        if (isForeground)
        {
            if (context is Service)
            {
                context.startForeground(notificationID, notification.build())
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun addSummaryText(notification: Notification.Builder): Notification.InboxStyle
    {
        val inboxStyle = Notification.InboxStyle()
        inboxStyle.setSummaryText("Add email here")
        notification.setStyle(inboxStyle)
        return inboxStyle
    }


    fun stopNotification(notificationID: Int)
    {
        notificationManager!!.cancel(notificationID)
    }

//    companion object
//    {
//        fun getInstance(context: Context): CallsNotificationManager
//        {
//            return CallsNotificationManager(context)
//        }
//
//        fun getInstance(context: Context, notificationData: String): CallsNotificationManager
//        {
//            return CallsNotificationManager(context, notificationData)
//        }
//    }
}
