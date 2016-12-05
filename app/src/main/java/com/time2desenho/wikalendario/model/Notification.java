package com.time2desenho.wikalendario.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.time2desenho.wikalendario.model.Notification.NOTIFICATION_TABLE;

@DatabaseTable(tableName = NOTIFICATION_TABLE)
public class Notification {
    public final static String NOTIFICATION_TABLE = "notifications";
    public final static String NOTIFICATION_ID = "notification_id";
    public final static String NOTIFICATION_TITLE = "notification_title";
    public final static String NOTIFICATION_DESCRIPTION = "notification_description";
    public final static String NOTIFICATION_DATE = "notification_date";

    @DatabaseField(generatedId = true, columnName = NOTIFICATION_ID)
    private int idNotification;

    @DatabaseField(columnName = NOTIFICATION_TITLE, canBeNull = false)
    private String title;

    @DatabaseField(columnName = NOTIFICATION_DESCRIPTION, canBeNull = false)
    private String description;

    @DatabaseField(columnName = NOTIFICATION_DATE, canBeNull = false)
    private String date;

    public Notification(){

    }

    public Notification(String title, String description, String date){
        setTitle(title);
        setDescription(description);
        setDate(date);
    }

    public boolean validateTitle(String title){
        if (title.length() > 0 && title.length() <= 80){
            return true;
        }else{
            return false;
        }
    }

    public boolean validateDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        try {
            format.parse(date);
        } catch(ParseException e) {
            return false;
        }
        return true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (validateTitle(title)) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Invalid title");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if (validateDate(date)) {
            this.date = date;
        } else {
            throw new IllegalArgumentException("Invalid date");
        }
    }

    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    public String toString(){
        return idNotification+" "+title+" "+description+" "+date;
    }
}

