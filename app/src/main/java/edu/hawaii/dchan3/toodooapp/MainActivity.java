package edu.hawaii.dchan3.toodooapp;
import android.util.Log;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.widget.*;
import android.view.MenuItem;
import android.content.Context;
public class MainActivity extends AppCompatActivity {
    TextView tv;
    class Background extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {
            String eh = YelpCall.get().search("Chinese", "Honolulu");
            return eh;
        }

        protected void onPreExecute() {
            ((TextView)findViewById(R.id.textView)).setText("");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            ((TextView)findViewById(R.id.textView)).setText("Loading...");
        }

        protected void onPostExecute(String result) {
            Log.d("RESULT", result);
            ((TextView)findViewById(R.id.textView)).setText(result);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displayHello(View v) {
        new Background().execute();
               // ((TextView) findViewById(R.id.textView)).setText("Hello.");
    }
}
