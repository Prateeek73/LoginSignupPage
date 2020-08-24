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

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "activity_login";
    private static final int ANIMATION_DURATION = 1000;

    private Context mContext;
    private RelativeLayout rootView;
    private ProgressBar mProgressBar;
    private EditText mEmail;
    private EditText mPassword;
    private ImageView showPasswordToggle;
    private Button btnLogin;
    private TextView linkSignup;
    private TextView forgotPassword;

    public LoginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        initializeWidgets();
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
        linkSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "on Click:navigating to register screen");
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"Add a dialog for it",Toast.LENGTH_SHORT).show();
                //apply procedure for forgot password
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                if (email.equals("")) {
                    mProgressBar.setVisibility(View.GONE);
                    YoYo.with(Techniques.Shake).duration(ANIMATION_DURATION).playOn(mEmail);
                    mEmail.setText("");
                    mEmail.setError("Please enter a email-id");
                    mEmail.requestFocus();

                } else if (password.equals("")) {
                    mProgressBar.setVisibility(View.GONE);
                    YoYo.with(Techniques.Shake).duration(ANIMATION_DURATION).playOn(mPassword);
                    mPassword.setText("");
                    mPassword.setError("Please enter a password");
                    mPassword.requestFocus();
                } else {
                    Toast.makeText(mContext,"Add ur authentication process here",Toast.LENGTH_LONG).show();
                    //Redirect yourself to another activity after performing authentication test
                }
            }
        });
    }

    private void initializeWidgets() {
        Log.d(TAG, "initializeWidgets: ");
        rootView = findViewById(R.id.rootView);
        mProgressBar = findViewById(R.id.loginrequestloadingprogressbar);
        mProgressBar.setVisibility(View.GONE);
        mEmail = findViewById(R.id.input_email);
        mPassword = findViewById(R.id.input_password);
        showPasswordToggle = findViewById(R.id.show_pass_btn);
        btnLogin = findViewById(R.id.btn_login);
        linkSignup = findViewById(R.id.link_signup);
        forgotPassword = findViewById(R.id.forgotPassword);
        mContext = LoginActivity.this;
    }
}