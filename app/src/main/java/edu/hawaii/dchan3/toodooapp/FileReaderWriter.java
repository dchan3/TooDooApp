package com.example.brianm.hackathon2016;


import android.util.JsonReader;
import android.util.JsonToken;
import android.util.JsonWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileReaderWriter {

    public void Main(String args[]) throws IOException {

        String address = "address";
        String position = "-500 500";
        yObject testobj = new yObject(1234, "5678", address, position);

        List<yObject> testList = new ArrayList<yObject>();

        testList.add(testobj);

        File testfile = new File("test.json");

        FileOutputStream file = new FileOutputStream("test.json");

        try {
            writeJsonStream(file, testList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileInputStream in = new FileInputStream(testfile);

        readJsonStream(in);
    }

    public void writeJsonStream(OutputStream out, List<yObject> messages)throws IOException {

        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.setIndent("  ");
        writeMessagesArray(writer, messages);
        writer.close();

    }

    public void writeMessagesArray(JsonWriter writer, List<yObject> messages)throws IOException {
        writer.beginArray();
        for (yObject Object: messages) {
            System.out.println("This is my object info:" + Object + "\n");
            writeMessage(writer, Object);
        }
        writer.endArray();
    }

    public void writeMessage(JsonWriter writer, yObject message) throws IOException {

        writer.beginObject();
        writer.name("nid").value(message.getnid());
        writer.name("id").value(message.getid());
        writer.name("position").value(message.getposition());
        writer.name("address").value(message.getaddress());

        //writer.name("text").value(message.getText()); // from tut

        /*
        if (message.getGeo() != null) {
            writer.name("geo");
            writeDoublesArray(writer, message.getGeo());
        } else {
            writer.name("geo").nullValue();
        }

        writer.name("user");
        writeUser(writer, message.getUser());
        */
        writer.endObject();
    }

    /*
    public void writeUser(JsonWriter writer, User user) throws IOException {
        writer.beginObject();
        writer.name("name").value(user.getName());
        writer.name("followers_count").value(user.getFollowersCount());
        writer.endObject();
    }
    */

    public void writeDoublesArray(JsonWriter writer, List<yObject> doubles) throws IOException {
        writer.beginArray();
        for (yObject value : doubles) {
            writer.value(String.valueOf(value));
        }
        writer.endArray();
    }


    //CODE FOR READING BELOW*****

    public List readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readMessagesArray(reader);

        } finally{
            reader.close();
        }

    }

    public List readMessagesArray(JsonReader reader) throws IOException {

        List messages = new ArrayList();

        reader.beginArray();
        while (reader.hasNext()) {
            messages.add(readMessage(reader));
        }
        reader.endArray();
        return messages;
    }

    public yObject readMessage(JsonReader reader) throws IOException {
        long nid = -1;
        String id = null;
        //User user = null; //from tut
        String position = null;
        String address = null;

        reader.beginObject();

        while (reader.hasNext()) {
            String name = reader.nextName();

            if (name.equals("nid")) {
                nid = reader.nextLong();
            } else if (name.equals("id")) {
                id = reader.nextString();
            } else if (name.equals("position") && reader.peek() != JsonToken.NULL) {
                position = reader.nextString();
            } else if (name.equals("address")) {
                address = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new yObject(nid, id, position, address);
    }

    public List readDoublesArray(JsonReader reader) throws IOException {
        List doubles = new ArrayList();

        reader.beginArray();
        while (reader.hasNext()) {
            doubles.add(reader.nextDouble());
        }
        reader.endArray();
        return doubles;
    }

    /**
    public User readUser(JsonReader reader) throws IOException {
        String username = null;
        int followersCount = -1;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                username = reader.nextString();
            } else if (name.equals("followers_count")) {
                followersCount = reader.nextInt();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new User(username, followersCount);
    }
     */
    }
