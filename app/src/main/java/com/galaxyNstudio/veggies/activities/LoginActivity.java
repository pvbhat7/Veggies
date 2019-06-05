package com.galaxyNstudio.veggies.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.galaxyNstudio.veggies.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.loginButton)
    Button btnBottomSheet;

    @BindView(R.id.butterLoginButton)
    Button butterLoginButton;


    @BindView(R.id.login_mobile_number)
    EditText loginMobileEditText;

    @BindView(R.id.bottom_sheet)
    LinearLayout layoutBottomSheet;

    BottomSheetBehavior sheetBehavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
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
                if(start == 10) {
                    butterLoginButton.setText("CONTINUE");
                    butterLoginButton.setEnabled(true);
                }
                else if(start > 10)
                    butterLoginButton.setText("INVALID MOBILE NUMBER");
                else
                    butterLoginButton.setText("PLEASE ENTER MOBILE NUMBER");
            }

        });
    }



    @OnClick(R.id.loginButton)
    public void showBottomSheetDialogFragment() {
        LoginBottomSheetFragment loginBottomSheetFragment = new LoginBottomSheetFragment();
        loginBottomSheetFragment.show(getSupportFragmentManager(), loginBottomSheetFragment.getTag());
    }

    @OnClick(R.id.skip)
    public void updateLocationInfo() {
        // ask user for location info then fetch location co-ordinates & save it.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }else{
            //Toast.makeText(this, "LOCATION PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AddressMapActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }




    /*@OnClick(R.id.loginButton)
    public void showBottomSheetDialog() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet, null);

        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        dialog.setContentView(view);
        dialog.show();
    }*/
}
