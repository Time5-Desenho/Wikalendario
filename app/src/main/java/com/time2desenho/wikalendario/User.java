package com.time2desenho.wikalendario;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

import static com.time2desenho.wikalendario.User.USERS_TABLE;

@DatabaseTable(tableName = USERS_TABLE)
public class User implements Serializable {
    public final static String USERS_TABLE = "users";
    public final static String USER_ID = "user_id";
    public final static String USER_NAME = "user_name";
    public final static String USERNAME = "username";
    public final static String EMAIL = "email";
    public final static String PASSWORD = "password";


    @DatabaseField(generatedId = true, columnName = USER_ID)
    private Long id;

    @DatabaseField(columnName = USER_NAME)
    private String name;

    @DatabaseField(columnName = USERNAME)
    private String username;

    @DatabaseField(columnName = EMAIL)
    private String email;

    @DatabaseField(columnName = PASSWORD)
    private String password;

    public User(String username) {
        this.username = username;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
