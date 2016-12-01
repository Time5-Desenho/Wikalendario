package com.time2desenho.wikalendario.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.widget.TextView;

import com.time2desenho.wikalendario.R;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        TextView textViewDate = (TextView) getActivity().findViewById(R.id.eventDate);

        textViewDate.setText(view.getDayOfMonth() + " / " + view.getMonth() + " / " + view.getYear());
    }
}

