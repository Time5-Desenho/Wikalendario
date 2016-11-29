package com.time2desenho.wikalendario.controller;

import android.content.Context;
import android.content.res.Resources;
import android.widget.EditText;

import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.view.CreateUserActivity;
import com.time2desenho.wikalendario.view.EditUserActivity;
import com.time2desenho.wikalendario.model.User;

public class UserHelper {

    private final EditText nameField;
    private final EditText emailField;
    private final EditText usernameField;
    private final EditText passwordField;
    private final EditText confirmPasswordField;

    private final String EMPTY_STRING = "";
    private User user;

    public UserHelper(CreateUserActivity activity){
        nameField  = (EditText) activity.findViewById(R.id.name);
        emailField  = (EditText) activity.findViewById(R.id.email);
        usernameField  = (EditText) activity.findViewById(R.id.username);
        passwordField  = (EditText) activity.findViewById(R.id.password);
        confirmPasswordField  = (EditText) activity.findViewById(R.id.confirm_password);

        user = new User();
    }

    public UserHelper(EditUserActivity activity){
        nameField  = (EditText) activity.findViewById(R.id.name);
        emailField  = (EditText) activity.findViewById(R.id.email);
        usernameField  = (EditText) activity.findViewById(R.id.username);
        passwordField = new EditText(activity);
        confirmPasswordField = new EditText(activity);

        user = new User();
    }

    public void setUser(Context context){
        User user = SessionSingleton.getInstance(context).getLoggedUser(context);
        nameField.setText(user.getName());
        emailField.setText(user.getEmail());
        usernameField.setText(user.getUsername());
    }

    public User getNewUser(Context context) throws  IllegalArgumentException{
        User user = getUser(context);

        String password = passwordField.getText().toString();
        String passwordConfirmation = confirmPasswordField.getText().toString();

        Resources resources = context.getResources();

        if(!password.equals(passwordConfirmation)){
            throw new IllegalArgumentException(resources.getString(R.string.not_matching_passwords));
        }

        if(password.equals(EMPTY_STRING)){
            throw new IllegalArgumentException(resources.getString(R.string.blank_password));
        }

        user.setPassword(password);

        return user;
    }

    public User getUser(Context context) throws IllegalArgumentException{
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String username = usernameField.getText().toString();

        Resources resources = context.getResources();

        if(name.equals(EMPTY_STRING)){
            throw new IllegalArgumentException(resources.getString(R.string.blank_name));
        }else if(email.equals(EMPTY_STRING)){
            throw new IllegalArgumentException(resources.getString(R.string.blank_email));
        }else if(username.equals(EMPTY_STRING)){
            throw new IllegalArgumentException(resources.getString(R.string.blank_username));
        }

        user.setName(name);
        user.setEmail(email);
        user.setUsername(username);

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