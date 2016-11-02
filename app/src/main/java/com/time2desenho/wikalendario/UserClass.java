package com.time2desenho.wikalendario;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static com.time2desenho.wikalendario.Class.CLASS_ID;
import static com.time2desenho.wikalendario.User.USER_ID;
import static com.time2desenho.wikalendario.UserClass.USER_CLASS_TABLE;

@DatabaseTable(tableName = USER_CLASS_TABLE)
public class UserClass {

    public static final String USER_CLASS_TABLE = "user_class";
    public static final String USER_CLASS_ID = "user_class_id";

    @DatabaseField(generatedId = true, columnName = USER_CLASS_ID)
    Long id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = USER_ID)
    User user;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = CLASS_ID)
    Class userClass;

    public UserClass(){
        setUser(new User());
        setUserClass(new Class());
    }

    public UserClass(User user, Class userClass){
        setUser(user);
        setUserClass(userClass);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Class getUserClass() {
        return userClass;
    }

    public void setUserClass(Class userClass) {
        this.userClass = userClass;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

