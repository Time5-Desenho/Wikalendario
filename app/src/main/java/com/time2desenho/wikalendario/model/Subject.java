package com.time2desenho.wikalendario.model;

import android.util.Log;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.lang.*;

import static com.time2desenho.wikalendario.model.Subject.SUBJECTS_TABLE;

@DatabaseTable(tableName = SUBJECTS_TABLE)
public class Subject {

    private static final String REGEX = "\\d+";
    private static final String TAG = "Subject";

    public final static String SUBJECTS_TABLE = "subjects";
    public final static String SUBJECT_ID = "subject_id";
    public final static String SUBJECT_NAME = "subject_name";
    public final static String SUBJECT_CLASSES = "subject_classes";
    public final static String CODE = "code";


    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField(columnName = SUBJECT_NAME)
    private String name;

    @ForeignCollectionField(columnName = SUBJECT_CLASSES)
    private ForeignCollection<Class> classes;

    @DatabaseField(columnName = CODE)
    private String code;

    public Subject(){

    }

    public Subject(String code, String name) {
        setCode(code);
        setName(name);
    }

    public void addClass(Class classUnity){
        this.classes.add(classUnity);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        Log.v(TAG, "Subject name set as " + name);
        assert !name.equals("");

        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        Log.v(TAG, "Subject code set as " + code);
        assert code.matches(REGEX);

        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ForeignCollection<Class> getClasses() {
        return classes;
    }

    public void setClasses(ForeignCollection<Class> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return getName();
    }
}
