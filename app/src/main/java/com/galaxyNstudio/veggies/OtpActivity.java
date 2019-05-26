package com.galaxyNstudio.veggies;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class OtpActivity extends AppCompatActivity implements View.OnClickListener {


    EditText otp_1,otp_2,otp_3,otp_4,otp_5,otp_6;
    Button resendOtpButton,verifyOtpButton;
    TextView otpLabel;
    //Add this on top where other variables are declared
    String mVerificationId;
    //Add it below the lines where you declared the fields
    //Add this on top where other variables are declared
    PhoneAuthProvider.ForceResendingToken mResendToken;
    private FirebaseAuth mAuth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        getSupportActionBar().hide();
        initFields();
        //otpLabel.setText("OTP sent to "+getIntent().getStringExtra("phone"));


        //Add it in the onCreate method, after calling method initFields()
        mAuth = FirebaseAuth.getInstance();

        initFireBaseCallbacks();
        sendOtp();

    }

    void initFields() {
        resendOtpButton = findViewById(R.id.resendOtpButton);
        verifyOtpButton = findViewById(R.id.verifyOtpButton);
        otp_1=findViewById(R.id.otp_1);
        otp_2=findViewById(R.id.otp_2);
        otp_3=findViewById(R.id.otp_3);
        otp_4=findViewById(R.id.otp_4);
        otp_5=findViewById(R.id.otp_5);
        otp_6=findViewById(R.id.otp_6);
        resendOtpButton.setOnClickListener(this);
        verifyOtpButton.setOnClickListener(this);

    }

    ///Add this method below auth initialization in the onCreate method.
    void initFireBaseCallbacks() {

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                String smscode=credential.getSmsCode();
                if(smscode != null){
                    byte[] smsbytes = smscode.getBytes();
                    for(int i=0;i<smsbytes.length;i++){
                        char ch = (char) smsbytes[i];
                        switch (i){
                            case 0:
                                otp_1.setText(String.valueOf(ch));
                                break;
                            case 1:
                                otp_2.setText(String.valueOf(ch));
                                break;
                            case 2:
                                otp_3.setText(String.valueOf(ch));
                                break;
                            case 3:
                                otp_4.setText(String.valueOf(ch));
                                break;
                            case 4:
                                otp_5.setText(String.valueOf(ch));
                                break;
                            case 5:
                                otp_6.setText(String.valueOf(ch));
                                break;
                        }
                    }

                    verifyOtp();
                }

                //Toast.makeText(OtpActivity.this, "Verification Complete", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(OtpActivity.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                resendOtpButton.setEnabled(true);
                resendOtpButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                Toast.makeText(OtpActivity.this, "Code Sent", Toast.LENGTH_SHORT).show();
                mVerificationId = verificationId;
                mResendToken = token; //Add this line to save the resend token
            }
        };
    }



    public void sendOtp(){
        try{
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+91"+getIntent().getStringExtra("phone"),        // Phone number to verify
                    1,                 // Timeout duration
                    TimeUnit.MINUTES,   // Unit of timeout
                    this,               // Activity (for callback binding)
                    mCallbacks);
        }
        catch (Exception e){}
    }

    public void resentOtp(){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+getIntent().getStringExtra("phone"),          // Phone number to verify
                1  ,               // Timeout duration
                TimeUnit.MINUTES,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                mResendToken);             // Force Resending Token from callbacks
    }

    public void verifyOtp(){
        String OTP=otp_1.getText().toString().trim()+
                otp_2.getText().toString().trim()+
                otp_3.getText().toString().trim()+
                otp_4.getText().toString().trim()+
                otp_5.getText().toString().trim()+
                otp_6.getText().toString().trim();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, OTP);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(OtpActivity.this, "Verification Success", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = task.getResult().getUser();
                            Intent intent=new Intent(OtpActivity.this,MainActivity.class);
                            startActivity(intent);
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(OtpActivity.this, "Verification Failed, Invalid credentials", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    public void sighOut(){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(OtpActivity.this, "Signing out ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.resendOtpButton:
                resentOtp();
                break;
            case R.id.verifyOtpButton:
                verifyOtp();
                break;
        }
    }



}
