package com.example.devesh.Coride;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.widget.Button;



public class SignIn extends AppCompatActivity {

    Master master=new Master();
    String url_string = master.url +"loginuser";
    Context c = this;
    Button btnGCMRegister;
    Button btnAppShare;
    GoogleCloudMessaging gcm;
    Context context;
    String regId;

    public static final String REG_ID = "regId";
    private static final String APP_VERSION = "appVersion";

    static final String TAG = "Register Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        context = getApplicationContext();

        btnGCMRegister = (Button) findViewById(R.id.btnGCMRegister);
        btnGCMRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (TextUtils.isEmpty(regId)) {
                    regId = registerGCM();
                    Log.d("RegisterActivity", "GCM RegId: " + regId);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Already Registered with GCM Server!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btnAppShare = (Button) findViewById(R.id.btnAppShare);
        btnAppShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (TextUtils.isEmpty(regId)) {
                    Toast.makeText(getApplicationContext(), "RegId is empty!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(getApplicationContext(),
                            MainActivity11.class);
                    i.putExtra("regId", regId);
                    Log.d("RegisterActivity",
                            "onClick of Share: Before starting main activity.");
                    startActivity(i);
                    finish();
                    Log.d("RegisterActivity", "onClick of Share: After finish.");
                }
            }
        });
    }

    public void register(View v) {
        Intent intent = new Intent(this,DriverRegistration.class);
        startActivity(intent);
    }


    public void login(View v) throws MalformedURLException{
        String password;
        String email;
        EditText editPassword;
        AutoCompleteTextView editEmail;
        int count = 0;
        String lstring;
        editPassword = (EditText) findViewById(R.id.loginpassword);
        editEmail = (AutoCompleteTextView) findViewById(R.id.loginemail);
        email = editEmail.getText().toString();
        master.mobile=email;
        password = editPassword.getText().toString();
        RegistrationActivity r = new RegistrationActivity();

       // count = r.email_validation(editEmail,getBaseContext());
        if(count == 0){
           // count = passwordLogin(password);
        }
        if (count == 0) {
            lstring = "[{mobile:\"" + email+ "\"},{password:\"" + password+ "\"}]";
            SendLoginData sld = new SendLoginData(lstring);
            URL url = new URL(url_string);
            sld.execute(url);
        }
    }
    /* public  int email_validation(AutoCompleteTextView emailValidate){
         String email = emailValidate.getText().toString().trim();
         int count = 0;
         String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

 // onClick of button perform this simplest code.
         if (email.matches(emailPattern))
         {
             Toast.makeText(this.getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
         }
         else
         {
             Toast.makeText(this.getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
             count++;
         }
         return count;
     }*/
    public int passwordLogin(String password){
        int count = 0;
        if(password.matches("")){
            Toast.makeText(this.getApplicationContext(), "You did not enter a username", Toast.LENGTH_SHORT).show();
            count++;
        }
        return count;
    }

    public  void textToast(String textToDisplay)  {
        Toast.makeText(getBaseContext(), textToDisplay, Toast.LENGTH_SHORT).show();
    }
    public class SendLoginData extends AsyncTask<URL, Void, String>  {
        String data;
        String output ="";
        String st = "";


        SendLoginData(){}
        SendLoginData(String data) {
            this.data = data;
        }

        protected String doInBackground(URL... urls) {
            SendLoginData s = new SendLoginData();
            URL url = urls[0];
            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");

                //  String input = "{\"qty\":100,\"name\":\"iPad 4\"}";

                OutputStream os = conn.getOutputStream();
                // ObjectOutputStream oos = new ObjectOutputStream(os);
                //  oos.writeObject(params);
                System.out.println("input : " + data);
                os.write(data.getBytes());
                os.flush();
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                // String output;
                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    st=st + output;
                    System.out.println(output);
                }

                if(st.equals("failure"))
                {
                    fail();
                    System.out.println(st);
                }
                else
                {
                    success();

                }
                conn.disconnect();

            }
            catch (IOException i){
                i.printStackTrace();
            }

            return st;
        }


        }
    public void fail() {
        Intent intent = new Intent(this,SignIn.class);
        startActivity(intent);
    }
    public void success() {
        Intent intent = new Intent(this, citycabs.class);
        startActivity(intent);
    }


        protected void onPostExecute(String st){
            st = st.trim();
            System.out.println(st);
            if(st.equals("no")){
                Toast.makeText(c, "check it", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(c, "check it ggg", Toast.LENGTH_SHORT).show();
            }
        }

    public String registerGCM() {

        gcm = GoogleCloudMessaging.getInstance(this);
        regId = getRegistrationId(context);

        if (TextUtils.isEmpty(regId)) {

            registerInBackground();

            Log.d("RegisterActivity",
                    "registerGCM - successfully registered with GCM server - regId: "
                            + regId);
        } else {
            Toast.makeText(getApplicationContext(),
                    "RegId already available. RegId: " + regId,
                    Toast.LENGTH_LONG).show();
        }
        return regId;
    }

    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getSharedPreferences(
                MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        String registrationId = prefs.getString(REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }
        int registeredVersion = prefs.getInt(APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "App version changed.");
            return "";
        }
        return registrationId;
    }

    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("RegisterActivity",
                    "I never expected this! Going down, going down!" + e);
            throw new RuntimeException(e);
        }
    }

    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regId = gcm.register(Config.GOOGLE_PROJECT_ID);
                    Log.d("RegisterActivity", "registerInBackground - regId: "
                            + regId);
                    msg = "Device registered, registration ID=" + regId;

                    storeRegistrationId(context, regId);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    Log.d("RegisterActivity", "Error: " + msg);
                }
                Log.d("RegisterActivity", "AsyncTask completed: " + msg);
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                Toast.makeText(getApplicationContext(),
                        "Registered with GCM Server." + msg, Toast.LENGTH_LONG)
                        .show();
            }
        }.execute(null, null, null);
    }

    private void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getSharedPreferences(
                MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        int appVersion = getAppVersion(context);
        Log.i(TAG, "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(REG_ID, regId);
        editor.putInt(APP_VERSION, appVersion);
        editor.commit();
    }
}