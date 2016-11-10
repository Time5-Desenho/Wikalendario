package com.time2desenho.wikalendario.util;

import android.content.Context;
import android.content.res.Resources;
import android.widget.EditText;

import com.j256.ormlite.dao.Dao;
import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.activity.CreateUserActivity;
import com.time2desenho.wikalendario.dao.UserDatabaseHelper;
import com.time2desenho.wikalendario.model.User;

import java.sql.SQLException;

public class UserHelper {

    private final EditText nameField;
    private final EditText emailField;
    private final EditText usernameField;
    private final EditText passwordField;
    private final EditText confirmPasswordField;

    private final String EMPTY_STRING = "";
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
        confirmPasswordField  = (EditText) activity.findViewById(R.id.confirm_password);

        user = new User();
    }

    public User getUser(Context context) throws IllegalArgumentException{
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        String passwordConfirmation = confirmPasswordField.getText().toString();

        Resources resources = context.getResources();

        if(!password.equals(passwordConfirmation)){
            throw new IllegalArgumentException(resources.getString(R.string.not_matching_passwords));
        }

        if(name.equals(EMPTY_STRING)){
            throw new IllegalArgumentException(resources.getString(R.string.blank_name));
        }else if(email.equals(EMPTY_STRING)){
            throw new IllegalArgumentException(resources.getString(R.string.blank_email));
        }else if(username.equals(EMPTY_STRING)){
            throw new IllegalArgumentException(resources.getString(R.string.blank_username));
        }else if(password.equals(EMPTY_STRING)){
            throw new IllegalArgumentException(resources.getString(R.string.blank_password));
        }

        user.setName(name);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

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
