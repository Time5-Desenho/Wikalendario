package com.time2desenho.wikalendario.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.controller.SessionSingleton;
import com.time2desenho.wikalendario.controller.UsersController;
import com.time2desenho.wikalendario.model.User;
import com.time2desenho.wikalendario.controller.UserHelper;

public class CreateUserActivity extends AppCompatActivity {

    private UserHelper helper;
    private Button confirmationButton;
    private Context context;
    private UsersController usersController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        User user = SessionSingleton.getInstance(this).getLoggedUser(this);
        ((TextView) findViewById(R.id.userNameToolbar)).setText(user.getName());
        ((TextView) findViewById(R.id.userEmailToolbar)).setText(user.getEmail());
        */

        if(SessionSingleton.getInstance(this).isLoggedIn()){
            Intent goToForm = new Intent(CreateUserActivity.this, MainActivity.class);
            startActivity(goToForm);
        }
        setContentView(R.layout.activity_create_user);

        helper = new UserHelper(this);
        usersController = new UsersController(this);

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
                Log.d("CLICK", "Clicked");
                createUser();
            }
        };

        return listener;
    }

    private void createUser() {
        User user;
        try {
            user = helper.getNewUser(this);
            usersController.createUser(user, getContext());
        }catch (IllegalArgumentException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

        Intent goToForm = new Intent(CreateUserActivity.this, MainActivity.class);
        goToForm.putExtra("user", user);
        SessionSingleton.getInstance(this).login(user.getId(), this);
        startActivity(goToForm);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
