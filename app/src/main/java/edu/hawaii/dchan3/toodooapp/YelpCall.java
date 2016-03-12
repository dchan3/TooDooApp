package edu.hawaii.dchan3.toodooapp;

import android.content.Context;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class YelpCall {
    OAuthService service;
    Token token;

    public static YelpCall get() {
        return new YelpCall("SN2zG_JMVwy3BSOxIBVR0g", "CD722sxU3xLzIm3HMnWSyEjoBnA", "EIqd1fKqbSJdPfBENbMiT_8UoPhwH0ca", "B8lLwJBa-_0a6B3VXFt403SRWfQ");
    }

    public YelpCall(String ckey, String csecret, String tkey, String tsecret) {
        this.service = new ServiceBuilder().provider(YelpApi.class).apiKey(ckey).apiSecret(csecret).build();
        this.token = new Token(tkey, tsecret);
    }

    public String search(String term, String location) {
        OAuthRequest r = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
        r.addQuerystringParameter("term", term);
        r.addQuerystringParameter("location", location);
        this.service.signRequest(this.token, r);
        Response resp = r.send();
        File f = new File("c.json");
        BufferedReader read = new BufferedReader(new InputStreamReader(YelpCall.get().searchStream("food", "Honolulu")));
        String line;
        Log.d("DEBUG","I am done.");
        try{
            OutputStreamWriter outs = new OutputStreamWriter(new FileOutputStream(f));
            while((line = read.readLine())!=null) {
                outs.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp.getBody();
    }

    public InputStream searchStream(String term, String location) {
        OAuthRequest r = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
        r.addQuerystringParameter("term", term);
        r.addQuerystringParameter("location", location);
        this.service.signRequest(this.token, r);
        Response resp = r.send();
        return resp.getStream();
    }

    public static void main(String[] args) {
        YelpCall yelpCall = new YelpCall("SN2zG_JMVwy3BSOxIBVR0g", "CD722sxU3xLzIm3HMnWSyEjoBnA", "EIqd1fKqbSJdPfBENbMiT_8UoPhwH0ca", "B8lLwJBa-_0a6B3VXFt403SRWfQ");
        String resp = yelpCall.search("cream+puffs", "San+Franciso");
        System.out.println(resp);
    }
}
