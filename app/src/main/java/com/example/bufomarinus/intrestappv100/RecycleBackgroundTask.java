package com.example.bufomarinus.intrestappv100;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Bufo Marinus on 07.03.2017.
 */

public class RecycleBackgroundTask extends AsyncTask<Void,Post,Void>{

    //Deklarierung
    Context ctx;
    Activity activity;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Post> arrayList = new ArrayList<>();

    public RecycleBackgroundTask(Context ctx){
        this.ctx = ctx;
        activity = (Activity)ctx;
    }

    // Link zum Server
    String json_string = "http://www.bewerbungmariuswais.de/app/post.php";


    @Override
    public void onPreExecute() { // Bevor Ausführung starten

        //Initialisierung
        recyclerView = (RecyclerView)activity.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public Void doInBackground(Void... params) { //Hintergrundaktivität

        try {
            URL url = new URL(json_string); // URL Objekt
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection(); //URl verbindung
            InputStream inputStream = httpURLConnection.getInputStream();// Eingabestream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); // Aus Stream lesen
            StringBuilder stringBuilder = new StringBuilder(); // String erstellen
            String line;


            while ((line=bufferedReader.readLine())!=null) {
                stringBuilder.append(line+"\n");
            }
            httpURLConnection.disconnect();
            String json_string = stringBuilder.toString().trim();

            JSONObject jsonObject = new JSONObject(json_string);        // JSOn Object generieren
            JSONArray jsonArray = jsonObject.getJSONArray("server_response"); // JSON Array

            int count = 0;
            while(count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                count++;

                Post post = new Post(JO.getString("headline"),JO.getString("source"),JO.getString("url")); // Datenbankinformationen als JSOn Object
                publishProgress(post);
                String link = JO.getString("url");
            }

        // catch clauses/fehlermeldungen
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void onProgressUpdate(Post... values) { // Update während des Verlaufs

        arrayList.add(values[0]);
        adapter.notifyDataSetChanged();

    }


    @Override
    public void onPostExecute(Void aVoid) { // Abschluss
        super.onPostExecute(aVoid);
    }




}
