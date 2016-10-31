package com.time2desenho.wikalendario;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "eventsGroup")
public class EventGroup {
    // Attributes.

    @DatabaseField(generatedId = true)
    private Long id;

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

    public EventGroup(String eventTitle, String eventDescription, String eventClass, String eventDate) {
        setEventGroupTitle(eventTitle);
        setEventGroupDescription(eventDescription);
        setEventGroupClass(eventClass);
        setEventGroupDate(eventDate);
    }

    public EventGroup() {
        setEventGroupTitle("Titulo");
        setEventGroupDescription("Descrição");
        setEventGroupClass("Turma");
        setEventGroupDate("Data");
    }

    public String getEventGroupTitle() {
        return eventTitle;
    }

    public void setEventGroupTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventGroupDescription() {
        return eventDescription;
    }

    public void setEventGroupDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventGroupClass() {
        return eventClass;
    }

    public void setEventGroupClass(String eventClass) {
        this.eventClass = eventClass;
    }

    public String getEventGroupDate() {
        return eventDate;
    }

    public void setEventGroupDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void showEventGroupToUser(){
        //TO DO
    }
}
