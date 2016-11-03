package com.time2desenho.wikalendario.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.time2desenho.wikalendario.model.Event;

public class EventDatabaseHelper extends DatabaseHelper {

    private Dao<Event, Long> eventDAO;

    public EventDatabaseHelper(Context context){
        super(context);
    }

    public Dao<Event, Long> getDAO() throws java.sql.SQLException {
        if(eventDAO == null){
            eventDAO = getDao(Event.class);
        }

        return eventDAO;
    }
}