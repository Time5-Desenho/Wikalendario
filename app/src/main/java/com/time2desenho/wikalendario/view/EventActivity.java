package com.time2desenho.wikalendario.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.DialogFragment;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.controller.EventsController;
import com.time2desenho.wikalendario.controller.NotificationController;
import com.time2desenho.wikalendario.dao.SubjectDatabaseHelper;
import com.time2desenho.wikalendario.model.Class;
import com.time2desenho.wikalendario.model.Event;
import com.time2desenho.wikalendario.model.Group;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {

    // Variables to this class.
    private EditText etEventTitle;
    private EditText etEventDescription;
    private TextView etEventClass;
    private TextView etEventSubject;
    private TextView etEventDate;
    private Button eventCreate;
    private TextWatcher textWatcher;
    private Date date;
    private boolean group = false;

    private TextView switchGroup;
    private Switch switchAux;

    private Event event;
    private EventsController eventsController;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        initViews();

        setCurrentDate(etEventDate);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        NotificationController notificationController = new NotificationController(context);

        eventsController = new EventsController(context);
        // Adding a observer that notifies new event
        eventsController.attachObserver(notificationController);
        eventsController.setCurrentClass(Long.valueOf(id), context);

        etEventClass.setText(eventsController.getCurrentClass().getLetter() + " - " + eventsController.getCurrentClass().getTeacher());
        etEventSubject.setText(eventsController.getCurrentClass().getSubject().getName());

        switchAux.setChecked(true);
        //attach a listener to check for changes in state
        switchAux.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    switchGroup.setText("Seu evento é de grupo!");
                    group = true;
                }else{
                    switchGroup.setText("Seu evento é público");
                    group=false;
                }

            }
        });

        if(switchAux.isChecked()){
            switchGroup.setText("Seu evento é de grupo!");
            group = true;
        }else{
            switchGroup.setText("Seu evento é público");
            group=false;
        }

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Criar Evento");
    }

    private void initViews() {
        etEventTitle = (EditText)findViewById(R.id.eventTitle);

        etEventDescription = (EditText)findViewById(R.id.eventDescription);

        etEventClass = (TextView) findViewById(R.id.eventClass);

        etEventSubject = (TextView) findViewById(R.id.eventSubject);

        etEventDate = (TextView)findViewById(R.id.eventDate);

        eventCreate = (Button)findViewById(R.id.eventCreate);

        switchAux = (Switch) findViewById(R.id.switchGroup);

        switchGroup = (TextView) findViewById(R.id.switchStatus);

        eventCreate.setOnClickListener(this);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void setCurrentDate(TextView textView){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        textView.setText(day + " / " + month + " / " + year);
    }

    @Override
    public void onClick(View view) {
        Event event = new Event();

        if(group){
            //TODO colocar grupo certo do cara, como faremos isso é um dos muitos mistérios da vida
            Group group = new Group();
            event.setGroup(group);
        }

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = (Date)formatter.parse(etEventDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        event.setTitle(etEventTitle.getText().toString());
        event.setDescription(etEventDescription.getText().toString());
        event.setEventClass(eventsController.getCurrentClass());
        event.setDate(date);

        Toast.makeText(this, "Evento Criado!", Toast.LENGTH_SHORT).show();
        finish();

        SubjectDatabaseHelper s = new SubjectDatabaseHelper(this);

        /*try {
        //TODO salvar na turma correta

        } catch (SQLException e) {
           e.printStackTrace();
        }*/
    }

}
