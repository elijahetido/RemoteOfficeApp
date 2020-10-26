package com.etido.elijah.remoteoffice.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.etido.elijah.remoteoffice.R;

public class MainActivity extends AppCompatActivity {
    EditText mEmail;
    EditText mPassword;
    Button mLogin;
    TextView mSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.text_email);
        mPassword = findViewById(R.id.text_password);
        mLogin = findViewById(R.id.login_button);
        mSignUp = findViewById(R.id.signup_text);

        mSignUp.setOnClickListener(v -> {
            AlertDialog.Builder success = new AlertDialog.Builder (
                    this);
            ViewGroup viewGroup = findViewById (R.id.content);
            View dialogView = LayoutInflater.from(this).inflate(R.layout.popup_window,
                    viewGroup,false);
            success.setView (dialogView);
            AlertDialog alertDialog = success.create();
            alertDialog.show();


            Button mCompanybutton = alertDialog.findViewById (R.id.company_button);
            Button mWorkerButton = alertDialog.findViewById (R.id.worker_button);

            mCompanybutton.setOnClickListener (view -> {
                Intent intent = new Intent (this,
                        CompanySignupActivity.class);
                startActivity (intent);

            });
            mWorkerButton.setOnClickListener( view ->{
                Intent intent = new Intent (this,
                        WorkerSignupActivity.class);
                startActivity (intent);
            });
        });

    }


}