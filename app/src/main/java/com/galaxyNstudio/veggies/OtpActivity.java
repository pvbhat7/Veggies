package com.galaxyNstudio.veggies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class OtpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView otpLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        otpLabel=(TextView)findViewById(R.id.otpLabel);
        otpLabel.setText("OTP sent to "+getIntent().getStringExtra("phone"));

    }

    @Override
    public void onClick(View view) {

    }
}
