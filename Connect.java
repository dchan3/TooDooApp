package Code;

import android.util.JsonReader;
import android.util.JsonToken;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by NikoBenick on 3/12/2016.
 */
public class Connect {
    private InputStream json;
    public ArrayList<yOject> yOject;

    public Connect(InputStream json) throws IOException {
        this.json = json;
        readJsonStream(json);
    }

    public void readJsonStream(InputStream in) throws IOException {
        long i = 0;
        //JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        BufferedReader stdout = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String line;
        boolean done = false;

        try {
            while((line = stdout.readLine()) != null || !done){
                if(line.indexOf("\"businesses\"") != -1){
                    readMessage(stdout);
                    done = true;
                }
            }
            //return readMessagesArray(reader);
        } finally {
            stdout.close();
        }
    }


    public void readMessage(BufferedReader stdout) throws IOException {
        String id = null;
        String address = "", lat = "", lon = "";
        String line;
        long i = 0;
        while((line = stdout.readLine()) != null ){
            if(line.indexOf("\"id\":") != -1){
                if(id != null){
                    yOject.add(new yOject(i,id,address.trim(),lat+";"+lon));
                    i++;
                }
                id = line.replace("\"id\": ","");
                id = id.trim();
            }else if(line.indexOf("\"coordinate\"") != -1){
                lat = stdout.readLine().replace("\"latitude\":","");
                lat=lat.trim();
                lon = stdout.readLine().replace("\"longitude\":", "");
                lon=lon.trim();
            }else if (line.indexOf("\"display_address\"") != -1){
                address = stdout.readLine()+stdout.readLine();
            }
        }

    }

    public ArrayList<yOject> getyOject() {
        return yOject;
    }
}

