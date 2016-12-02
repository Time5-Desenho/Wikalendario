package com.time2desenho.wikalendario.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.controller.EventsController;

public class EventsActivity extends AppCompatActivity {

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

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Eventos");
    }
}
