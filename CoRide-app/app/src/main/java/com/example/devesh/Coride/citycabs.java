package com.example.devesh.Coride;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.util.Pair;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A login screen that offers login via email/password.
 */
public class citycabs extends AppCompatActivity  {
    Master master = new Master();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citycabs);
    }
    public void sendData(View view) throws MalformedURLException{
        Log.e("TEST LOG", "ho gya, aa gya loop me");
        Toast.makeText(this,"coride",Toast.LENGTH_SHORT).show();
        Master master=new Master();
        String url_string =master.url +"requestride";
        EditText source;
        EditText destination;
        EditText tolerance;
        RadioGroup rg;
        LocationManager locationManager;
        String ssource;
        String sdestination;
        String stolerance;
        Double latitude=0.00,longitude=0.00;

        int count = 0;

        rg = (RadioGroup) findViewById(R.id.radioGroup1);
       source =(EditText)findViewById(R.id.source);
       destination=(EditText)findViewById(R.id.destination);
        tolerance = (EditText)findViewById(R.id.tolerance);
        ssource= source.getText().toString();
        master.s1=ssource;

        sdestination= destination.getText().toString();
        master.d1=sdestination;
        stolerance= tolerance.getText().toString();
        String gender= ((RadioButton)findViewById(rg.getCheckedRadioButtonId() )).getText().toString();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Log.e("shishir","I am here dude");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    0);

        }
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location =  locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        if(location==null){
            Toast.makeText(getApplicationContext(), "Location Disabled Enable it", Toast.LENGTH_SHORT).show();
        }
        else {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
       Master obj1=new Master();
        if(count == 0) {
            String json ="[{source:\""+ssource+"\"},{destination:\""+sdestination+"\"},{tolerance:\""+stolerance+"\"},{gender:\""+gender+"\"},{latitude:\""+latitude+"\"},{longitude:\""+longitude+"\"},{mobile:\""+obj1.mobile+"\"}]";
            // snd.addNameValuePair("name", name);
            //snd.addNameValuePair("mobile", mobile);
            //snd.addNameValuePair("password", password);
            // snd.addNameValuePair("email",email);
            //snd.addNameValuePair("age", age);
            SendDataTask snd = new SendDataTask(json); //Go to sendData class fromhere
            URL url = new URL(url_string);
            snd.execute(url);
            Intent intent = new Intent(this,MapsActivity.class);
            startActivity(intent);
        }

    }
    public class SendDataTask extends AsyncTask<URL,Void,Void>{
        ArrayList<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
        String json;
        SendDataTask(String json){
            this.json = json;
        }
        // ArrayList<String> params = new ArrayList<String>();
        //params.put(a);
        // Gson g = new Gson();
        //String json = g.toJson(params);
        protected Void doInBackground(URL... urls){
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


