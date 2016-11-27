package com.time2desenho.wikalendario.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.j256.ormlite.dao.Dao;
import com.roomorama.caldroid.CaldroidListener;
import com.time2desenho.wikalendario.view.DayActivity;
import com.time2desenho.wikalendario.dao.EventDatabaseHelper;
import com.time2desenho.wikalendario.model.Event;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FullCalendarBuilder extends CalendarBuilder {

    @Override
    protected HashMap<Date, Drawable> createPaintedDates(final Context context){
        HashMap<Date, Drawable> paintedDates = new HashMap<>();

        //FIXME isso tá MUITO errado, colocar eventos certos
        ColorDrawable yellow = new ColorDrawable(Color.YELLOW);
        ColorDrawable blue = new ColorDrawable(Color.BLUE);
        Log.d("Entrou", "entrou aqui");

        try {
            EventDatabaseHelper eventDatabaseHelper = new EventDatabaseHelper(context);
            Dao<Event, Long> eventDAO = eventDatabaseHelper.getDAO();
            boolean aux = false;

            //FIXME só quero os do usuário
            List<Event> events = eventDAO.queryForAll();
            Log.d("Size", "size da lista de eventos: " + events.size());
            for(Event event : events){
                paintedDates.put(event.getDate(), aux ? yellow : blue);
                aux = !aux;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        //TODO Devolver TODOS eventos
        return paintedDates;
    }

    @Override
    protected CaldroidListener createCaldroidListener(final Context context){
        final CaldroidListener caldroidListener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {

                Intent intent = new Intent(context, DayActivity.class);
                intent.putExtra("date", date);

                context.startActivity(intent);
            }
        };

        return caldroidListener;
    }
}
