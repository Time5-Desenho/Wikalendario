package com.time2desenho.wikalendario.controller;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.dao.SubjectDatabaseHelper;
import com.time2desenho.wikalendario.model.Subject;

import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectController {

    private ArrayList<Subject> subjects;
    private SubjectDatabaseHelper subjectDatabaseHelper;
    private Dao<Subject, Long> subjectDao;

    public SubjectController(Context context){
         subjectDatabaseHelper = new SubjectDatabaseHelper(context);

        try {
            subjectDao = subjectDatabaseHelper.getDAO();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Subject> getSubjects() {
        ArrayList subjectsList = new ArrayList<Subject>();

        for (Subject subject : subjectDao) {
            subjectsList.add(subject);
        }

        setSubjects(subjectsList);

        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public void setSubjectsListOnDatabase(Context context) {

        setSubjects(CSVReader.getSubjects(context, R.raw.lista));

        try {
            for (Subject subject : subjects) {
                subjectDao.createOrUpdate(subject);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

}
