package com.example.bufomarinus.intrestappv100;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Bufo Marinus on 28.02.2017.
 */

public class AddPost extends Activity{

    // Deklarierung der Variablen \\

    EditText ET_HEADLINE,ET_SOURCE,ET_LINK;
    String headline, source,link;


    @Override
    protected void onCreate(Bundle savedInstanceState){ // Beim Start ausführen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_layout); // Bestimmung des Layouts

        //Initialiserung der Textfelder und Zuweisung per ID\\
        ET_HEADLINE = (EditText)findViewById((R.id.new_headline));
        ET_SOURCE = (EditText)findViewById((R.id.new_source));
        ET_LINK = (EditText)findViewById((R.id.new_link));
    }

    public void addPost(View view){
        // Inhalt der Textfelder in Variable übergeben
        headline = ET_HEADLINE.getText().toString();
        source = ET_SOURCE.getText().toString();
        link = ET_LINK.getText().toString();

        String method = "post"; // Definierung des Prozesses
        PostBackgroundTask postbackgroundTask = new PostBackgroundTask((this)); //  Objektgenerierung des Tasks
        postbackgroundTask.execute(method,headline,source,link);    // execute anweisung des Backgroundtasks
        finish();   // anweisung zum abschluss/beedigung
        Intent refresh =new Intent(this, DisplayList.class); // Activity neu laden nach beendetem Post
        startActivity(refresh);
    }


    // Funktionen der Toolbar um andere Activities aufzurufen
    public void getToLogin(View view)
    {
        Intent intent = new Intent(AddPost.this, Login.class);
        startActivity(intent);
    }
    public void getToPost(View view)
    {
        Intent intent = new Intent(AddPost.this, AddPost.class);
        startActivity(intent);

    }public void getToHome(View view)
    {
        Intent intent = new Intent(AddPost.this, DisplayList.class);
        startActivity(intent);
    }
    public void getData(View view) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Look whats new on Intrest! (Get your App at intrestapp.com)");
        startActivity(Intent.createChooser(sharingIntent, "Share your intrests"));

    }
}
