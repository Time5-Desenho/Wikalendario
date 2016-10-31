package com.time2desenho.wikalendario;

import android.content.Context;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

public abstract class DatabaseHelper extends OrmLiteSqliteOpenHelper{
    protected static final String DATABASE_NAME = "Wikalendario";
    protected static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
