package com.example.bufomarinus.intrestappv100;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.bufomarinus.intrestappv100.R.id.headline;

public class MainActivity extends AppCompatActivity{

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_view);
        RecycleBackgroundTask recycleBackgroundTask = new RecycleBackgroundTask(MainActivity.this);
        recycleBackgroundTask.execute();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

    }

    // Aufruf einer neuen Activity\\

    public void getToLogin(View view) {
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
    }

    public void getToPost(View view) {
        Intent intent = new Intent(MainActivity.this, AddPost.class);
        startActivity(intent);

    }

    public void getToHome(View view) {
        Intent intent = new Intent(MainActivity.this, DisplayList.class);
        startActivity(intent);
    }
    public void getData(View view) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Look whats new on Intrest! (Get your App at intrestapp.com)");
        startActivity(Intent.createChooser(sharingIntent, "Share your intrests"));

    }
}