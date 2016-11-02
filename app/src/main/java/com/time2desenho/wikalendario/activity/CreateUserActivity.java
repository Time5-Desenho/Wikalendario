package com.time2desenho.wikalendario.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.j256.ormlite.dao.Dao;
import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.util.UserHelper;
import com.time2desenho.wikalendario.dao.UserDatabaseHelper;
import com.time2desenho.wikalendario.model.User;

import java.sql.SQLException;

public class CreateUserActivity extends AppCompatActivity {

    private UserHelper helper;

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
                User user = helper.getUser();

                UserDatabaseHelper userDatabaseHelper = new UserDatabaseHelper(this);

                try {
                    Dao<User, Long> userDAO = userDatabaseHelper.getDAO();
                    userDAO.createOrUpdate(user);

                    User firstUser = new User("joao", "joao18araujo","joao18araujo@gmail.com", "12345678");
                    firstUser.setId(1L);
                    userDAO.createOrUpdate(firstUser);
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                Intent goToForm = new Intent(CreateUserActivity.this, MainActivity.class);
                goToForm.putExtra("user", user);
                startActivity(goToForm);

                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
