package com.time2desenho.wikalendario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SubjectDAO {

    private ArrayList<Subject> subjects;
    private FirebaseDatabase database;

    public SubjectDAO(){
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference("subjects");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                GenericTypeIndicator<ArrayList<Subject>> sub = new GenericTypeIndicator<ArrayList<Subject>>() {};
                setSubjects(dataSnapshot.getValue(sub));
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
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

}

