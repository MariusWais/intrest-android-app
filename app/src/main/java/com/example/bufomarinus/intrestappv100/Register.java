package com.example.bufomarinus.intrestappv100;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Bufo Marinus on 28.02.2017.
 */

public class Register extends Activity{
    //Deklarierung
    EditText ET_NAME,ET_USER_NAME,ET_USER_PASS;
    String name, user_name,user_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState){ // Beim Start ausführen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout); // layout
        //Initialiserung
        ET_NAME = (EditText)findViewById((R.id.name));
        ET_USER_NAME = (EditText)findViewById((R.id.new_user_name));
        ET_USER_PASS = (EditText)findViewById((R.id.new_user_pass));
    }

    public void userReg(View view){

        name = ET_NAME.getText().toString();    //Inhalt der Texteingabe in Variable speichern
        user_name = ET_USER_NAME.getText().toString();
        user_pass = ET_USER_PASS.getText().toString();
        if(!name.isEmpty()&&!user_name.isEmpty()&&!user_pass.isEmpty()) { // Wenn Textfeld nicht leer ist
            String method = "register";
            BackgroundTask backgroundTask = new BackgroundTask((this)); // Hintegrundaktivität ausführen
            backgroundTask.execute(method, name, user_name, user_pass);
            finish();
        }
    }

    // Funktionen der Toolbar um Activities aufzurufen\\

    public void getToLogin(View view)
    {
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
    }
    public void getToPost(View view)
    {
        Intent intent = new Intent(Register.this, AddPost.class);
        startActivity(intent);

    }public void getToHome(View view)
    {
        Intent intent = new Intent(Register.this, DisplayList.class);
        startActivity(intent);
    }

    public void getData(View view) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Look whats new on Intrest! (Get your App at intrestapp.com)");
        startActivity(Intent.createChooser(sharingIntent, "Share your Intrests"));

    }
}
