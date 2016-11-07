package com.time2desenho.wikalendario;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.time2desenho.wikalendario.dao.UserDatabaseHelper;
import com.time2desenho.wikalendario.model.User;

import java.sql.SQLException;

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

    public void createUser(User user){
        try {
            userDAO.create(user);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void updateUser(User user){
        try {
            userDAO.create(user);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteUser(User user){
        try {
            userDAO.create(user);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
