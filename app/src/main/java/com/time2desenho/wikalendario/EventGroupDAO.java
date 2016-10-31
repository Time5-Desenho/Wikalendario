package com.time2desenho.wikalendario;


import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

public class EventGroupDAO extends SQLiteOpenHelper {


    protected static String TABLE = "EVENTCLASS";
    protected static String COLUMN_TITTLE = "tittle";
    protected static String COLUMN_DESCRIPTION="description";
    protected static String COLUMN_CLASS="class";
    protected static String COLUMN_DATE="date";


    public EventGroupDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public EventGroupDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public static void createTableClass(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE  IF NOT EXISTS " + TABLE + " (" +
                COLUMN_TITTLE + " VARCHAR(45) NOT NULL, " +
                COLUMN_DESCRIPTION + " VARCHAR(45) NOT NULL, " +
                COLUMN_CLASS + " VARCHAR(45) NOT NULL, " +
                COLUMN_DATE + "VARCHAR(45) NOT NULL, " +
                " CONSTRAINT " + TABLE + "_PK PRIMARY KEY (" + COLUMN_TITTLE + "))");
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
    private ContentValues getEventClassData(EventClass eventClass) {
        ContentValues data = new ContentValues();
        data.put(COLUMN_TITTLE, eventClass.getEventClassTitle());
        data.put(COLUMN_CLASS, eventClass.getEventClass());
        data.put(COLUMN_DESCRIPTION, eventClass.getEventClassDescription());
        data.put(COLUMN_DATE, eventClass.getEventClassDate());
        return data;
    }

    public int insertEventClass(EventClass eventClass) throws SQLException {
        SQLiteDatabase dataBase = getWritableDatabase();
        int insertReturn;
        ContentValues data = getEventClassData(eventClass);

        insertReturn = (int) dataBase.insertOrThrow(TABLE, null, data);

        return  insertReturn;
    }

}