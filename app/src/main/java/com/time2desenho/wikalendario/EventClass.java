package com.time2desenho.wikalendario;


import com.j256.ormlite.field.DatabaseField;

public class EventClass{
    // Attributes.
    @DatabaseField
    private String eventTitle;

    @DatabaseField
    private String eventDescription;

    @DatabaseField
    private String eventClass;

    @DatabaseField
    private String eventDate;

    public static final String CLASS_ID_FIELD_NAME = "class_id";

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = CLASS_ID_FIELD_NAME)
    private Class eventClass1;

    public EventClass(String eventTitle, String eventDescription, String eventClass, String eventDate) {
        setEventClassTitle(eventTitle);
        setEventClassDescription(eventDescription);
        setEventClass(eventClass);
        setEventClassDate(eventDate);
    }

    public EventClass() {
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

    public String getEventClassDate() {
        return eventDate;
    }

    public void setEventClassDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void showEventClassToUser(){
        //TO DO
    }
}
