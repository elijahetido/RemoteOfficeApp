package com.etido.elijah.remoteoffice.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.etido.elijah.remoteoffice.Database.CompanyWorkerDatabaseHelper;
import com.etido.elijah.remoteoffice.R;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity{
    private EditText mEmail;
    private EditText mPassword;
    private Button mLogin;
    private TextView mSignUp;
    CompanyWorkerDatabaseHelper companyWorkerDatabaseHelper;
    private ConstraintLayout mConstraintLayout;
    public static final String workerEmail = " ";
    public static final String workerPassword = " ";
    public static final String companyEmail = " ";
    public static final String companyPassword = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        companyWorkerDatabaseHelper = new CompanyWorkerDatabaseHelper(this);
        initializeViews();

        mSignUp.setOnClickListener(v -> {
            AlertDialog.Builder success = new AlertDialog.Builder (
                    this);
            ViewGroup viewGroup = findViewById (R.id.content);
            View dialogView = LayoutInflater.from(this).inflate(R.layout.signup_popup_window,
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
        mLogin.setOnClickListener(v -> {
            validate();
            verifyData();
        });
    }

    private void initializeViews(){
        mEmail = findViewById(R.id.text_email);
        mPassword = findViewById(R.id.text_password);
        mLogin = findViewById(R.id.login_button);
        mSignUp = findViewById(R.id.signup_text);
        mConstraintLayout = findViewById(R.id.login_layout);
    }

//    @Override
//    public void onClick(View view){
//        switch (view.getId()){
//            case R.id.login_button:
//                verifyData();
//                break;
//            case R.id.signup_text:
//                signUp();
//                break;
//        }
//    }

    public void verifyData() {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

            if(companyWorkerDatabaseHelper.checkWorker(email, password)){
                Intent workerIntent = new Intent(LoginActivity.this, WorkerMainActivity.class);
                workerIntent.putExtra(workerEmail, email);
//              workerIntent.putExtra(workerPassword, password);
                startActivity(workerIntent);

            } else if (companyWorkerDatabaseHelper.checkCompany(email, password)){
                Intent companyIntent = new Intent(LoginActivity.this, CompanyMainActivity.class);
                companyIntent.putExtra(companyEmail, email);
//                companyIntent.putExtra(companyPassword, password);
                startActivity(companyIntent);
            } else {
                Snackbar.make(mConstraintLayout," Login Unsuccessful",Snackbar.LENGTH_LONG).show();
        }
    }

    public boolean validate(){
        boolean valid;
        valid = false;

        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            valid = false;
            mEmail.setError("please enter a valid email");
        }else{
            valid = true;
            mEmail.setError(null);
        }

        if(password.isEmpty()){
            valid = false;
            mEmail.setError("Please enter a valid password!");
        }else{
            if(password.length() > 5){
                valid = true;
                mEmail.setError(null);
            }else {
                valid = false;
                mEmail.setError("password is too short!");
            }
        }
        return valid;
    }

//    private void signUp(){
//
//    }

    private void clearTextFields(){
        mEmail.setText("");
        mPassword.setText("");
    }



}