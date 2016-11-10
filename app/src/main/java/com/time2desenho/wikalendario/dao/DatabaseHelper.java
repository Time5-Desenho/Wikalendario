package com.time2desenho.wikalendario.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.time2desenho.wikalendario.model.Class;
import com.time2desenho.wikalendario.model.Event;
import com.time2desenho.wikalendario.model.Group;
import com.time2desenho.wikalendario.model.Subject;
import com.time2desenho.wikalendario.model.User;
import com.time2desenho.wikalendario.model.UserClass;
import com.time2desenho.wikalendario.model.UserGroup;

import java.sql.SQLException;

public abstract class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    public static final String DATABASE_NAME = "sql3143440";
    public static final int DATABASE_VERSION = 1;
    public static final String HOST = "sql3.freemysqlhosting.net";
    public static final String DATABASE_USERNAME = "sql3143440";
    public static final String PORT = "3306";
    public static final String DATABASE_PASSWORD = "NfjLTEQYa1";


    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, User.class);
            TableUtils.createTableIfNotExists(connectionSource, Subject.class);
            TableUtils.createTableIfNotExists(connectionSource, Class.class);
            TableUtils.createTableIfNotExists(connectionSource, Group.class);
            TableUtils.createTableIfNotExists(connectionSource, Event.class);
            TableUtils.createTableIfNotExists(connectionSource, UserClass.class);
            TableUtils.createTableIfNotExists(connectionSource, UserGroup.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, User.class, false);
            TableUtils.dropTable(connectionSource, Subject.class, false);
            TableUtils.dropTable(connectionSource, Class.class, false);
            TableUtils.dropTable(connectionSource, Group.class, false);
            TableUtils.dropTable(connectionSource, Event.class, false);
            TableUtils.dropTable(connectionSource, UserClass.class, false);
            TableUtils.dropTable(connectionSource, UserGroup.class, false);

            onCreate(db, connectionSource);
        }catch (java.sql.SQLException e){
            e.printStackTrace();
        }
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
