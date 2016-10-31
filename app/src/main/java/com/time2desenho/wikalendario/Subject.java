package com.time2desenho.wikalendario;

import android.util.Log;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

@DatabaseTable(tableName = "subjects")
public class Subject extends Class {

    private static final String REGEX = "\\d+";
    private static final String TAG = "Subject";

    @DatabaseField(generatedId = true)
    private Long idSubject;

    @DatabaseField
    private String name;

    @ForeignCollectionField
    private ForeignCollection<Class> classes;

    @DatabaseField
    private String code;

    public Subject(String code, String name) {
        setCode(code);
        setName(name);
    }

    public Subject(Long idClass, String letter, String teacher, Long idSubject, String name, String code) {
        super(idClass, letter, teacher);
        this.idSubject = idSubject;
        this.name = name;
        this.code = code;
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

    public Long getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Long idSubject) {
        this.idSubject = idSubject;
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
