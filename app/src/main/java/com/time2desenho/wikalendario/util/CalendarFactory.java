package com.time2desenho.wikalendario.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public abstract class CalendarFactory {
    public CaldroidFragment createCalendarFragment(final Context context){
        CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle bundle = new Bundle();
        Calendar calendar = Calendar.getInstance();
        bundle.putInt(CaldroidFragment.MONTH, calendar.get(Calendar.MONTH) + 1);
        bundle.putInt(CaldroidFragment.YEAR, calendar.get(Calendar.YEAR));
        caldroidFragment.setArguments(bundle);

        HashMap<Date, Drawable> paintedDates = createPaintedDates();

        caldroidFragment.setBackgroundDrawableForDates(paintedDates);

        CaldroidListener caldroidListener = createCaldroidListener(context);

        caldroidFragment.setCaldroidListener(caldroidListener);

        return caldroidFragment;
    }

    protected abstract HashMap<Date, Drawable> createPaintedDates();
    protected abstract CaldroidListener createCaldroidListener(final Context context);
}
