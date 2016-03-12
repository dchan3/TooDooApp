package edu.hawaii.dchan3.toodooapp;

import android.util.JsonReader;
import android.util.JsonToken;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Connect {
    private InputStream json;
    public ArrayList<yOject> yOject;

    public Connect(InputStream json) throws IOException {
        this.json = json;
        readJsonStream(json);
    }

    public void readJsonStream(InputStream in) throws IOException {
        long i = 0;
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            //return readMessagesArray(reader);
            reader.beginArray();
            while (reader.hasNext()) {
                String[] temp = readMessage(reader);
                yOject.add(new yOject(i,temp[0],temp[1],temp[3]));
                i++;
            }
        }
        finally {
            reader.close();
        }
    }


    public String[] readMessage(JsonReader reader) throws IOException {
        String id = "";
        String address = "", lat = "",lon = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                id = reader.nextString();
            } else if (name.equals("display_address")) {
               address = reader.nextString();
            } else if (name.equals("latitude") && reader.peek() != JsonToken.NULL) {
                lat = reader.nextString();
            }else if (name.equals("longitude") && reader.peek() != JsonToken.NULL) {
                lon = reader.nextString();
            /*} else if (name.equals("user")) {
                user = readUser(reader);*/
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        String[] temp = {id, address,lat+";"+lon};
        return temp;
    }

    public ArrayList<yOject> getyOject(){
        return yOject;
    }
}

