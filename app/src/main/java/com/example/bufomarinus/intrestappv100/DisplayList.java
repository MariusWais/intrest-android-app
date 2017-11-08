package com.example.bufomarinus.intrestappv100;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


import java.util.ArrayList;


/**
 * Created by Bufo Marinus on 07.03.2017.
 */
// Darstellung des Recyclerviews
public class DisplayList extends AppCompatActivity {
    //Deklarierung von Variablen
    RecyclerView.Adapter adapter;
    ArrayList<Post> arrayList = new ArrayList<>();
    Toolbar toolbar;
    RecyclerAdapter recyclerAdapter;
    TextView textView,headlinetext;


    protected void onCreate(Bundle savedInstanceState) { // Zum Start ausführen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_view); //layout
        RecycleBackgroundTask recycleBackgroundTask = new RecycleBackgroundTask(DisplayList.this);
        recycleBackgroundTask.execute();
        adapter = new RecyclerAdapter(arrayList);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        headlinetext=(TextView)findViewById(R.id.headline) ;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
    }


    // Funktionen der Toolbar um Activities aufzurufen\\

    public void getToHome(View view) {
        Intent intent = new Intent(DisplayList.this, DisplayList.class);
        startActivity(intent);
    }

    public void getToLogin(View view) {
        Intent intent = new Intent(DisplayList.this, Login.class);
        startActivity(intent);
    }

    public void getToPost(View view) {
        Intent intent = new Intent(DisplayList.this, AddPost.class);
        startActivity(intent);

    }
    public void getData(View view) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Look whats new on Intrest! (Get your App at intrestapp.com)");
        startActivity(Intent.createChooser(sharingIntent, "Share your intrests"));

    }

    public interface ChildItemClickListener {
        public void onClick(View v, int position);
    }


}
