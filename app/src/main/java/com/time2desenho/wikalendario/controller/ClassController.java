package com.time2desenho.wikalendario.controller;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.dao.ClassDatabaseHelper;
import com.time2desenho.wikalendario.model.Class;
import com.time2desenho.wikalendario.model.User;
import com.time2desenho.wikalendario.util.ManyToManyDAOFacade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassController {

    private ArrayList<Class> classes;
    private ClassDatabaseHelper classDatabaseHelper;
    private Dao<Class, Long> classDao;
    private Long currentSubjectId;

    public ClassController(Context context){
        classDatabaseHelper = new ClassDatabaseHelper(context);

        try {
            classDao = classDatabaseHelper.getDAO();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Long getCurrentSubjectId() {
        return currentSubjectId;
    }

    public void setCurrentSubject(Long currentSubjectId) {
        this.currentSubjectId = currentSubjectId;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }

    public void setClassesListOnDatabase(Context context) {

        setClasses(ClassReader.getClasses(context, R.raw.turma));

        try {
            for (Class mclass : classes) {
                classDao.createOrUpdate(mclass);
                Log.d("CLASSES", "Set " + mclass.getIdClass() + " " + mclass.getLetter() + " "+ mclass.getSubject().getName() + " on database");
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Class> getSubjectClasses(){
        ArrayList<Class> classesList = null;

        try {
            classesList = (ArrayList<Class>) classDao.queryForEq("subject_id", currentSubjectId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classesList;
    }

    public void createClass(Class classes) throws IllegalArgumentException{
        try {
            classDao.create(classes);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Class readClass(Long id, Context context){
        Class classes = new Class();
        try {
            classes = classDao.queryForId(id);
            ManyToManyDAOFacade manyToManyDAOFacade = new ManyToManyDAOFacade(context);
            List<User> users = manyToManyDAOFacade.getUsers(classes);
            classes.setUsers(users);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    public void updateClass(Class classes, Long id){
        try {
            classes.setIdClass(id);
            classDao.update(classes);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteClass(Class classes) {
        try {
            classDao.delete(classes);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public Class getClass(Long id) {
        List<Class> classes = null;

        try {
            classes = classDao.queryForEq("class_id", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classes.get(0);
    }
}
