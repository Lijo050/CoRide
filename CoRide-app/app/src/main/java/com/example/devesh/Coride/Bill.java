package com.example.devesh.Coride;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.lang.*;

public class Bill extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        Master master=new Master();

        String[] parts = master.distance.split(" ");
        String dis = parts[0];
        float dist = Float.parseFloat(dis);
        float amount1= dist*10;

        String amount=String.valueOf(amount1);
        EditText edtText = (EditText) findViewById(R.id.phone);
        edtText.setText(master.mobile);
        edtText.setEnabled(false);
        edtText.setTextColor(Color.parseColor("#FFFFFF"));
        EditText edtText1 = (EditText) findViewById(R.id.source);
        edtText1.setText(master.s1);
        edtText1.setEnabled(false);
        edtText1.setTextColor(Color.parseColor("#FFFFFF"));
        EditText edtText2 = (EditText) findViewById(R.id.destination);
        edtText2.setText(master.d1);
        edtText2.setEnabled(false);
        edtText2.setTextColor(Color.parseColor("#FFFFFF"));
        EditText edtText3 = (EditText) findViewById(R.id.distance);
        edtText3.setText(master.distance);
        edtText3.setEnabled(false);
        edtText3.setTextColor(Color.parseColor("#FFFFFF"));
        EditText edtText4 = (EditText) findViewById(R.id.rate);
        edtText4.setText("10");
        edtText4.setEnabled(false);
        edtText4.setTextColor(Color.parseColor("#FFFFFF"));
        EditText edtText5 = (EditText) findViewById(R.id.amount);
        edtText5.setText(amount);
        edtText5.setEnabled(false);
        edtText5.setTextColor(Color.parseColor("#FFFFFF"));

    }
    public void home(View v) {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
}
