package com.time2desenho.wikalendario.model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.lang.*;
import java.util.Date;

import static com.time2desenho.wikalendario.model.Class.CLASS_ID;
import static com.time2desenho.wikalendario.model.Event.EVENTS_TABLE;

@DatabaseTable(tableName = EVENTS_TABLE)
public class Event {
    // Attributes.

    public final static String EVENTS_TABLE = "events";
    public final static String EVENT_ID = "event_id";
    public final static String TITLE = "event_title";
    public final static String DESCRIPTION = "description";
    public final static String DATE = "date";

    @DatabaseField(generatedId = true, columnName = EVENT_ID)
    Long id;

    @DatabaseField(columnName = TITLE)
    private String title;

    @DatabaseField(columnName = DESCRIPTION)
    private String description;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = CLASS_ID)
    private Class eventClass;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = Group.GROUP_ID)
    private Group group;

    @DatabaseField(columnName = DATE)
    private Date date;

    public Event(String title, String description, Class eventClass, Group group, Date date) {
        setTitle(title);
        setDescription(description);
        setEventClass(eventClass);
        setGroup(group);
        setDate(date);
    }

    public Event(String title, String description, Class eventClass, Date date) {
        this(title, description, eventClass, null, date);
    }

    public Event() {
        this("Titulo", "Descrição", null, new Date());
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Class getEventClass() {
        return eventClass;
    }

    public void setEventClass(Class eventClass) {
        this.eventClass = eventClass;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
