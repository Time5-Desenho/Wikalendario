package com.time2desenho.wikalendario.controller;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.model.Event;
import com.time2desenho.wikalendario.model.Notification;

public class NotificationReceiver extends BroadcastReceiver implements EventObserver{
    @Override
    public void onReceive(Context context, Intent intent) {
        registerNotification(context,intent);
    }

    public void registerNotification(Context context,Intent intent) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationController notificationController = new NotificationController(context);
        Notification notification = notificationController.notificationByEvent();

        if(notification!=null) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setContentTitle(notification.getTitle());
            builder.setContentText(notification.getDescription());
            builder.setSmallIcon(R.drawable.icon_notification_1);
            builder.setLargeIcon(selectIconNotification(context));
            builder.setContentIntent(p);
            android.app.Notification n = builder.build();

            n.vibrate = new long[]{150, 300, 150, 600};
            n.flags = android.app.Notification.FLAG_AUTO_CANCEL;
            nm.notify(R.mipmap.ic_launcher, n);

            try {
                Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone toque = RingtoneManager.getRingtone(context, som);
                toque.play();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public Bitmap selectIconNotification(Context context){
        int idImage = R.drawable.icon_notification_2;
        return BitmapFactory.decodeResource(context.getResources(), idImage);
    }

    @Override
    public void notifyNewEvent(Event event) {

    }
}
