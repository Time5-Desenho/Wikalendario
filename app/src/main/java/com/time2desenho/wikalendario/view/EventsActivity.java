package com.time2desenho.wikalendario.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.controller.EventsController;
import com.time2desenho.wikalendario.model.Event;

public class EventsActivity extends AppCompatActivity {

    private Button newEvent;
    private EventsController eventsController;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        eventsController = new EventsController(context);

        eventsController.setCurrentClass(Long.valueOf(id), context);

        newEvent = (Button) findViewById(R.id.new_event);
        newEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventsActivity.this, EventActivity.class);
                intent.putExtra("id", eventsController.getCurrentClass().getIdClass());
                startActivity(intent);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Eventos");
    }
}
