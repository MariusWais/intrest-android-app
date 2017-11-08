package com.example.bufomarinus.intrestappv100;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.system.Os;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Bufo Marinus on 28.02.2017.
 */
// Hintergrundtasks für Login und Registration
public class PostBackgroundTask extends AsyncTask<String,Void,String> {
    //Deklarierung
    AlertDialog alertDialog;
    Context ctx;

    PostBackgroundTask(Context ctx) {
        this.ctx = ctx;
    } // Kontext

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
    } // Ausführen vor Beginn

    @Override
    protected String doInBackground(String... params) { // Hintergrundaktivität

        // Link zum Server(localhost) nur bei Virtual Devices
        String post_url = "http://www.bewerbungmariuswais.de/app/add_post.php";

        //!!!!! Link zum Server(localhost) für echte Geräte (Hier muss die eigene IPv4 Adresse des Computers verwendet werden. Diese findet man in CMD unter ipconfig)!!!!!
        //String post_url = "http://192.168.X.XX/app/register.php";

        String method = params[0];

        if (method.equals("post")) {    // Zuweisung der Parameter

            String headline = params[1];
            String source = params[2];
            String link = params[3];

            try {

                URL url = new URL(post_url);    // URL-Objekt
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); // URL Verbindung erstellen
                httpURLConnection.setRequestMethod("POST"); // POST Methode
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream(); // Ausgabestream
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8")); // in DB schreiben
                String data = URLEncoder.encode("headline", "UTF-8") + "=" + URLEncoder.encode(headline, "UTF-8") + "&" +
                        URLEncoder.encode("source", "UTF-8") + "=" + URLEncoder.encode(source, "UTF-8") + "&" +
                        URLEncoder.encode("url", "UTF-8") + "=" + URLEncoder.encode(link, "UTF-8");


                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream(); // Eingabestream
                IS.close();
                return "Post success"; // Post erfolgreich

            //catch clauses/fehlermeldungen

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
        return post_url;
    }

        @Override
        protected void onProgressUpdate (Void...values){
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute (String result){ // Nach Ausführung
            if (result.equals("Post success")) {
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show(); // Meldung


            } else {
                alertDialog.setMessage(result);
                alertDialog.show();
            }
        }
}
