package com.time2desenho.wikalendario;

import java.util.ArrayList;
import java.util.List;

public class Class implements Event{

    private Long idClass;
    private String letter;
    private String teacher;

    private List<EventClass> eventsClass = new ArrayList<>();
    private List<EventGroup> eventsGroup = new ArrayList<>();


    public Class(){

    }

    public Class(Long idClass, String letter, String teacher) {
        this.idClass = idClass;
        this.letter = letter;
        this.teacher = teacher;
    }

    public void addEvents(EventGroup event){
        this.eventsGroup.add(event);
    }

    public void addEvents(EventClass event){
        this.eventsClass.add(event);
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

    @Override
    public void showEventToUser() {
        //TO DO
    }
}
