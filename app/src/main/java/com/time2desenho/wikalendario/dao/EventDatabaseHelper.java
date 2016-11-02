package com.time2desenho.wikalendario.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.time2desenho.wikalendario.model.Event;

public class EventDatabaseHelper extends DatabaseHelper {

    public EventDatabaseHelper(Context context){
        super(context);
    }

    private Dao<Event, Long> eventDAO;

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Event.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Event.class, false);
        }catch (java.sql.SQLException e){
            e.printStackTrace();
        }
    }

    public Dao<Event, Long> getDAO() throws java.sql.SQLException {
        if(eventDAO == null){
            eventDAO = getDao(Event.class);
        }

        return eventDAO;
    }
}