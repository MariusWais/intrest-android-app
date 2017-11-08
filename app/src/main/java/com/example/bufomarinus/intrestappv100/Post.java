package com.example.bufomarinus.intrestappv100;

import android.support.v7.widget.SearchView;

import java.lang.ref.SoftReference;
import java.security.PublicKey;

/**
 * Created by Bufo Marinus on 07.03.2017.
 */

public class Post {

    //Deklarierung

    private String headline;
    private String source;
    private String url;

    public Post(String headline,String source,String url){ // Konstruktor
        this.setHeadline(headline);
        this.setSource(source);
        this.setUrl(url);

    }

    //Get Set Methoden

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
