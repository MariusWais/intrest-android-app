package com.example.bufomarinus.intrestappv100;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.system.Os;
import android.util.Log;
import android.widget.Button;
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

public class BackgroundTask extends AsyncTask<String,Void,String> { // Hintergrungd Operationen ohne Manipulation von threads/handler

    // Deklarierung\\

    Context ctx;
    Button b;



    BackgroundTask(Context ctx){
        this.ctx = ctx; //Kontext
    }

    @Override
    protected void onPreExecute() { // Vor Ausführung starten

    }
    @Override
    protected String doInBackground(String... params) { // Hintergund Operationen

        // URL-Link zum Server(localhost)\\
        String reg_url = "http://www.bewerbungmariuswais.de/app/register.php";
        String login_url = "http://www.bewerbungmariuswais.de/app/vote.php";

        //!!!!! Link zum Server(localhost) für echte Geräte (Hier muss die eigene IPv4 Adresse des Computers verwendet werden. Diese findet man in CMD unter ipconfig)!!!!!
        //String reg_url = "http://192.168.1.XX/app/register.php";
        //String login_url = "http://192.168.1.XX/app/login.php";


        String method = params[0];
        if(method.equals("register")) { // Wenn Registrierung vorliegt
            String name = params[1];    //Zuordnung von Parametern
            String user_name = params[2];
            String user_pass = params[3];
            try {
                URL url = new URL(reg_url); // Url Objekt generieren

                // HttpUrl Verbindung erstellen\\
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream OS = httpURLConnection.getOutputStream();// Ausgabestream

                // In Datei schreiben mithilfe von bufferedwriter
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("user","UTF-8") + "="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("user_name","UTF-8") + "="+URLEncoder.encode(user_name,"UTF-8")+"&"+
                        URLEncoder.encode("user_pass","UTF-8") + "="+URLEncoder.encode(user_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();//Eingabestream
                IS.close();

                return "Registration success"; // Rückmeldung wenn Registrierung erfolgreich war


                // catch clauses /  fehlermeldungen \\
            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }

            return reg_url;

        }else if(method.equals("login")){ // Wenn Login vorliegt
            String login_name = params[1];  // Zuweisung von Parametern
            String login_pass = params[2];

            try {
                URL url = new URL(login_url); //Url Objekt generieren

                //HttpUrl Verbindung herstellen\\
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream(); // Ausgabestream

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"utf-8")); // in datei schreiben

                // Datenbankinformationen werden kodiert und in String Variable gespeichert
                String data = URLEncoder.encode("login_name","UTF-8")+"="+URLEncoder.encode(login_name,"UTF-8")+"&"+URLEncoder.encode("login_pass","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream(); // Eingabestream

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1")); // aus datei lesen
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null){
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response; // Rückmeldung

            } catch (MalformedURLException e){ // catch fehlermeldungen
                e.printStackTrace();

            }catch (IOException e){
                e.printStackTrace();
            }
            return login_url;
        }
        return null;

    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {   // Nach Ausführung starten

        if (result != null && result.equals("Registration success")) {  //Wenn Registrierung erfolgreich ist gibt es eine Meldung
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();

        }



        if (result != null && result.equalsIgnoreCase("Login success")) { //Wenn Loginerfolgreich ist gibt es eine Meldung

            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();


        }
    }
}
