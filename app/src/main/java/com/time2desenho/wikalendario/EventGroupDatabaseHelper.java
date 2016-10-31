package com.time2desenho.wikalendario;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class EventGroupDatabaseHelper extends DatabaseHelper {

    public  EventGroupDatabaseHelper(Context context){
        super(context);
    }

    private Dao<EventGroup, Long> eventGroupDAO;

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, EventGroup.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, EventGroup.class, false);
        }catch (java.sql.SQLException e){
            e.printStackTrace();
        }
    }

    public Dao<EventGroup, Long> getDAO() throws java.sql.SQLException {
        if(eventGroupDAO == null){
            eventGroupDAO = getDao(EventGroup.class);
        }

        return eventGroupDAO;
    }
}