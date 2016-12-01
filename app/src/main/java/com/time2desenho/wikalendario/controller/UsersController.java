package com.time2desenho.wikalendario.controller;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.dao.UserDatabaseHelper;
import com.time2desenho.wikalendario.model.User;

import java.sql.SQLException;
import java.util.List;

import static com.time2desenho.wikalendario.model.User.EMAIL;
import static com.time2desenho.wikalendario.model.User.USERNAME;

public class UsersController {

    private UserDatabaseHelper userDatabaseHelper;
    private Dao<User, Long> userDAO;

    public UsersController(Context context) {
        userDatabaseHelper = new UserDatabaseHelper(context);

        try {
            userDAO = userDatabaseHelper.getDAO();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void createUser(User user, Context context) throws IllegalArgumentException{
        Resources resources = context.getResources();

        try {
            userDAO.create(user);

            List<User> us = userDAO.queryForAll();
            for(User u : us){
                Log.d("Eita", "Nome = " + u.getName());
            }

        }catch(SQLException e){
            sqlExceptionTreatment(e, resources);
        }
    }

    public void updateUser(User loggedUser, User user, Context context){
        Resources resources = context.getResources();

        user.setId(loggedUser.getId());
        user.setPassword(loggedUser.getPassword());

        try {
            userDAO.update(user);
        }catch(SQLException e){
            sqlExceptionTreatment(e, resources);
        }
    }

    public void deleteUser(User user){
        try {
            userDAO.delete(user);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void sqlExceptionTreatment(SQLException e, Resources resources) throws IllegalArgumentException{
        e.printStackTrace();
        Throwable t = e.getCause();
        if(t != null){
            t = t.getCause();
            if(t != null){
                String message = t.getMessage();
                Log.d("SQLException", "message = " + message);
                if(message.contains("UNIQUE constraint failed: users.")){
                    message = message.replace("UNIQUE constraint failed: users.", "");

                    if(message.contains(USERNAME)){
                        throw new IllegalArgumentException(resources.getString(R.string.user_already_registered));
                    }else if(message.contains(EMAIL)){
                        throw new IllegalArgumentException(resources.getString(R.string.email_already_registered));
                    }
                }
            }else{
                e.printStackTrace();
            }
        }
    }
}
