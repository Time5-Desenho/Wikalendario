package com.time2desenho.wikalendario.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.time2desenho.wikalendario.model.UserGroup;

public class UserGroupDatabaseHelper extends DatabaseHelper{
    private Dao<UserGroup, Long> userGroupDAO;

    public UserGroupDatabaseHelper(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, UserGroup.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, UserGroup.class, false);
        }catch (java.sql.SQLException e){
            e.printStackTrace();
        }
    }

    public Dao<UserGroup, Long> getDAO() throws java.sql.SQLException {
        if(userGroupDAO == null){
            userGroupDAO = getDao(UserGroup.class);
        }

        return userGroupDAO;
    }
}