package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "register";
    private static final int ANIMATION_DURATION = 1000;

    private Context mContext;
    //widgets
    private RelativeLayout rootView;
    private ProgressBar mProgressBar;
    private EditText mUsername;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Button btnregister;
    private TextView linkLogin;
    private ImageView showPasswordToggle;

    //variables
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

        initWidgets();
        init();
    }
    private void init() {

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Activity.INPUT_METHOD_SERVICE);
                assert imm != null;
                imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
            }
        });

        showPasswordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    showPasswordToggle.setImageResource(R.drawable.ic_visibility_on);
                    mPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    showPasswordToggle.setImageResource(R.drawable.ic_visibility_off);
                    mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        //registration button
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEmail.getText().toString();
                username = mUsername.getText().toString();
                password = mPassword.getText().toString();
                confirmPassword = mConfirmPassword.getText().toString();

                if (SignupActivity.this.checkInputs(email, username, password, confirmPassword))
                {
                    if (SignupActivity.this.checkValidity(password) || SignupActivity.this.checkValidity(confirmPassword))
                        if (password.equals(confirmPassword)) {
                            mProgressBar.setVisibility(View.VISIBLE);
                            //register the user here
                        } else {
                            YoYo.with(Techniques.Shake).duration(ANIMATION_DURATION).playOn(mPassword);
                            YoYo.with(Techniques.Shake).duration(ANIMATION_DURATION).playOn(mConfirmPassword);
                            mPassword.setText("");
                            mConfirmPassword.setText("");
                            mPassword.setError("Password don not match");
                            mPassword.requestFocus();
                            mConfirmPassword.setError("Password don not match");
                            mConfirmPassword.requestFocus();
                        }
                }
                else{
                    YoYo.with(Techniques.Shake).duration(ANIMATION_DURATION).playOn(mPassword);
                    YoYo.with(Techniques.Shake).duration(ANIMATION_DURATION).playOn(mConfirmPassword);
                    mPassword.setText("");
                    mConfirmPassword.setText("");
                }
            }
        });

        //link login
        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "on Click:navigating to register screen");
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                SignupActivity.this.startActivity(intent);
            }
        });
    }
    private boolean checkInputs(String email, String username, String password, String confirmPassword) {
        Log.d(TAG, "checkInputs:checking inputs for null values");

        if (username.equals("")) {
            YoYo.with(Techniques.Shake).duration(ANIMATION_DURATION).playOn(mUsername);
            Toast.makeText(mContext, "Empty username field", Toast.LENGTH_SHORT).show();
            mUsername.setError("Please enter a username");
            mUsername.requestFocus();
            return false;
        }
        if (email.equals("")) {
            YoYo.with(Techniques.Shake).duration(ANIMATION_DURATION).playOn(mEmail);
            Toast.makeText(mContext, "Empty email field", Toast.LENGTH_SHORT).show();
            mEmail.setError("Please enter a email-id");
            mEmail.requestFocus();
            return false;
        }
        if (password.equals("")) {
            YoYo.with(Techniques.Shake).duration(ANIMATION_DURATION).playOn(mPassword);
            YoYo.with(Techniques.Shake).duration(ANIMATION_DURATION).playOn(mConfirmPassword);
            mPassword.setText("");
            mConfirmPassword.setText("");
            mPassword.setError("Please enter a password");
            mPassword.requestFocus();
            Toast.makeText(mContext, "Empty password field", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (confirmPassword.equals("")) {
            YoYo.with(Techniques.Shake).duration(ANIMATION_DURATION).playOn(mConfirmPassword);
            mConfirmPassword.setText("");
            mConfirmPassword.setError("Please enter a password");
            mConfirmPassword.requestFocus();
            Toast.makeText(mContext, "Empty confirm password field", Toast.LENGTH_SHORT).show();
            return false;
        }else
            return true;
    }
    private boolean checkValidity(String password) {
        //add more parameters to check ur passwords
        if (password.length() < 6) {
            YoYo.with(Techniques.Shake).duration(ANIMATION_DURATION).playOn(mPassword);
            YoYo.with(Techniques.Shake).duration(ANIMATION_DURATION).playOn(mConfirmPassword);
            mPassword.setText("");
            mConfirmPassword.setText("");
            mPassword.setError("Password too short");
            mPassword.requestFocus();
            mConfirmPassword.setError("Password too short");
            mConfirmPassword.requestFocus();
            return false;
        }
        return true;
    }

    private void initWidgets() {
        Log.d(TAG, "initWidgets: initiliazing widgets");
        rootView = findViewById(R.id.rootView);
        mProgressBar = findViewById(R.id.Registerrequestloadingprogressbar);
        mUsername = findViewById(R.id.username);
        mEmail = findViewById(R.id.input_email);
        mPassword = findViewById(R.id.input_password);
        mConfirmPassword = findViewById(R.id.confirm_password);
        btnregister = findViewById(R.id.btn_register);
        linkLogin = findViewById(R.id.link_login);
        mContext = SignupActivity.this;
        mProgressBar.setVisibility(View.GONE);
        showPasswordToggle = findViewById(R.id.show_pass_btn);
    }


}