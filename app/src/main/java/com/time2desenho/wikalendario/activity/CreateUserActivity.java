package com.time2desenho.wikalendario.activity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.dao.ClassDatabaseHelper;
import com.time2desenho.wikalendario.dao.DatabaseHelper;
import com.time2desenho.wikalendario.model.Class;
import com.time2desenho.wikalendario.util.UserHelper;
import com.time2desenho.wikalendario.dao.UserDatabaseHelper;
import com.time2desenho.wikalendario.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;

import static com.time2desenho.wikalendario.dao.DatabaseHelper.DATABASE_NAME;

public class CreateUserActivity extends AppCompatActivity {

    private UserHelper helper;
    private Button confirmationButton;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        helper = new UserHelper(this);

        Intent intent = getIntent();
        String mUsername = (String) intent.getSerializableExtra("username");
        if(mUsername != null){
            helper.fillForm(new User(mUsername));
        }

        context = this;
        confirmationButton = (Button) findViewById(R.id.confirmation_button);
        confirmationButton.setOnClickListener(onConfirmationButtonClick());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sigin, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sigin_menu:
                createUser();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener onConfirmationButtonClick(){
        View.OnClickListener listener = new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                createUser();
            }
        };

        return listener;
    }

    private void createUser() {
        User user = helper.getUser();

        try {
            Class testClass = new Class();

            Log.d("Create", "creating");
            UserDatabaseHelper userDatabaseHelper = new UserDatabaseHelper(this);

            Dao<User, Long> userDAO = userDatabaseHelper.getDAO();
            userDAO.create(user);
            Log.d("Create", "created");

            Log.d("Create", "creating class1");
            ClassDatabaseHelper classDatabaseHelper = new ClassDatabaseHelper(this);
            Dao<Class, Long> classDAO = classDatabaseHelper.getDAO();
            classDAO.createOrUpdate(testClass);
            Log.d("Create", "created class1");

            //TODO tirar esse usuário de teste
            User firstUser = new User("joao", "joao18araujo","joao18araujo@gmail.com", "12345678");
            firstUser.setId(1L);
            userDAO.createOrUpdate(firstUser);
        } catch (SQLException e){
            Throwable code = e.getCause();
            String error = code.getCause().getMessage();
            Log.d("CPD", error);
        }

        Intent goToForm = new Intent(CreateUserActivity.this, MainActivity.class);
        goToForm.putExtra("user", user);
        startActivity(goToForm);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
