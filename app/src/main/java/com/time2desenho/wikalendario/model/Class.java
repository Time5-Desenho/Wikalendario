package com.time2desenho.wikalendario.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import static com.time2desenho.wikalendario.model.Class.CLASSES_TABLE;
import static com.time2desenho.wikalendario.model.Subject.SUBJECT_ID;

@DatabaseTable(tableName = CLASSES_TABLE)
public class Class{

    public final static String CLASSES_TABLE = "classes";
    public final static String CLASS_ID = "class_id";
    public final static String LETTER = "letter";
    public final static String TEACHER = "teacher";
    public final static String CLASS_EVENTS = "class_events";
    public final static String CLASS_GROUPS = "class_groups";

    @DatabaseField(generatedId = true, columnName = CLASS_ID)
    private Long id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = SUBJECT_ID)
    private Subject subject;

    @DatabaseField(columnName = LETTER)
    private String letter;

    @DatabaseField(columnName = TEACHER)
    private String teacher;

    @ForeignCollectionField(columnName = CLASS_EVENTS)
    private ForeignCollection<Event> events;

    @ForeignCollectionField(columnName = CLASS_GROUPS)
    private ForeignCollection<Group> groups;

    public Class(){

    }

    public Class(String letter, String teacher) {
        this.letter = letter;
        this.teacher = teacher;
    }


    public void addEvent(Event event){
        this.getEvents().add(event);
    }

    public Long getIdClass() {
        return id;
    }

    public void setIdClass(Long idClass) {
        this.id = idClass;
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

    public ForeignCollection<Event> getEvents() {
        return events;
    }

    public void setEvents(ForeignCollection<Event> events) {
        this.events = events;
    }

    public ForeignCollection<Group> getGroups() {
        return groups;
    }

    public void setGroups(ForeignCollection<Group> groups) {
        this.groups = groups;
    }
}
