package com.time2desenho.wikalendario;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

import static com.time2desenho.wikalendario.Class.SUBJECT_ID_FIELD_NAME;

@DatabaseTable(tableName = "classes")
public class Class implements Event{

    @DatabaseField(generatedId = true)
    private Long idClass;

    public static final String SUBJECT_ID_FIELD_NAME = "subject_id";

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = SUBJECT_ID_FIELD_NAME)
    private Subject subject;

    @DatabaseField
    private String letter;

    @DatabaseField
    private String teacher;

    //TODO classe não possui evento de grupo
    @ForeignCollectionField
    private ForeignCollection<EventClass> eventsClass;

    @ForeignCollectionField
    private ForeignCollection<EventGroup> eventsGroup;

    public Class(){

    }

    public Class(Long idClass, String letter, String teacher) {
        this.idClass = idClass;
        this.letter = letter;
        this.teacher = teacher;
    }


    public void addEvents(EventGroup event){
        this.getEventsGroup().add(event);
    }

    public void addEvents(EventClass event){
        this.getEventsClass().add(event);
    }

    public Long getIdClass() {
        return idClass;
    }

    public void setIdClass(Long idClass) {
        this.idClass = idClass;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
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

    public ForeignCollection<EventClass> getEventsClass() {
        return eventsClass;
    }

    public void setEventsClass(ForeignCollection<EventClass> eventsClass) {
        this.eventsClass = eventsClass;
    }

    public ForeignCollection<EventGroup> getEventsGroup() {
        return eventsGroup;
    }

    public void setEventsGroup(ForeignCollection<EventGroup> eventsGroup) {
        this.eventsGroup = eventsGroup;
    }

    @Override
    public void showEventToUser() {
        //TO DO
    }
}
