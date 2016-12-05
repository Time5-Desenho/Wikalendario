package com.time2desenho.wikalendario.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.time2desenho.wikalendario.model.User;

public class UserDatabaseHelper extends DatabaseHelper{

    private Dao<User, Long> userDAO;

    public UserDatabaseHelper(Context context) {
        super(context);
    }

    public Dao<User, Long> getDAO() throws java.sql.SQLException {
        if(userDAO == null){
            userDAO = getDao(User.class);
        }

        return userDAO;
    }
}
