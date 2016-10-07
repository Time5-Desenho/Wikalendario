package com.time2desenho.wikalendario;

import android.util.Log;

public class Subject {

    private static final String REGEX = "\\d+";
    private static final String TAG = "Subject";

    private Long id;
    private String name;
    private String code;

    public Subject(){

    }

    public Subject(String code, String name){
        setCode(code);
        setName(name);
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

}
