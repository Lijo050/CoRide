package com.example.devesh.Coride;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity11 extends Activity
{

	ShareExternalServer appUtil;
	String regId;
	AsyncTask<Void, Void, String> shareRegidTask;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		appUtil = new ShareExternalServer();

		regId = getIntent().getStringExtra("regId");
		Log.d("MainActivity", "regId: " + regId);

		final Context context = this;
		shareRegidTask = new AsyncTask<Void, Void, String>()
		{
			@Override
			protected String doInBackground(Void... params)
			{
				String result = appUtil.shareRegIdWithAppServer(context, regId);
				return result;
			}

			@Override
			protected void onPostExecute(String result)
			{
				shareRegidTask = null;
				Toast.makeText(getApplicationContext(), result,
						Toast.LENGTH_LONG).show();
			}

		};
		shareRegidTask.execute(null, null, null);
	}
	public void reject(View v) {
		Intent intent = new Intent(this,Login.class);
		startActivity(intent);
	}
	public void sendData(View view) throws MalformedURLException{
		Log.e("TEST LOG", "ho gya, aa gya loop me");
		Toast.makeText(this,"devesh",Toast.LENGTH_SHORT).show();
		Master master=new Master();
		String url_string =master.url +"userdata";
		String json ="[{mobile:\""+"12345"+"\"}]";
		SendDataTask snd = new SendDataTask(json);
		URL url = new URL(url_string);
		snd.execute(url);
		Intent intent = new Intent(this,Login.class);
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
