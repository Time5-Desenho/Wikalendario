package com.time2desenho.wikalendario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    private UserHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        helper = new UserHelper(this);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("username");
        if(user != null){
            helper.fillForm(user);
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

                UserDao dao = new UserDao(this);
                if(user.getId() != null){
                    dao.update(user);
                } else {
                    dao.insert(user);
                }
                dao.close();

                Toast.makeText(UserActivity.this, user.getName() + " Created!", Toast.LENGTH_SHORT).show();

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
