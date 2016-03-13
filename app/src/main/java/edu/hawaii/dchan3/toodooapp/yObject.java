package com.example.brianm.hackathon2016;

import java.util.Iterator;

/**
 * Created by Brian M on 3/12/2016.
 */
public class yObject {

    long nid = 0;
    String id;
    String position;
    String address;

    public yObject(long nid, String id, String address, String position){
        this.nid = nid;
        this.id = id;
        this.address = address;
        this.position = position;
    }

    //getters

    public long getnid(){
        return nid;
    }
    public String getid() {
        return id;
    }

    public String getposition(){
        return position;
    }

    public String getaddress(){
        return address;
    }


}
