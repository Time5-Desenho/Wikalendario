package com.time2desenho.wikalendario;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

public class ClassDAO extends SQLiteOpenHelper {

    protected static String TABLE = "CLASS";
    protected static String COLUMN_ID = "idClass";
    protected static String COLUMN_LETTER="letter";
    protected static String COLUMN_TEACHER="teacher";


    public ClassDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ClassDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public static void createTableClass(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE  IF NOT EXISTS " + TABLE + " (" +
                COLUMN_ID + " INTEGER NOT NULL, " +
                COLUMN_LETTER + " VARCHAR(45) NOT NULL, " +
                COLUMN_TEACHER + "VARCHAR(45) NOT NULL, " +
                " CONSTRAINT " + TABLE + "_PK PRIMARY KEY (" + COLUMN_ID + "))");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
        createTableClass(sqLiteDatabase);
    }

    @NonNull
    private ContentValues getClassData(Class classes) {
        ContentValues data = new ContentValues();
        data.put(COLUMN_ID, classes.getIdClass());
        data.put(COLUMN_LETTER, classes.getLetter());
        data.put(COLUMN_TEACHER, classes.getTeacher());
        return data;
    }

    public int insertBook(Class classes) throws SQLException {
        SQLiteDatabase dataBase = getWritableDatabase();
        int insertReturn;
        ContentValues data = getClassData(classes);

        insertReturn = (int) dataBase.insertOrThrow(TABLE, null, data);

        return  insertReturn;
    }

}
