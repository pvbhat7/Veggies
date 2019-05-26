package com.galaxyNstudio.veggies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText signup_mobile,signup_email,signup_password,signup_name;
    private Button butterSignUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        butterSignUpButton=findViewById(R.id.butterSignUpButton);
        butterSignUpButton.setOnClickListener(this);
        signup_name=findViewById(R.id.signup_name);
        signup_email=findViewById(R.id.signup_emailId);
        signup_password=findViewById(R.id.signup_password);
        signup_mobile=findViewById(R.id.signup_mobile_number);
        signup_mobile.setText(getIntent().getStringExtra("phone"));

        //TODO : check validations for name , email , password & accordingly change button text
        /*signup_name.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0) {
                    butterSignUpButton.setText("CONTINUE");
                    butterSignUpButton.setEnabled(true);
                }else{
                    butterSignUpButton.setText("ENTER VALID NAME");
                    butterSignUpButton.setEnabled(false);
                }
            }
        });
    }
    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        editTextEmail.setError("Enter a valid email");
        editTextEmail.requestFocus();
        return;
    }*/
}


@Override
public void onClick(View view){
        switch (view.getId()){
            case R.id.butterSignUpButton:
                if(signup_mobile.getText().toString().trim().length() == 0 || signup_email.getText().toString().trim().length() == 0 || signup_name.getText().toString().trim().length() == 0 || signup_password.getText().toString().trim().length() == 0 ){
                    Toast.makeText(getApplicationContext(),"Please enter all details",Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(this, OtpActivity.class);
                    intent.putExtra("phone",signup_mobile.getText().toString().trim());
                    startActivity(intent);
                }
                break;

        }
}
}
