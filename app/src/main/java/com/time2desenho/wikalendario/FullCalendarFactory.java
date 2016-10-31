package com.time2desenho.wikalendario;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class FullCalendarFactory extends CalendarFactory{

    @Override
    protected HashMap<Date, Drawable> createPaintedDates(){
        HashMap<Date, Drawable> paintedDates = new HashMap<>();
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
