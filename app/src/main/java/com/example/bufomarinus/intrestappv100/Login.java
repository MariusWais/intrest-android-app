package com.example.bufomarinus.intrestappv100;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Bufo Marinus on 28.02.2017.
 */

public class Login extends Activity{
    //Deklarationen
    EditText ET_NAME,ET_PASS;
    String login_name,login_pass;
    RecyclerAdapter recyclerAdapter;
    RecyclerView.Adapter adapter;
    Button b;




    @Override
    protected void onCreate(Bundle savedInstanceState) {// Startfunktion
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout); // Bestimmung des Layouts

        ET_NAME = (EditText)findViewById(R.id.user_name);// Texteingabefelder
        ET_PASS = (EditText)findViewById(R.id.user_pass);

        ArrayList<Post> arrayList = new ArrayList<>();
        adapter = new RecyclerAdapter(arrayList); // Arraylist im Adapter
        b = (Button)findViewById(R.id.recyclerButton); // button
    }



    public void userReg(View view){

        startActivity(new Intent(this,Register.class)); // Aufruf der Registrierungsactivity
    }

    public void userLogin(View view){

        login_name = ET_NAME.getText().toString();     // Inhalt der Texteingabefelder in String speichern
        login_pass = ET_PASS.getText().toString();

        if(!login_name.isEmpty()&&!login_pass.isEmpty()) { //Wenn etwas eingegeben wurde
            String method = "login";            // Definierung des Prozesses
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method, login_name, login_pass);
            finish();
           Intent refresh =new Intent(this, DisplayList.class); // Seite neu laden
            startActivity(refresh);
        }

    }


    // Toolbar Funktionen zum Auf ruf der jeweiligen Activities

    public void getToLogin(View view)
    {
        Intent intent = new Intent(Login.this, Login.class);
        startActivity(intent);
    }
    public void getToPost(View view)
    {
        Intent intent = new Intent(Login.this, AddPost.class);
        startActivity(intent);

    }public void getToHome(View view)
    {
        Intent intent = new Intent(Login.this, DisplayList.class);
        startActivity(intent);
    }

    public void getData(View view) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Look whats new on Intrest! (Get your App at intrestapp.com)");
        startActivity(Intent.createChooser(sharingIntent, "Share using"));

    }
}
