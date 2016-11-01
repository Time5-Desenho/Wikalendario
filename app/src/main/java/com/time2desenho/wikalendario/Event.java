package com.time2desenho.wikalendario;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import static com.time2desenho.wikalendario.Event.EVENTS_TABLE;

@DatabaseTable(tableName = EVENTS_TABLE)
public class Event {
    // Attributes.

    public final static String EVENTS_TABLE = "events";
    public final static String TITLE = "event_title";
    public final static String DESCRIPTION = "description";
    public final static String DATE = "";



    @DatabaseField
    private String eventTitle;

    @DatabaseField
    private String eventDescription;

    @DatabaseField
    private String eventClass;

    @DatabaseField
    private Date eventDate;

    public static final String CLASS_ID_FIELD_NAME = "class_id";

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = CLASS_ID_FIELD_NAME)
    private Class eventClass1;

    public Event(String eventTitle, String eventDescription, String eventClass, String eventDate) {
        setEventClassTitle(eventTitle);
        setEventClassDescription(eventDescription);
        setEventClass(eventClass);
        setEventClassDate(eventDate);
    }

    public Event() {
        setEventClassTitle("Titulo");
        setEventClassDescription("Descrição");
        setEventClass("Turma");
        setEventClassDate("Data");
    }

    public String getEventClassTitle() {
        return eventTitle;
    }

    public void setEventClassTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventClassDescription() {
        return eventDescription;
    }

    public void setEventClassDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventClass() {
        return eventClass;
    }

    public void setEventClass(String eventClass) {
        this.eventClass = eventClass;
    }

    public Date getEventClassDate() {
        return eventDate;
    }

    public void setEventClassDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void showEventClassToUser(){
        //TO DO
    }
}
