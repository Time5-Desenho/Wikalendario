package com.time2desenho.wikalendario.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.time2desenho.wikalendario.model.Class;

public class ClassDatabaseHelper extends DatabaseHelper{

    private Dao<Class, Long> classDAO;

    public ClassDatabaseHelper(Context context) {
        super(context);
    }

    public Dao<Class, Long> getDAO() throws java.sql.SQLException {
        if(classDAO == null){
            classDAO = getDao(Class.class);
        }

        return classDAO;
    }
}
