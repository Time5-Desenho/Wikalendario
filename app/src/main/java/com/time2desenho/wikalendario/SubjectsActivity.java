package com.time2desenho.wikalendario;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.util.ArrayList;

public class SubjectsActivity extends AppCompatActivity {

    private ArrayList<Subject> subjects;
    protected RecyclerView recyclerView;
    private final Context context = this;
    private SubjectDatabaseHelper subjectDAO;

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public Context getContext(){
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_subjects);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.subjects);

        setSubjects(CSVReader.getSubjects(this, R.raw.lista));

        try {
            SubjectDatabaseHelper subjectDatabaseHelper = new SubjectDatabaseHelper(getContext());
            Dao<Subject, Long> subjectDao = subjectDatabaseHelper.getDAO();
            for (Subject subject : subjects) {
                subjectDao.createOrUpdate(subject);
            }
        }catch (java.sql.SQLException e){
            e.printStackTrace();
        }

        ClassReader.getSubjects(this, R.raw.turma);

//        subjectDAO = new SubjectDatabaseHelper();
//        setSubjects(subjectDAO.getSubjects());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

    }

    @Override
    protected void onResume(){
        super.onResume();
        recyclerView.setAdapter(new SubjectAdapter(this, subjects, onClickSubject()));
    }

    public SubjectAdapter.SubjectOnClickListener onClickSubject(){
        return new SubjectAdapter.SubjectOnClickListener(){
            @Override
            public void onClickSubject(View view, int position){
                Subject subject = subjects.get(position);

                Intent intent = new Intent(getContext(), SubjectActivity.class);
                intent.putExtra("name", subject.getName());
                intent.putExtra("code", subject.getCode());
                startActivity(intent);

                Toast.makeText(getContext(),"Subject: " + subject.getCode() + ":" + subject.getName()
                        , Toast.LENGTH_SHORT).show();
            }
        };
    }
}
