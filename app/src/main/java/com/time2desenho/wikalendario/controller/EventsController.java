package com.time2desenho.wikalendario.controller;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.dao.EventDatabaseHelper;
import com.time2desenho.wikalendario.model.Class;
import com.time2desenho.wikalendario.model.Event;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class EventsController {

    private EventDatabaseHelper eventDatabaseHelper;
    private Dao<Event, Long> eventDAO;
    private Class currentClass;
    private Set<EventObserver> eventObservers = new HashSet<EventObserver>() ;

    public EventsController(Context context) {
        eventDatabaseHelper = new EventDatabaseHelper(context);

        try {
            eventDAO = eventDatabaseHelper.getDAO();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void attachObserver(EventObserver eventObserver){
        this.eventObservers.add(eventObserver);
    }

    public void detachObserver(EventObserver eventObserver){
        this.eventObservers.remove(eventObserver);
    }

    public void createEvent(Event event, Context context) throws IllegalArgumentException{
        Resources resources = context.getResources();

        try {
            eventDAO.create(event);
        }catch(SQLException e){
            sqlExceptionTreatment(e, resources);
        }

        for (EventObserver eventObserver : this.eventObservers) {
            eventObserver.notifyNewEvent(event);
        }
    }

    public void updateEvent(Event loggedEvent, Event event, Context context){
        Resources resources = context.getResources();

        event.setId(loggedEvent.getId());
        event.setTitle(loggedEvent.getTitle());
        event.setDescription(loggedEvent.getDescription());
        event.setDate(loggedEvent.getDate());

        try {
            eventDAO.update(event);
        }catch(SQLException e){
            sqlExceptionTreatment(e, resources);
        }
    }

    public void deleteEvent(Event event){
        try {
            eventDAO.delete(event);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void sqlExceptionTreatment(SQLException e, Resources resources) throws IllegalArgumentException{
        e.printStackTrace();
        Throwable t = e.getCause();
        if(t != null){
            t = t.getCause();
            if(t != null){
                String message = t.getMessage();
                Log.d("SQLException", "message = " + message);
                if(message.contains("UNIQUE constraint failed: users.")) {
                    throw new IllegalArgumentException(resources.getString(R.string.event_already_registered));
                }
            }else{
                e.printStackTrace();
            }
        }
    }

    public void setCurrentClass(Long id, Context context){
        ClassController classController = new ClassController(context);

        this.currentClass = classController.getClass(id);
    }

    public Class getCurrentClass(){
        return currentClass;
    }
}
