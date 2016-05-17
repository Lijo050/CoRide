package com.example.devesh.Coride;

import com.example.devesh.Coride.R;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    String part1,part2;
    Master master = new Master();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
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
    public void reject(View v) {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
    public void sendData(View view) throws MalformedURLException {
        Log.e("TEST LOG", "ho gya, aa gya loop me");
        Toast.makeText(this,"devesh",Toast.LENGTH_SHORT).show();
        Master master=new Master();
        String url_string =master.url +"accept";
        Master obj=new Master();
        String json ="[{mobile:\""+obj.mobile+"\"}]";
        SendDataTask snd = new SendDataTask(json);
        URL url = new URL(url_string);
        snd.execute(url);
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }
    public class SendDataTask extends AsyncTask<URL,Void,Void>
    {
        ArrayList<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
        String json;
        SendDataTask(String json)
        {
            this.json = json;
        }

        protected Void doInBackground(URL... urls)
        {
            try {
                String a ="ff";
                URL url = urls[0];
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");

                //  String input = "{\"qty\":100,\"name\":\"iPad 4\"}";

                OutputStream os = conn.getOutputStream();
                // ObjectOutputStream oos = new ObjectOutputStream(os);
                //  oos.writeObject(params);
                System.out.println("input : "+json);
                os.write(json.getBytes());//Sendin the json object
                os.flush();
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                String output;
                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                    String[] parts = output.split(",");
                     part1 = parts[0];
                     part2 = parts[1];

                    System.out.println("------------------------------------------------------------------------------");
                    System.out.println(master.flag);

                        if(master.flag==0) {
                            master.s1=part1;
                            master.d1=part2;


                            master.flag++;
                        }

                    else{
                            master.source = part1;
                            master.destination = part2;
                        }
                    System.out.println("------------------------------------------------------------------------------");
                    System.out.println(   "source "+master.source);
                    System.out.println( "destination"+master.destination);
                    System.out.println(   "s1 "+master.s1);
                    System.out.println( "d1"+master.d1);

                }

                conn.disconnect();
            }
            catch (IOException i){
                i.printStackTrace();
            }

            return null;
        }
    }
}
