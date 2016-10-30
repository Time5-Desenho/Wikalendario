package com.time2desenho.wikalendario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {

    // Variables to this class.
    private EditText etEventTitle;
    private EditText etEventDescription;
    private EditText etEventClass;
    private EditText etEventDate = null;
    private Button eventCreate;

    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        event = new Event();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        init();
    }

    private void init() {
        etEventTitle = (EditText)findViewById(R.id.eventTitle);
        etEventDescription = (EditText)findViewById(R.id.eventDescription);
        etEventClass = (EditText)findViewById(R.id.eventClass);
        etEventDate = (EditText)findViewById(R.id.eventDate);

        eventCreate = (Button)findViewById(R.id.eventCreate);

        eventCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        event.setEventTitle(etEventTitle.getText().toString());
        event.setEventDescription(etEventDescription.getText().toString());
        event.setEventClass(etEventClass.getText().toString());

        //Date auxDate = new Date(etEventDate.getText().toString());
        //event.setEventDate(auxDate);

        //System.out.println(event.getEventTitle() + "\n" + event.getEventDescription() + "\n" +
        //        event.getEventClass() + "\n" + event.getEventDate());
        System.out.println(event.getEventTitle() + "\n" + event.getEventDescription() + "\n" + event.getEventClass());
    }

}
