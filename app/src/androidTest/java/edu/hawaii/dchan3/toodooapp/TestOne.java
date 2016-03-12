package edu.hawaii.dchan3.toodooapp;

import org.junit.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import junit.framework.*;
import org.junit.*;

public class TestOne {
    @Test
    public void test(){
        Connect c;
        ArrayList<yOject> y = new ArrayList<>();
        try {
            c = new Connect(YelpCall.get().searchStream("cream puffs", "San Francisco"));
            y = c.getyOject();
        } catch (IOException ioe) {

        }
        assertEquals(false,y.isEmpty());
    }

}
