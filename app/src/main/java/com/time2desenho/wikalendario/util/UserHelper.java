package com.time2desenho.wikalendario.util;

import android.content.Context;
import android.widget.EditText;

import com.j256.ormlite.dao.Dao;
import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.activity.CreateUserActivity;
import com.time2desenho.wikalendario.dao.UserDatabaseHelper;
import com.time2desenho.wikalendario.model.User;

import java.sql.SQLException;

/**
 * Created by mateus on 9/2/16.
 */
public class UserHelper {

    private final EditText nameField;
    private final EditText emailField;
    private final EditText usernameField;
    private final EditText passwordField;

    private User user;

    //TODO substituir qq chamada dessa função pela chamada de usuário logado
    public static User getFirstUser(Context context){
        UserDatabaseHelper userDatabaseHelper = new UserDatabaseHelper(context);

        User user = new User();
        try {
            Dao<User, Long> userDAO = userDatabaseHelper.getDAO();
            user = userDAO.queryForId(1L);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public UserHelper(CreateUserActivity activity){
        nameField  = (EditText) activity.findViewById(R.id.name);
        emailField  = (EditText) activity.findViewById(R.id.email);
        usernameField  = (EditText) activity.findViewById(R.id.username);
        passwordField  = (EditText) activity.findViewById(R.id.password);

        user = new User();
    }

    public User getUser() {
        user.setName(nameField.getText().toString());
        user.setEmail(emailField.getText().toString());
        user.setUsername(usernameField.getText().toString());
        user.setPassword(passwordField.getText().toString());

        return user;
    }

    public void fillForm(User user) {
        nameField.setText(user.getName());
        emailField.setText(user.getEmail());
        usernameField.setText(user.getUsername());
        passwordField.setText(user.getPassword());
        this.user = user;
    }
}
