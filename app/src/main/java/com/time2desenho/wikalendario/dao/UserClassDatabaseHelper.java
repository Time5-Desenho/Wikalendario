package com.time2desenho.wikalendario.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.time2desenho.wikalendario.model.UserClass;

public class UserClassDatabaseHelper extends DatabaseHelper{
    private Dao<UserClass, Long> userClassDAO;

    public UserClassDatabaseHelper(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, UserClass.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, UserClass.class, false);
        }catch (java.sql.SQLException e){
            e.printStackTrace();
        }
    }

    public Dao<UserClass, Long> getDAO() throws java.sql.SQLException {
        if(userClassDAO == null){
            userClassDAO = getDao(UserClass.class);
        }

        return userClassDAO;
    }
}