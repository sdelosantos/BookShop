package com.example.dell.bookshopandroid;

import android.app.Activity;
import android.app.Service;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import model.User;
import services.ApiResponse;
import services.BookShopService;
import services.IBookShop;
import services.RestApi.RestService;
import services.SqlApi.DataBase;
import services.SqlApi.IBookShopSQL;

public class LoginActivity extends Activity {

    final String USER_EMAIL = "email";
    final String USER_ID = "user_id";
    EditText tvEmail;
    EditText tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvEmail = findViewById(R.id.tvEmail);
        tvPassword = findViewById(R.id.tvPassword);
        if(savedInstanceState!=null){
            tvEmail.setText(savedInstanceState.getString(USER_EMAIL));
        }
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String id = sharedPref.getString(USER_ID,null);
        if(id!=null){
            Intent bookShop = new Intent(LoginActivity.this,BookShopActivity.class);
            bookShop.putExtra(USER_ID,id);
            startActivity(bookShop);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(USER_EMAIL,tvEmail.getText().toString());
        super.onSaveInstanceState(outState);
    }

    public void Login(View v){
        try {
            User us = new User();
            us.email = tvEmail.getText().toString();
            us.password = tvPassword.getText().toString();

            BookShopService.REST.login(us, new ApiResponse<User>() {
                @Override
                public void success(final User user) {
                    super.success(user);
                    if (user.userId != null) {
                        // SAVE USER INFO IN SQL
                      final User us = user;
                       new AsyncTask<Void, Void, Integer>() {
                            @Override
                            protected Integer doInBackground(Void... voids) {
                                DataBase db = Room.databaseBuilder(LoginActivity.this, DataBase.class, DataBase.NAME).fallbackToDestructiveMigration().build();
                                IBookShopSQL s = db.service();
                                User u = s.getUserById(us.userId);
                                if(u.userId==null){
                                    s.saveUser(us);
                                }


                                setShareRef(us.userId);
                                Intent bookShop = new Intent(LoginActivity.this, BookShopActivity.class);
                                startActivity(bookShop);
                               return  1;
                            }

                            @Override
                            protected void onPostExecute(Integer integer) {
                                super.onPostExecute(integer);
                            }

                        }.execute();

                    } else {
                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(LoginActivity.this);
                        dlgAlert.setMessage("Wrong password or email [User:admin@test.com, password:abc123]");
                        dlgAlert.setTitle("Login Fail");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.create().show();
                    }
                }
            });
        }catch (Exception ex){
            String msg = ex.getMessage();
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(LoginActivity.this);
            dlgAlert.setMessage(msg);
            dlgAlert.setTitle("Login Fail");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.create().show();
        }
    }
    private void setShareRef(String userId){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(USER_ID, userId);
        editor.apply();
    }
}
