package com.galaxyNstudio.veggies.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.galaxyNstudio.veggies.R;
import com.galaxyNstudio.veggies.api.RetrofitClient;
import com.galaxyNstudio.veggies.responseWrapper.MobileExist;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginBottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    EditText loginMobileEditText;

    Handler handler;

    Button butterLoginButton;
    ProgressDialog progressDialog;
    private int pStatus = 0;
    ProgressBar progressBar4;

    public LoginBottomSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottom_sheet, container, false);

        progressBar4=view.findViewById(R.id.progressBar1);
        loginMobileEditText = (EditText) view.findViewById(R.id.login_mobile_number);
        butterLoginButton = (Button) view.findViewById(R.id.butterLoginButton);
        butterLoginButton.setOnClickListener(this);
        if (loginMobileEditText != null) {

            loginMobileEditText.requestFocus();
            loginMobileEditText.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    InputMethodManager keyboard = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    keyboard.showSoftInput(loginMobileEditText, 0);
                }
            }, 100);
            loginMobileEditText.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable s) {
                    System.out.println("");

                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    System.out.println("");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() == 10) {
                        butterLoginButton.setEnabled(true);
                        butterLoginButton.setBackgroundColor(Color.BLACK);
                        butterLoginButton.setText("CONTINUE");
                        butterLoginButton.setEnabled(true);
                    } else if (s.length() > 10) {
                        butterLoginButton.setEnabled(false);
                        butterLoginButton.setBackgroundColor(android.graphics.Color.parseColor("#BFBFBF"));
                        butterLoginButton.setText("INVALID MOBILE NUMBER");
                    } else if (s.length() < 10) {
                        butterLoginButton.setEnabled(false);
                        butterLoginButton.setBackgroundColor(android.graphics.Color.parseColor("#BFBFBF"));
                        butterLoginButton.setText("PLEASE ENTER MOBILE NUMBER");
                    }
                }

            });
        }

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.butterLoginButton:
                /*progressDialog=new ProgressDialog(getActivity());
                //progressDialog.setMessage("Loading data...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();*/
                //progressBar4 = v.findViewById(R.id.progressBar1);
                progressBar4.getProgressDrawable().setColorFilter(
                        Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                progressBar4.setVisibility(View.VISIBLE);
                butterLoginButton.setEnabled(false);

                //showProgressBar(v);


                String mobile = loginMobileEditText.getText().toString().trim();

                //TODO  check first whether user is already registered or not,
                //TODO if User already registered then redirect to otp activity else redirect to signup activity
                checkUserAlreadyPresent(mobile);



                break;

        }
    }

    private void showProgressBar(View v) {
        progressBar4 = v.findViewById(R.id.progressBar1);

        handler = new Handler();
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus < 100) {

                    pStatus++;

                    if(pStatus == 99){
                        pStatus = 0;
                    }
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            progressBar4.setProgress(pStatus);
                            progressBar4.setSecondaryProgress(pStatus+15);
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(16); //thread will take approx 3 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void checkUserAlreadyPresent(final String mobile) {

        Call<MobileExist> call = RetrofitClient
                .getInstance()
                .getApi()
                .checkUserMobileExists(mobile);

        call.enqueue(new Callback<MobileExist>() {
            @Override
            public void onResponse(Call<MobileExist> call, Response<MobileExist> response) {
                progressBar4.setVisibility(View.GONE);
                //progressDialog.dismiss();
                if (response.code() == 201) {
                    if(response != null)
                    {
                        MobileExist res = response.body();
                        if (res.getError()) {
                            // redirect to OTP screen
                            Intent intent = new Intent(getActivity(), OtpActivity.class);
                            intent.putExtra("phone", mobile);
                            intent.putExtra("parent_command","login_activity");
                            startActivity(intent);
                        } else {
                            //new user creation so redirect to signup activity
                            Intent intent = new Intent(getActivity(), SignUpActivity.class);
                            intent.putExtra("phone", mobile);
                            startActivity(intent);
                        }
                    }
                } else if (response.code() == 422) {
                    Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MobileExist> call, Throwable t) {
                progressBar4.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });


    }
}


