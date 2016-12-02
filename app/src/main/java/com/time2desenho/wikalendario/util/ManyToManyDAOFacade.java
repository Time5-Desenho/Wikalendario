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
import static com.time2desenho.wikalendario.model.UserClass.USER_CLASS_ID;
import static com.time2desenho.wikalendario.model.UserGroup.USER_GROUP_ID;

public class ManyToManyDAOFacade {

    private Dao<UserClass, Long> userClassDAO;
    private Dao<UserGroup, Long> userGroupDAO;
    private Dao<Class, Long> classDAO;
    private Dao<Group, Long> groupDAO;
    private Dao<User, Long> userDAO;

    private PreparedQuery<Group> groupsForUserQuery;
    private PreparedQuery<Class> classesForUserQuery;
    private PreparedQuery<User> usersForClassQuery;
    private PreparedQuery<User> usersForGroupQuery;

    public ManyToManyDAOFacade(Context context){
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

        try {
            if(classesForUserQuery == null) {
                classesForUserQuery = makeClassesForUserQuery();
            }

            classesForUserQuery.setArgumentHolderValue(0, user);
            classes = classDAO.query(classesForUserQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classes;
    }

    public List<Group> getGroups(User user){
        List<Group> groups = new ArrayList<>();

        try {
            if(groupsForUserQuery == null){
                groupsForUserQuery = makeGroupsForUserQuery();
            }

            groupsForUserQuery.setArgumentHolderValue(0, user);
            groups = groupDAO.query(groupsForUserQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groups;
    }

    public List<User> getUsers(Class userClass){
        List<User> users = new ArrayList<>();

        try{
            if(usersForClassQuery == null){
                usersForClassQuery = makeUsersForClassQuery();
            }

            usersForClassQuery.setArgumentHolderValue(0, userClass);
            users = userDAO.query(usersForClassQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public List<User> getUsers(Group group){
        List<User> users = new ArrayList<>();

        try{
            if(usersForGroupQuery == null){
                usersForGroupQuery = makeUsersForGroupQuery();
            }
            usersForGroupQuery.setArgumentHolderValue(0, group);
            users = userDAO.query(usersForGroupQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    private PreparedQuery<Class> makeClassesForUserQuery() throws SQLException {
        QueryBuilder<UserClass, Long> userClassesQb = userClassDAO.queryBuilder();
        userClassesQb.selectColumns(USER_CLASS_ID);
        SelectArg userSelectArg = new SelectArg();
        userClassesQb.where().eq(USER_ID, userSelectArg);

        QueryBuilder<Class, Long> classQb = classDAO.queryBuilder();
        classQb.where().in(CLASS_ID, userClassesQb);
        return classQb.prepare();
    }

    private PreparedQuery<Group> makeGroupsForUserQuery() throws SQLException {
        QueryBuilder<UserGroup, Long> userGroupsQb = userGroupDAO.queryBuilder();
        userGroupsQb.selectColumns(USER_GROUP_ID);
        SelectArg userSelectArg = new SelectArg();
        userGroupsQb.where().eq(USER_ID, userSelectArg);

        QueryBuilder<Group, Long> groupsQb = groupDAO.queryBuilder();
        groupsQb.where().in(GROUP_ID, userGroupsQb);
        return groupsQb.prepare();
    }

    private PreparedQuery<User> makeUsersForClassQuery() throws SQLException {
        QueryBuilder<UserClass, Long> userClassQb = userClassDAO.queryBuilder();
        userClassQb.selectColumns(USER_CLASS_ID);
        SelectArg classSelectArg = new SelectArg();
        userClassQb.where().eq(CLASS_ID, classSelectArg);

        QueryBuilder<User, Long> userQb = userDAO.queryBuilder();
        userQb.where().in(CLASS_ID, userClassQb);
        return userQb.prepare();
    }

    private PreparedQuery<User> makeUsersForGroupQuery() throws SQLException {
        QueryBuilder<UserGroup, Long> userGroupQb = userGroupDAO.queryBuilder();
        userGroupQb.selectColumns(USER_GROUP_ID);
        SelectArg groupSelectArg = new SelectArg();
        userGroupQb.where().eq(GROUP_ID, groupSelectArg);

        QueryBuilder<User, Long> userQb = userDAO.queryBuilder();
        userQb.where().in(GROUP_ID, userGroupQb);
        return userQb.prepare();
    }
}
