package com.time2desenho.wikalendario.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.lang.*;

import static com.time2desenho.wikalendario.model.Class.CLASS_ID;
import static com.time2desenho.wikalendario.model.Group.GROUPS_TABLE;

@DatabaseTable(tableName = GROUPS_TABLE)
public class Group {

    public final static String GROUPS_TABLE = "groups";
    public final static String GROUP_ID = "group_id";
    public final static String GROUP_NAME = "group_name";
    public final static String GROUP_EVENTS = "group_events";


    @DatabaseField(generatedId = true, columnName = GROUP_ID)
    private Long id;

    @DatabaseField(columnName = GROUP_NAME)
    private String name;

    @ForeignCollectionField(columnName = GROUP_EVENTS)
    private ForeignCollection<Event> events;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = CLASS_ID)
    private Class groupClass;

    //TODO associar com user n x n
    //TODO criar construtores

    public Group(){

    }

    public Group(String name, Class groupClass){
        setName(name);
        setGroupClass(groupClass);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ForeignCollection<Event> getEvents() {
        return events;
    }

    public void setEvents(ForeignCollection<Event> events) {
        this.events = events;
    }

    public Class getGroupClass() {
        return groupClass;
    }

    public void setGroupClass(Class groupClass) {
        this.groupClass = this.groupClass;
    }
}
