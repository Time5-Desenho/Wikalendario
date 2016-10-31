package com.time2desenho.wikalendario;

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

import java.util.ArrayList;
import java.util.Date;

public class DayActivity extends AppCompatActivity {

    private ArrayList<EventClass> eventClasses;
    private ArrayList<EventGroup> eventGroups;
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
        actionBar.setTitle(R.string.events_day);

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

        //TODO: Place real events
        eventClasses = new ArrayList<>();
        eventClasses.add(new EventClass("EventoC1", "Teste1", "Turma1", "01/10/2016"));
        eventClasses.add(new EventClass("EventoC2", "Teste2", "Turma2", "02/10/2016"));

        eventGroups= new ArrayList<>();
        eventGroups.add(new EventGroup("EventoG1", "Teste1", "Turma1", "01/10/2016"));
        eventGroups.add(new EventGroup("EventoG2", "Teste2", "Turma2", "02/10/2016"));

        //ClassReader.getEvents(this, R.raw.turma);

//        eventDAO = new EventDAO();
//        setEvents(eventDAO.getEvents());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerEventView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

    }



    public ArrayList<EventClass> getEventClasses() {
        return eventClasses;
    }

    public void setEventClasses(ArrayList<EventClass> eventClasses) {
        this.eventClasses = eventClasses;
    }

    public ArrayList<EventGroup> getEventGroups() {
        return eventGroups;
    }

    public void setEventGroups(ArrayList<EventGroup> eventGroups) {
        this.eventGroups = eventGroups;
    }

    public Context getContext(){
        return context;
    }

    @Override
    protected void onResume(){
        super.onResume();
        recyclerView.setAdapter(new EventAdapter(this, eventClasses, onClickEvent()));
    }

    public EventAdapter.EventOnClickListener onClickEvent(){
        return new EventAdapter.EventOnClickListener(){
            @Override
            public void onClickEvent(View view, int position){
                EventClass eventClass = eventClasses.get(position);

                Intent intent = new Intent(getContext(), EventActivity.class);
                intent.putExtra("title", eventClass.getEventClassTitle());
                intent.putExtra("description", eventClass.getEventClassDescription());
                intent.putExtra("date", eventClass.getEventClassDate());

                startActivity(intent);

                Toast.makeText(getContext(),"Event: " + eventClass.getEventClassTitle() + ":" + eventClass.getEventClassDescription()
                        , Toast.LENGTH_SHORT).show();
            }
        };
    }


}
