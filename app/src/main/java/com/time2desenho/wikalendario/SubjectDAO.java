package com.time2desenho.wikalendario;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {

    private ArrayList<Subject> subjects;
    private FirebaseDatabase database;

    public SubjectDAO(){
        database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference("subjects");

        dbRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                GenericTypeIndicator<List<Subject>> sub = new GenericTypeIndicator<List<Subject>>() {};
                List<Subject> s = dataSnapshot.getValue(sub);
                setSubjects((ArrayList<Subject>) dataSnapshot.getValue(sub));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                error.toException();
            }
        });
    }

    public void update(){
        DatabaseReference dbRef = database.getReference("subjects");
        dbRef.setValue(subjects);
    }

    public ArrayList<Subject> getSubjects() {
        assert subjects != null;
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

}

