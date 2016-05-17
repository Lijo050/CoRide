package com.example.devesh.Coride;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
public class DriverRegistration extends AppCompatActivity  {
    Master master=new Master();
    // RegistrationActivity ra = new RegistrationActivity();
    Context c = this;
    Button btnGCMRegister;
    Button btnAppShare;
    GoogleCloudMessaging gcm;
    Context context;
    String regId;
    public static final String REG_ID = "regId";
    private static final String APP_VERSION = "appVersion";

    static final String TAG = "Register Activity";
    static Intent i=null;

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };


    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_registration);
        context = getApplicationContext();

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        //populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);



        /**
         * Attempts to sign in or register the account specified by the login form.
         * If there are form errors (invalid email, missing fields, etc.), the
         * errors are presented and no actual login attempt is made.
         */

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }



    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    //private static String url_login = "http://172.16.81.136:8080/REST_Server/locupdate";

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        LocationManager locationManager;
        JSONParser jparser = new JSONParser();
        JSONObject json;
        Double latitude,longitude;


        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
    public void showSettingsAlert(String provider) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                DriverRegistration.this);

        alertDialog.setTitle(provider + " SETTINGS");

        alertDialog
                .setMessage(provider + " is not enabled! Want to go to settings menu?");

        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        DriverRegistration.this.startActivity(intent);
                        dialog.cancel();
                    }
                });

        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Enable location to use APP", Toast.LENGTH_SHORT).show();
                        dialog.cancel();

                    }
                });

        alertDialog.show();
    }

    public void callLocationService() {
        Log.e("callLocation", "InsideCallLocation");
        //Log.e("callLocation", "" + userid);
        if(i==null) {
            Log.e("Location Service","Starting location service");
            i = new Intent(this, LocationService.class);
            i.putExtra("email",master.mobile1);
            startService(i);
        }
        else{
            Log.e("Location Service","already ON");
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    public void sendData(View view) throws MalformedURLException{
        Log.e("TEST LOG", "Entered the loop");
        Toast.makeText(this,"coride",Toast.LENGTH_SHORT).show();
        Master master=new Master();
        String url_string =master.url +"driverdata";
        EditText n_edittext;
        EditText mob_edittext;
        EditText confrm_edittext;
        EditText age_edittext;
        EditText lnumber;
        EditText pnumber;
        EditText vnumber;
        EditText mnumber;
        EditText capacity;
        String name;
        String mobile;
        String conpassword;
        String password;
        String address;
        String email;
        String slnumber;
        String spnumber;
        String svnumber;
        String smnumber;
        String scapacity;
        int count = 0;
        n_edittext = (EditText)findViewById(R.id.name);
        mob_edittext =(EditText)findViewById(R.id.mobileno);
        confrm_edittext =(EditText)findViewById(R.id.confirmpassword);
        mPasswordView = (EditText)findViewById(R.id.password);
        mEmailView = (AutoCompleteTextView)findViewById(R.id.email);
        age_edittext = (EditText)findViewById(R.id.address);
        lnumber=(EditText)findViewById(R.id.lnumber);
        pnumber=(EditText)findViewById(R.id.pnumber);
        vnumber=(EditText)findViewById(R.id.vnumber);
        mnumber=(EditText)findViewById(R.id.mnumber);
        capacity=(EditText)findViewById(R.id.capacity);
        name = n_edittext.getText().toString();
        mobile = mob_edittext.getText().toString();
        master.mobile1=mobile;
        password = mPasswordView.getText().toString();
        conpassword = confrm_edittext.getText().toString();
        address= age_edittext.getText().toString();
        email = mEmailView.getText().toString();
        slnumber=lnumber.getText().toString();
        spnumber=pnumber.getText().toString();
        svnumber=vnumber.getText().toString();
        smnumber=mnumber.getText().toString();;
        scapacity=capacity.getText().toString();



        regId = registerGCM();
        System.out.println(regId);
//        String reg=regId.toString();
        count =  validate(name,email,mobile,password,conpassword,address);
        if(count == 0){
            count = email_validation(mEmailView,getBaseContext());
        }
        if(count == 0){
            count =  password_matcher(password,conpassword);
        }

        if(count == 0) {



            String json ="[{name:\""+name+"\"},{mobile:\""+mobile+"\"},{password:\""+password+"\"},{email:\""+email+"\"},{address:\""+address+"\"},{license:\""+slnumber+"\"},{pan:\""+spnumber+"\"},{vehicle:\""+svnumber+"\"},{model:\""+smnumber+"\"},{capacity:\""+scapacity+"\"},{regid:\""+regId+"\"}]";
            // snd.addNameValuePair("name", name);
            //snd.addNameValuePair("mobile", mobile);
            //snd.addNameValuePair("password", password);
            // snd.addNameValuePair("email",email);
            //snd.addNameValuePair("age", age);
            SendDataTask snd = new SendDataTask(json); //Go to sendData class fromhere
            URL url = new URL(url_string);
            snd.execute(url);



        }

        System.out.println("i am hear");
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)) {
            Log.e("Coride", "Provider enable");
            callLocationService();
        } else {
            Log.e("Coride", "provider disabled");
            showSettingsAlert("NETWORK_PROVIDER");

        }

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

                callLocationService();
            }

            @Override
            public void onProviderDisabled(String s) {
                showSettingsAlert("NETWORK_PROVIDER");
            }
        };
        //   dialog.cancel();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
            return;

        }
        locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 0, 0, locationListener);


    }


    //end of send data
    public  int email_validation(AutoCompleteTextView emailValidate,Context mcontext){
        String email = emailValidate.getText().toString().trim();
        int count = 0;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

// onClick of button perform this simplest code.
        if (email.matches(emailPattern))
        {
            Toast.makeText(mcontext,"valid email address",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(mcontext,"Invalid email address", Toast.LENGTH_SHORT).show();
            count++;
        }
        return count;
    }
    public int validate(String name,String email,String mobile,String password,String conpassword,String age) {
        int count = 0;
        if (name.matches("")) {
            Toast.makeText(this.getApplicationContext(), "You did not enter a username", Toast.LENGTH_SHORT).show();
            count++;

        }
        if (email.matches("")) {
            Toast.makeText(this.getApplicationContext(), "You did not enter  an email_id", Toast.LENGTH_SHORT).show();
            count++;
        }
        if (password.matches("")) {
            Toast.makeText(this.getApplicationContext(), "You did not enter a password", Toast.LENGTH_SHORT).show();
            count++;
        }
        if (conpassword.matches("")) {
            Toast.makeText(this.getApplicationContext(), "You did not enter a confirm password", Toast.LENGTH_SHORT).show();
            count++;
        }
        if (age.matches("")) {
            Toast.makeText(this.getApplicationContext(), "You did not enter  age", Toast.LENGTH_SHORT).show();
            count++;
        }
        if (mobile.matches("")) {
            Toast.makeText(this.getApplicationContext(), "You did not enter mobile number", Toast.LENGTH_SHORT).show();
            count++;
        }
        return  count;
    }
    public int password_matcher(String password,String conpassword){
        int count = 0;
        if(!password.matches(conpassword)){
            Toast.makeText(this.getApplicationContext(), "password is not matching", Toast.LENGTH_SHORT).show();
            count++;
        }
        return count;

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

        System.out.println("------------------------------------------------------");
        final SharedPreferences prefs = getSharedPreferences(
                MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        int appVersion = getAppVersion(context);

        System.out.println(appVersion);
        Log.i(TAG, "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(REG_ID, regId);
        editor.putInt(APP_VERSION, appVersion);
        editor.commit();
    }


}

