package com.time2desenho.wikalendario;

public class Class {

    private Long idClass;
    private String letter;
    private String teacher;

    public Class(){

    }

    public Class(Long idClass, String letter, String teacher) {
        this.idClass = idClass;
        this.letter = letter;
        this.teacher = teacher;
    }

    public Long getIdClass() {
        return idClass;
    }

    public void setIdClass(Long idClass) {
        this.idClass = idClass;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
