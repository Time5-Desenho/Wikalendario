package com.time2desenho.wikalendario;

import android.util.Log;

public class Subject extends Class {

    private static final String REGEX = "\\d+";
    private static final String TAG = "Subject";

    private Long idSubject;
    private String name;
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

    @Override
    public String toString() {
        return getName();
    }
}
