package com.time2desenho.wikalendario;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubjectsActivity extends AppCompatActivity {

    private ArrayList<Subject> subjects;
    protected RecyclerView recyclerView;
    private final Context context = this;
    private SubjectDAO subjectDAO;

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

//        subjectDAO = new SubjectDAO();
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
