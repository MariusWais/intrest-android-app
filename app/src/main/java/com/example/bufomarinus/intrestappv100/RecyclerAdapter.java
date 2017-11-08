package com.example.bufomarinus.intrestappv100;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.bufomarinus.intrestappv100.R.id.headline;
import static com.example.bufomarinus.intrestappv100.R.id.new_headline;
import static com.example.bufomarinus.intrestappv100.R.id.recyclerButton;
import static com.example.bufomarinus.intrestappv100.R.id.source;

/**
 * Created by Bufo Marinus on 07.03.2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.RecyclerViewHolder> {

    //Deklarierung u. Init. von static Variablen für den Viewholder

    private static final int TYPE_HEAD = 0;
    private static final int TYPE_LIST = 1;



    private DisplayList.ChildItemClickListener listener; //Klicklistener

    ArrayList<Post> arrayList = new ArrayList<>(); // List

    public RecyclerAdapter(ArrayList<Post> arrayList) { // Konstr.
        this.arrayList = arrayList;

    }


    public void setChildItemClickListener(DisplayList.ChildItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { // Bei Beginn des Aufrufs des Viewholders


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler, parent, false); // Layout inflate des Reihen Layouts
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, viewType); // RVH Object
        return recyclerViewHolder;





    }


    @Override
    public long getItemId(int position) {
        return position; // Item position

    }


    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {


        if (holder.viewType == TYPE_LIST) {
            Post post = arrayList.get(position-1);
            holder.Headline.setText(post.getHeadline());
            holder.Source.setText(post.getSource());


        }


    }

    @Override
    public int getItemCount() {
        return arrayList.size()+1;

    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {       //Viewholder mit Onclicklistener


        //Deklarierung
        TextView Headline, Source;
        int viewType;
        private String mItem;
        EditText editText;
        Integer position;
        WebView WebView;
        Button b, sb;
        Context context;



        public RecyclerViewHolder(View view, int viewType) {

            super(view);
            if (viewType == TYPE_LIST) {

                //Initialiseirung
                Headline = (TextView) view.findViewById(R.id.headline);
                Source = (TextView) view.findViewById(R.id.source);
                b = (Button)view.findViewById(R.id.recyclerButton);
                sb = (Button)view.findViewById(R.id.shareButton);
                WebView = (WebView) view.findViewById(R.id.webView);
                WebView.setVisibility(View.GONE);

                // set
                b.setEnabled(true);
                b.setOnClickListener(this);
                sb.setOnClickListener(this);
                Headline.setOnClickListener(this);
                Source.setOnClickListener(this);



                this.viewType = TYPE_LIST;

            } else if (viewType == TYPE_HEAD) {

                this.viewType = TYPE_HEAD;
            }
        }


        public void setItem(String item) {
            mItem = item;
            Headline.setText(item);
        }



        @Override
        public void onClick(View v) {  // Onclick und Button funktionen im RecyclerView

            position = getPosition();   // Position der Reihe
            context = v.getContext();   // kontext


            String link ="http://www.bewerbungmariuswais.de/app/get_url.php?id="+ getPosition(); // Link zum PHP script mit Übergabe der ID in jeweiliger Position des Recyclers
            String voteString =  "http://www.bewerbungmariuswais.de/app/get_votes.php?id="+ getPosition();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link)); // Start des Gerätebrowsers

            switch(v.getId()){
                case R.id.recyclerButton: // Vote Button
                    WebView.loadUrl(voteString); //Ausführen des Scripts
                   // b.getBackground().setColorFilter(0xFF3B5998, PorterDuff.Mode.MULTIPLY); // Farbänderung
                    b.setClickable(false);
                    b.setBackgroundResource(R.mipmap.arrow_dark);
                    Toast.makeText(v.getContext(), "voted", Toast.LENGTH_SHORT).show(); // Meldung
                    break;
               case R.id.shareButton:
                   break;
                case R.id.headline:
                    b.getContext().startActivity(browserIntent); // Browser starten
                break;
                case R.id.source:
                    b.getContext().startActivity(browserIntent); // Browser starten
                    break;

                default:
                    break;
            }
        }
    }



    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEAD;
        return TYPE_LIST;


    }




}



