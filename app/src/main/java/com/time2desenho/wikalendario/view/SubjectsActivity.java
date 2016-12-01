package com.time2desenho.wikalendario.view;

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
import com.time2desenho.wikalendario.controller.CSVReader;
import com.time2desenho.wikalendario.controller.ClassReader;
import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.controller.SubjectController;
import com.time2desenho.wikalendario.dao.SubjectDatabaseHelper;
import com.time2desenho.wikalendario.model.Subject;

import java.util.ArrayList;

public class SubjectsActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    private final Context context = this;
    private SubjectController subjectController;


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

        subjectController = new SubjectController(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

    }

    @Override
    protected void onResume(){
        super.onResume();
        recyclerView.setAdapter(new SubjectAdapter(this, subjectController.getSubjects(), onClickSubject()));
    }

    public SubjectAdapter.SubjectOnClickListener onClickSubject(){
        return new SubjectAdapter.SubjectOnClickListener(){
            @Override
            public void onClickSubject(View view, int position){
                Subject subject = subjectController.getSubjects().get(position);

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
