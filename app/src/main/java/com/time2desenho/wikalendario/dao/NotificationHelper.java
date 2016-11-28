package com.time2desenho.wikalendario.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.time2desenho.wikalendario.model.Class;


public class NotificationHelper extends DatabaseHelper {

    private Dao<Class, Long> classDAO;

    public NotificationHelper(Context context) {
        super(context);
    }

    public Dao<Class, Long> getDAO() throws java.sql.SQLException {
        if(classDAO == null){
            classDAO = getDao(Class.class);
        }

        return classDAO;
    }
}
