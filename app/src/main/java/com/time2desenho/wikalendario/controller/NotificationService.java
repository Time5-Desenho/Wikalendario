package com.time2desenho.wikalendario.controller;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.view.MainActivity;

public class NotificationService extends FirebaseMessagingService {

    private static final String TAG = "wikalendario-a922f";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    @Override
    public void zzm(Intent intent) {
        Intent launchIntent = new Intent(this, MainActivity.class);
        launchIntent.setAction(Intent.ACTION_MAIN);
        launchIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, launchIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon_notification_1)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_notification_2))
                .setContentTitle(intent.getStringExtra("gcm.notification.title"))
                .setContentText(intent.getStringExtra("gcm.notification.body"))
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(intent.getIntExtra("fcm.notification.id", 0), notificationBuilder.build());
    }
}