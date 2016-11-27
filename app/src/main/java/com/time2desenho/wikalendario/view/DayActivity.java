package com.time2desenho.wikalendario.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.model.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DayActivity extends AppCompatActivity {

    private ArrayList<Event> events;
    protected RecyclerView recyclerView;
    private final Context context = this;
    //private EventDAO eventDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //setEvents(CSVReader.getEvents(this, R.raw.lista));

        Intent intent = getIntent();
        Date date = (Date) intent.getSerializableExtra("date");
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yy");

        actionBar.setTitle(getResources().getString(R.string.events_day) + " " + s.format(date));

        //ClassReader.getEvents(this, R.raw.turma);

//        eventDAO = new EventDAO();
//        setEvents(eventDAO.getEvents());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerEventView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

    }



    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public Context getContext(){
        return context;
    }

    @Override
    protected void onResume(){
        super.onResume();
        recyclerView.setAdapter(new EventAdapter(this, events, onClickEvent()));
    }

    public EventAdapter.EventOnClickListener onClickEvent(){
        return new EventAdapter.EventOnClickListener(){
            @Override
            public void onClickEvent(View view, int position){
                Event eventClass = events.get(position);

                Intent intent = new Intent(getContext(), EventActivity.class);
                intent.putExtra("title", eventClass.getTitle());
                intent.putExtra("description", eventClass.getDescription());
                intent.putExtra("date", eventClass.getDate());

                startActivity(intent);

                Toast.makeText(getContext(),"Event: " + eventClass.getTitle() + ":" + eventClass.getDescription()
                        , Toast.LENGTH_SHORT).show();
            }
        };
    }
}
