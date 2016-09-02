package com.time2desenho.wikalendario;

import android.widget.EditText;

/**
 * Created by mateus on 9/2/16.
 */
public class UserHelper {

    private final EditText nameField;
    private final EditText emailField;
    private final EditText usernameField;
    private final EditText passwordField;

    private User user;

    public UserHelper(UserActivity activity){
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
