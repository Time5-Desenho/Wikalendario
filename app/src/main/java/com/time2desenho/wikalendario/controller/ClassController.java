package com.time2desenho.wikalendario.controller;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.dao.ClassDatabaseHelper;
import com.time2desenho.wikalendario.model.Class;
import com.time2desenho.wikalendario.model.Subject;

import java.sql.SQLException;
import java.util.ArrayList;

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
}
