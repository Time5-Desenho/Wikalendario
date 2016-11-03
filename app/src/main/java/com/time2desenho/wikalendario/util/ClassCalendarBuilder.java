package com.time2desenho.wikalendario.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.roomorama.caldroid.CaldroidListener;
import com.time2desenho.wikalendario.activity.DayActivity;

import java.util.Date;
import java.util.HashMap;

public class ClassCalendarBuilder extends CalendarBuilder {

    @Override
    protected HashMap<Date, Drawable> createPaintedDates(final Context context){
        HashMap<Date, Drawable> paintedDates = new HashMap<>();
        //TODO Devolver eventos da classe
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
