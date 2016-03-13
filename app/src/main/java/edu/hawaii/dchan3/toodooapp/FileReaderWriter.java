package com.example.brianm.hackathon2016;


import android.util.JsonWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

        FileOutputStream file = new FileOutputStream("test.json");

        try {
            writeJsonStream(file, testList);
        } catch (IOException e) {
            e.printStackTrace();
        }
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


}
