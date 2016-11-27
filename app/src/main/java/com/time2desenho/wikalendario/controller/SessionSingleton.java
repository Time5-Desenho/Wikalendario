package com.time2desenho.wikalendario.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.time2desenho.wikalendario.dao.UserDatabaseHelper;
import com.time2desenho.wikalendario.model.User;

import java.sql.SQLException;

public class SessionSingleton{
    private static String TAG = SessionSingleton.class.getSimpleName();

    private static SessionSingleton instance;

    private static SharedPreferences sharedPreferences;

    private static SharedPreferences.Editor editor;

    private static User loggedUser;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "WikalendarioLogin";

    private static final String KEY_IS_LOGGED = "isLoggedIn";
    private static final String KEY_LOGGED_USER = "loggedUser";

    public static SessionSingleton getInstance(Context context){
        SessionSingleton sessionSingleton;
        if(instance != null){
            sessionSingleton = instance;
        }else{
            sessionSingleton = new SessionSingleton(context);
        }

        return sessionSingleton;
    }

    public SessionSingleton(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void login(Long id, Context context){
        editor.putBoolean(KEY_IS_LOGGED, true);
        editor.putLong(KEY_LOGGED_USER, id);
        editor.commit();

        UserDatabaseHelper userDatabaseHelper = new UserDatabaseHelper(context);
    }

    public void logout(){
        editor.putBoolean(KEY_IS_LOGGED, false).commit();
        editor.putLong(KEY_LOGGED_USER, -1L);
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(KEY_IS_LOGGED, false);
    }

    public User getLoggedUser(Context context){
        User user = new User();
        if(!isLoggedIn()){
            throw new IllegalStateException("User not logged!");
        }else{
            if(loggedUser != null){
                user = loggedUser;
            }else{
                UserDatabaseHelper userDatabaseHelper = new UserDatabaseHelper(context);
                Long id = sharedPreferences.getLong(KEY_LOGGED_USER, -1);
                try {
                    user = userDatabaseHelper.getDAO().queryForId(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return user;
    }
}
