package com.time2desenho.wikalendario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {

    // Attributes.
    private String eventTitle;
    private String eventDescription;
    private String eventSubject;
    private Date eventDate;

    // Variables to this class.
    private EditText etEventTitle;
    private EditText etEventDescription;
    private EditText etEventSubject;
    private EditText etEventDate;
    private Button eventCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        init();
    }

    private void init() {
        etEventTitle = (EditText)findViewById(R.id.eventTitle);
        etEventDescription = (EditText)findViewById(R.id.eventDescription);
        etEventSubject = (EditText)findViewById(R.id.eventSubject);
        //etEventDate = (EditText)findViewById(R.id.eventDate);

        eventCreate = (Button)findViewById(R.id.eventCreate);

        eventCreate.setOnClickListener(this);
    }


    public void onClick(View view) {
        setEventTitle(etEventTitle.getText().toString());
        setEventDescription(etEventDescription.getText().toString());
        setEventSubject(etEventSubject.getText().toString());

        //eventDate = new Date(etEventDate.getText().toString());

        //System.out.println(getEventTitle() + "\n" + getEventDescription() + "\n" + getEventSubject() + "\n" + getEventDate());
        System.out.println(getEventTitle() + "\n" + getEventDescription() + "\n" + getEventSubject());
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

    public String getEventSubject() {
        return eventSubject;
    }

    public void setEventSubject(String eventSubject) {
        this.eventSubject = eventSubject;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
}
