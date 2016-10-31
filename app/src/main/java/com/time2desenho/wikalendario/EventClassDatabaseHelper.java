package com.time2desenho.wikalendario;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class EventClassDatabaseHelper extends DatabaseHelper {

    public  EventClassDatabaseHelper(Context context){
        super(context);
    }

    private Dao<EventClass, Long> eventClassDAO;

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, EventClass.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, EventClass.class, false);
        }catch (java.sql.SQLException e){
            e.printStackTrace();
        }
    }

    public Dao<EventClass, Long> getDAO() throws java.sql.SQLException {
        if(eventClassDAO == null){
            eventClassDAO = getDao(EventClass.class);
        }

        return eventClassDAO;
    }
}