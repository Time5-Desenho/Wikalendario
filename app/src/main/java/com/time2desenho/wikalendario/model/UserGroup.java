package com.time2desenho.wikalendario.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static com.time2desenho.wikalendario.model.User.USER_ID;
import static com.time2desenho.wikalendario.model.UserGroup.USER_GROUP_TABLE;

@DatabaseTable(tableName = USER_GROUP_TABLE)
public class UserGroup {
    public static final String USER_GROUP_TABLE = "user_group";
    public static final String USER_GROUP_ID = "user_group_id";

    @DatabaseField(generatedId = true, columnName = USER_GROUP_ID)
    Long id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = USER_ID)
    User user;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = Group.GROUP_ID)
    Group group;

    public UserGroup(){
        setUser(new User());
        setGroup(new Group());
    }

    public UserGroup(User user, Group group){
        setUser(user);
        setGroup(group);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
