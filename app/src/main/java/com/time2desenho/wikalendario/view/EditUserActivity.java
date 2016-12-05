package com.time2desenho.wikalendario.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

public class EditUserActivity extends AppCompatActivity {

    private UserHelper helper;
    private Button confirmationButton;
    private Button deleteButton;
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

        if(!SessionSingleton.getInstance(this).isLoggedIn()){
            Intent goToForm = new Intent(EditUserActivity.this, LoginActivity.class);
            startActivity(goToForm);
            finish();
            return;
        }

        setContentView(R.layout.activity_edit_user);

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
        deleteButton = (Button) findViewById(R.id.delete_account);
        deleteButton.setOnClickListener(onDeleteButtonClick());

        helper.setUser(this);
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
                editUser();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener onConfirmationButtonClick(){
        View.OnClickListener listener = new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("CLICK", "Clicked");
                editUser();
            }
        };

        return listener;
    }

    private View.OnClickListener onDeleteButtonClick(){
        View.OnClickListener listener = new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                new AlertDialog.Builder(EditUserActivity.this)
                        .setMessage("Você tem certeza que deseja deletar sua conta?")
                        .setCancelable(false)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteUser();
                                SessionSingleton.getInstance(EditUserActivity.this).logout();
                                Intent intent = new Intent(EditUserActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Não", null)
                        .show();
            }
        };

        return listener;
    }

    private void deleteUser() {
        User user = SessionSingleton.getInstance(this).getLoggedUser(this);
        usersController.deleteUser(user);
    }

    private void editUser() {
        User user;
        try {
            user = helper.getUser(this);
            User loggedUser = SessionSingleton.getInstance(getContext()).getLoggedUser(getContext());
            usersController.updateUser(loggedUser, user, getContext());
            Log.d("RASEN", "SHURIKEN");
            SessionSingleton.getInstance(this).login(user.getId(), this);
        }catch (IllegalArgumentException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

        Intent goToForm = new Intent(EditUserActivity.this, MainActivity.class);
        startActivity(goToForm);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
