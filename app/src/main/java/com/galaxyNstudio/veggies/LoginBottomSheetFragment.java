package com.galaxyNstudio.veggies;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import api.RetrofitClient;
import butterknife.BindView;
import model.MobileExist;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginBottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    EditText loginMobileEditText;

    Button butterLoginButton;

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

                String mobile = loginMobileEditText.getText().toString().trim();

                //TODO  check first whether user is already registered or not,
                //TODO if User already registered then redirect to otp activity else redirect to signup activity
                checkUserAlreadyPresent(mobile);



                break;

        }
    }

    public void checkUserAlreadyPresent(final String mobile) {

        boolean flag=false;

        Call<MobileExist> call = RetrofitClient
                .getInstance()
                .getApi()
                .checkUserMobileExists(mobile);

        call.enqueue(new Callback<MobileExist>() {
            @Override
            public void onResponse(Call<MobileExist> call, Response<MobileExist> response) {
                if (response.code() == 201) {

                    MobileExist res = response.body();
                    if (res.getError().equals(Boolean.TRUE)) {
                        // redirect to OTP screen
                        Intent intent = new Intent(getActivity(), OtpActivity.class);
                        intent.putExtra("phone", mobile);
                        startActivity(intent);
                    } else if (res.getError().equals(Boolean.FALSE)) {
                        //new user creation so redirect to signup activity
                        Intent intent = new Intent(getActivity(), SignUpActivity.class);
                        intent.putExtra("phone", mobile);
                        startActivity(intent);
                    }
                    Toast.makeText(getActivity(), res.getMessage(), Toast.LENGTH_LONG).show();

                } else if (response.code() == 422) {
                    Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MobileExist> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });


    }
}


