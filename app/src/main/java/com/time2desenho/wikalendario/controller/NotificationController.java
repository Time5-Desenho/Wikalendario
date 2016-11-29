package com.time2desenho.wikalendario.controller;

import android.content.Context;

import com.time2desenho.wikalendario.model.Notification;


public class NotificationController {

    private Context context;

    public NotificationController(Context context){
        this.context = context;
    }

    public Notification notificationByEvent(){
        Notification notification = new Notification(1, "title", "description", "00/00/0000");
        return notification;
    }

}
