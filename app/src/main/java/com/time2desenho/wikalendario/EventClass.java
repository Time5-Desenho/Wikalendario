package com.time2desenho.wikalendario;


public class EventClass{
    // Attributes.
    private String eventTitle;
    private String eventDescription;
    private String eventClass;
    private String eventDate;

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
