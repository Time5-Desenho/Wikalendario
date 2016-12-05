package com.time2desenho.wikalendario.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.time2desenho.wikalendario.model.Group;

public class GroupDatabaseHelper extends DatabaseHelper{

    private Dao<Group, Long> groupDAO;

    public GroupDatabaseHelper(Context context){
        super(context);
    }

    public Dao<Group, Long> getDAO() throws java.sql.SQLException {
        if(groupDAO == null){
            groupDAO = getDao(Group.class);
        }

        return groupDAO;
    }

}
