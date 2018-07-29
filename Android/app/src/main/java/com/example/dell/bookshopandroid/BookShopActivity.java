package com.example.dell.bookshopandroid;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.Book;
import model.User;
import services.ApiResponse;
import services.BookShopService;
import services.SqlApi.DataBase;
import services.SqlApi.IBookShopSQL;

public class BookShopActivity extends AppCompatActivity {
    final String USER_ID = "user_id";

    TextView tvUserName;
    RecyclerView dgView;
    RecycleViewAdapter adapter;
    RecyclerView.LayoutManager layout;
    ArrayList<Book> _list = new ArrayList<Book>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_shop);
        tvUserName = findViewById(R.id.tvUserName);
        dgView = (RecyclerView)findViewById(R.id.rcView);
        String userId = getIntent().getStringExtra(USER_ID);
        setData(userId);
    }

    private void setUserInf(User user){
        tvUserName.setText("Welcome, "+user.userName);
    }
    private void setData(String userID){
        final String user_id = userID;
        final Context context = getApplicationContext();
        BookShopService.REST.listBooks(new ApiResponse<List<Book>>() {
            @Override
            public void success(List<Book> value) {
                adapter = new RecycleViewAdapter(BookShopActivity.this,value);
                dgView.setHasFixedSize(true);
                layout = new LinearLayoutManager(BookShopActivity.this);
                dgView.setAdapter(adapter);
                dgView.setLayoutManager(layout);
            }
        });
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                DataBase db = Room.databaseBuilder(context, DataBase.class, DataBase.NAME).fallbackToDestructiveMigration().build();
                IBookShopSQL s = db.service();
                User us = s.getUserById(user_id);
                setUserInf(us);
                return  1;
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
            }

        }.execute();

       /*final Context cont = getApplicationContext();
        .databaseBuilder(cont,DataBase.class,DataBase.NAME)
                .fallbackToDestructiveMigration()
                .build().runInTransaction(new Runnable() {
                    @Override
                    public void run() {
                        DataBase db = Room.databaseBuilder(cont,DataBase.class,DataBase.NAME).fallbackToDestructiveMigration().build();
                        User us = db.service().getUserById(user_id);
                    }
                });*/
    }

}
