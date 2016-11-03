package com.time2desenho.wikalendario.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.time2desenho.wikalendario.model.Subject;

public class SubjectDatabaseHelper extends DatabaseHelper{

    private Dao<Subject, Long> subjectDAO;

    public SubjectDatabaseHelper(Context context){
        super(context);
    }

    public Dao<Subject, Long> getDAO() throws java.sql.SQLException {
        if(subjectDAO == null){
            subjectDAO = getDao(Subject.class);
        }

        return subjectDAO;
    }
}

