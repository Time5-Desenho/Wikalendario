package com.time2desenho.wikalendario.controller;

import android.content.Context;

import com.time2desenho.wikalendario.model.Event;
import com.time2desenho.wikalendario.model.Notification;


public class NotificationController implements EventObserver {

    private Context context;

    public NotificationController(Context context){
        this.context = context;
    }

    public Notification notificationByEvent(){
        Notification notification = new Notification("title", "description", "00/00/0000");
        return notification;
    }

    @Override
    public void notifyNewEvent(Event event) {
        Notification notification = new Notification(event.getTitle(), event.getDescription(), event.getDate().toString());
    }
}
