package com.time2desenho.wikalendario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

/**
 * Created by mateus on 9/1/16.
 */
public class UserDao extends SQLiteOpenHelper {

    public UserDao(Context context) {
        super(context, "Wikalendario", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Users (id INTEGER PRIMARY KEY, name TEXT NOT NULL, username TEXT UNIQUE NOT NULL, email TEXT, password TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Wikalendario";
        db.execSQL(sql);
        onCreate(db);
    }


    public User findUser(User user) {
        Cursor c = null;
        User userFinded;

        String sql = "SELECT * FROM Users WHERE username=?";
        SQLiteDatabase db = getReadableDatabase();

        try {
            c = db.rawQuery(sql, new String[] {user.getUsername()});

            if(c.moveToFirst()) {
                userFinded = new User();

                userFinded.setId(c.getLong(c.getColumnIndex("id")));
                userFinded.setName(c.getString(c.getColumnIndex("name")));
                userFinded.setUsername(c.getString(c.getColumnIndex("username")));
                userFinded.setEmail(c.getString(c.getColumnIndex("email")));
                userFinded.setPassword(c.getString(c.getColumnIndex("password")));
            } else{
                userFinded = new User("nothing");
            }
        } finally {
            c.close();
        }

        return userFinded;
    }

    public void insert(User user) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = getUserData(user);

        db.insert("Users", null, data);
    }

    public void delete(User user) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {user.getId().toString()};
        db.delete("Users", "id = ?", params);
    }

    public void update(User user) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = getUserData(user);

        String[] params = {user.getId().toString()};
        db.update("Users", data, "id = ?", params);
    }

    @NonNull
    private ContentValues getUserData(User user) {
        ContentValues data = new ContentValues();
        data.put("name", user.getName());
        data.put("username", user.getUsername());
        data.put("email", user.getEmail());
        data.put("password", user.getPassword());
        return data;
    }
}
