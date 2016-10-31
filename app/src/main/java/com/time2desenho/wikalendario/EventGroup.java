package com.time2desenho.wikalendario;


public class EventGroup {
    // Attributes.
    private String eventTitle;
    private String eventDescription;
    private String eventClass;
    private String eventDate;

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
