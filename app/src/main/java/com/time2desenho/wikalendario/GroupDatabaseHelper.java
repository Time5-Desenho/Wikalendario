package com.time2desenho.wikalendario;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class GroupDatabaseHelper extends DatabaseHelper{
    public GroupDatabaseHelper(Context context){
        super(context);
    }

    private Dao<Group, Long> groupDAO;

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Group.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Group.class, false);
        }catch (java.sql.SQLException e){
            e.printStackTrace();
        }
    }

    public Dao<Group, Long> getDAO() throws java.sql.SQLException {
        if(groupDAO == null){
            groupDAO = getDao(Group.class);
        }

        return groupDAO;
    }

}
