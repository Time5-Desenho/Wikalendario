package com.time2desenho.wikalendario.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import com.roomorama.caldroid.CaldroidFragment;
import com.time2desenho.wikalendario.dao.ClassDatabaseHelper;
import com.time2desenho.wikalendario.dao.EventDatabaseHelper;
import com.time2desenho.wikalendario.model.Class;
import com.time2desenho.wikalendario.model.Event;
import com.time2desenho.wikalendario.model.Group;
import com.time2desenho.wikalendario.model.Subject;
import com.time2desenho.wikalendario.model.User;
import com.time2desenho.wikalendario.util.CalendarBuilder;
import com.time2desenho.wikalendario.util.FullCalendarBuilder;
import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.util.SessionSingleton;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.System.exit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private Context context;
    private TextView helloText;

    //TODO tirar essas DAOS
    Dao<Event, Long> eventsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        User user = SessionSingleton.getInstance(this).getLoggedUser(this);

        context = this;
        helloText = (TextView) findViewById(R.id.helloText);
        helloText.setText("Olá, " + user.getName());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        //TODO Colocar eventos de verdade
        try {
            Class testClass = new Class();

            Log.d("Create", "creating class");
            ClassDatabaseHelper classDatabaseHelper = new ClassDatabaseHelper(this);
            Dao<Class, Long> classDAO = classDatabaseHelper.getDAO();
            classDAO.createOrUpdate(testClass);
            Log.d("Create", "created class");

            EventDatabaseHelper eventDatabaseHelper = new EventDatabaseHelper(this);
            Dao<Event, Long> eventDAO = eventDatabaseHelper.getDAO();

            SimpleDateFormat s = new SimpleDateFormat("dd/MM/yy");

            Event event = new Event("EventoC1", "Teste1", testClass, s.parse("31/10/16"));
            eventDAO.createOrUpdate(event);

            event = new Event("EventoC2", "Teste2", testClass, s.parse("02/11/16"));
            eventDAO.createOrUpdate(event);

            event = new Event("EventoC3", "Teste3", testClass, s.parse("17/11/16"));
            eventDAO.createOrUpdate(event);

            event = new Event("EventoC4", "Teste4", testClass, s.parse("30/11/16"));
            eventDAO.createOrUpdate(event);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //Fragment do calendario
        CalendarBuilder calendarBuilder = new FullCalendarBuilder();
        CaldroidFragment caldroidFragment = calendarBuilder.createCalendarFragment(getContext());

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();
        //Fragment do calendario

        ((TextView) header.findViewById(R.id.userNameToolbar)).setText(user.getName());
        ((TextView) header.findViewById(R.id.userEmailToolbar)).setText(user.getEmail());
    }

    public Context getContext(){
        return context;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, EditUserActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.subjects) {
            Intent intent = new Intent(this, SubjectsActivity.class);
            startActivity(intent);
        } else if (id == R.id.activity_event) {
            Intent intent2 = new Intent(this, EventActivity.class);
            startActivity(intent2);
        }/* else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
