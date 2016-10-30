package com.time2desenho.wikalendario;

import java.util.Date;

/**
 * Created by arthur on 30/10/16.
 */

public class Event {
    // Attributes.
    private String eventTitle;
    private String eventDescription;
    private String eventClass;
    private Date eventDate;

    public Event(String eventTitle, String eventDescription, String eventClass, Date eventDate) {
        setEventTitle(eventTitle);
        setEventDescription(eventDescription);
        setEventClass(eventClass);
        setEventDate(eventDate);
    }

    public Event() {
        setEventTitle("Titulo");
        setEventDescription("Descrição");
        setEventClass("Turma");
        //setEventDate(new Date("2016-01-01"));
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventClass() {
        return eventClass;
    }

    public void setEventClass(String eventClass) {
        this.eventClass = eventClass;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
}
