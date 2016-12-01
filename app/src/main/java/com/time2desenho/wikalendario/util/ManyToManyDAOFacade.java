package com.time2desenho.wikalendario.util;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.time2desenho.wikalendario.dao.*;
import com.time2desenho.wikalendario.model.*;
import com.time2desenho.wikalendario.model.Class;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.time2desenho.wikalendario.model.Class.CLASS_ID;
import static com.time2desenho.wikalendario.model.Group.GROUP_ID;
import static com.time2desenho.wikalendario.model.User.USER_ID;

public class ManyToManyDAOFacade {

    private Context context;
    private Dao<UserClass, Long> userClassDAO;
    private Dao<UserGroup, Long> userGroupDAO;
    private Dao<Class, Long> classDAO;
    private Dao<Group, Long> groupDAO;
    private Dao<User, Long> userDAO;

    private PreparedQuery<Class> classesForUserQuery;
    private PreparedQuery<User> usersForClassQuery;

    public ManyToManyDAOFacade(Context context){
        this.context = context;

        try {
            UserClassDatabaseHelper userClassDatabaseHelper = new UserClassDatabaseHelper(context);
            userClassDAO = userClassDatabaseHelper.getDAO();

            UserGroupDatabaseHelper userGroupDatabaseHelper = new UserGroupDatabaseHelper(context);
            userGroupDAO = userGroupDatabaseHelper.getDAO();

            ClassDatabaseHelper classDatabaseHelper = new ClassDatabaseHelper(context);
            classDAO = classDatabaseHelper.getDAO();

            GroupDatabaseHelper groupDatabaseHelper = new GroupDatabaseHelper(context);
            groupDAO = groupDatabaseHelper.getDAO();

            UserDatabaseHelper userDatabaseHelper = new UserDatabaseHelper(context);
            userDAO = userDatabaseHelper.getDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Class> getClasses(User user){
        List<Class> classes = new ArrayList<>();


        return classes;
    }

    public List<Group> getGroups(User user){
        List<Group> groups;

        return groups;
    }

    public List<User> getUsers(Class userClass){
        List<User> users;

        return users;
    }

    public List<User> getUsers(Group group){
        List<User> users;

        return users;
    }

    private PreparedQuery<Class> makeClassesForUserQuery() throws SQLException {
        QueryBuilder<UserClass, Long> userClassesQb = userClassDAO.queryBuilder();
        userClassesQb.selectColumns(CLASS_ID);
        SelectArg userSelectArg = new SelectArg();
        userClassesQb.where().eq(USER_ID, userSelectArg);

        QueryBuilder<Class, Long> classQb = classDAO.queryBuilder();
        classQb.where().in(CLASS_ID, userClassesQb);
        return classQb.prepare();
    }

    private PreparedQuery<Group> makeGroupsForUserQuery() throws SQLException {
        QueryBuilder<UserGroup, Long> userGroupsQb = userGroupDAO.queryBuilder();
        userGroupsQb.selectColumns(GROUP_ID);
        SelectArg userSelectArg = new SelectArg();
        userGroupsQb.where().eq(USER_ID, userSelectArg);

        QueryBuilder<Group, Long> groupsQb = groupDAO.queryBuilder();
        groupsQb.where().in(GROUP_ID, userGroupsQb);
        return groupsQb.prepare();
    }
}
