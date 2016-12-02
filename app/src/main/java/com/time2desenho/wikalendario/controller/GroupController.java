package com.time2desenho.wikalendario.controller;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.time2desenho.wikalendario.dao.GroupDatabaseHelper;
import com.time2desenho.wikalendario.model.Group;
import com.time2desenho.wikalendario.model.User;
import com.time2desenho.wikalendario.util.ManyToManyDAOFacade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupController {

    private ArrayList<Group> groups;
    private GroupDatabaseHelper groupDatabaseHelper;
    private Dao<Group, Long> groupDao;

    public GroupController(Context context){
        groupDatabaseHelper = new GroupDatabaseHelper(context);

        try {
            groupDao = groupDatabaseHelper.getDAO();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public void createGroup(Group group) throws IllegalArgumentException{
        try {
            groupDao.create(group);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Group readGroup(Long id, Context context){
        Group group = new Group();
        try {
            group = groupDao.queryForId(id);
            ManyToManyDAOFacade manyToManyDAOFacade = new ManyToManyDAOFacade(context);
            List<User> users = manyToManyDAOFacade.getUsers(group);
            group.setUsers(users);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    public void updateGroup(Group group, Long id){
        try {
            group.setId(id);
            groupDao.update(group);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteGroup(Group group){
        try {
            groupDao.delete(group);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
