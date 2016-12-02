package com.time2desenho.wikalendario.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.controller.ClassController;
import com.time2desenho.wikalendario.model.Class;
import com.time2desenho.wikalendario.model.Subject;

import static com.time2desenho.wikalendario.R.id.recyclerView;
import static com.time2desenho.wikalendario.R.string.show_code;

public class SubjectActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ClassController classController;
    private Context context = this;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject2);

        classController = new ClassController(context);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String code = intent.getStringExtra("code");
        String id = intent.getStringExtra("id");

        classController.setCurrentSubject(Long.valueOf(id));

        for (Class mclass : classController.getSubjectClasses()) {
            Log.d("CLASSES", mclass.getLetter() + " " + mclass.getSubject().getName() + " " + mclass.getTeacher());
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);


        //    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //    setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Disciplina");

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onResume(){
        super.onResume();
        recyclerView.setAdapter(new ClassAdapter(this, classController.getSubjectClasses(), onClickClass()));
    }

    public ClassAdapter.ClassOnClickListener onClickClass(){
        return new ClassAdapter.ClassOnClickListener(){
            @Override
            public void onClickClass(View view, int position){
                 Class mClass = classController.getSubjectClasses().get(position);

                Intent intent = new Intent(getContext(), EventsActivity.class);
                intent.putExtra("id", String.valueOf(mClass.getIdClass()));
                startActivity(intent);

                /**Toast.makeText(getContext(),"Class: " + mClass.getLetter() + ":" + mClass.getTeacher()
                        , Toast.LENGTH_SHORT).show();*/
            }
        };
    }

    public Context getContext() {
        return this;
    }

    @Override
    public void onStart() {
        super.onStart();

        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Subject Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.time2desenho.wikalendario/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Subject Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.time2desenho.wikalendario/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
