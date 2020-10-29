package com.etido.elijah.remoteoffice.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.etido.elijah.remoteoffice.DataModel.Worker;
import com.etido.elijah.remoteoffice.Database.CompanyWorkerDatabaseHelper;
import com.etido.elijah.remoteoffice.R;
import com.google.android.material.snackbar.Snackbar;

public class WorkerSignupActivity extends AppCompatActivity {
    private EditText mFullName;
    private Spinner mCategory;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Button mSubmit;
    CompanyWorkerDatabaseHelper companyWorkerDatabaseHelper;
    private ConstraintLayout mRegisterWorker;
    SQLiteDatabase db;
//    Worker worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_worker);
        companyWorkerDatabaseHelper = new CompanyWorkerDatabaseHelper(this);
        initializeViews();

        mSubmit.setOnClickListener(v -> {
            validate();
            sendDataToDatabase();
        });

    }

    private void initializeViews() {
        mFullName = findViewById(R.id.workers_name);
        mCategory = findViewById(R.id.category_worker);
        mEmail = findViewById(R.id.workers_email);
        mPassword = findViewById(R.id.worker_password);
        mConfirmPassword = findViewById(R.id.confirm_worker_password);
        mSubmit = findViewById(R.id.worker_submit_button);
        mRegisterWorker = findViewById(R.id.register_worker);

    }

    private void sendDataToDatabase(){
        String fullName = mFullName.getText().toString();
        String category = mCategory.getSelectedItem().toString().trim();
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        if(!companyWorkerDatabaseHelper.checkWorker(email, password)){

            Worker worker = new Worker();
            worker.setFullName(fullName);
            worker.setEmail(email);
            worker.setCategory(category);
            worker.setPassword(password);

//            companyWorkerDatabaseHelper.addWorker(new Worker(fullName, email, password, category));
            companyWorkerDatabaseHelper.addWorker(worker);
            Snackbar.make(mRegisterWorker, "Signed Up successfully", Snackbar.LENGTH_LONG).show();
            clearSignupTextFields();
            Intent companyIntent = new Intent(WorkerSignupActivity.this, LoginActivity.class);
            startActivity(companyIntent);
        } else{
            Snackbar.make(mRegisterWorker, "Registration was not successful", Snackbar.LENGTH_LONG).show();
        }
    }

    public boolean validate(){
        boolean valid;

        String fullName = mFullName.getText().toString();
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        String category =  mCategory.getSelectedItem().toString();
        String confirmPassword =  mConfirmPassword.getText().toString();

        if(fullName.isEmpty() || category.isEmpty()){
            valid = false;
            mFullName.setError("Please enter a Company name and Category!");
        }

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
        } else {
            if(password.length() > 5){
                valid = true;
                mEmail.setError(null);
            } else {
                valid = false;
                mEmail.setError("password is too short!");
            }
        }

        if(!confirmPassword.contentEquals(password)){
            valid = false;
            mConfirmPassword.setError("Please confirm your password");
        } else {
            valid = true;
            mConfirmPassword.setError(null);
        }
        return valid;
    }
    private void clearSignupTextFields(){

        mFullName.setText(null);
        mEmail.setText(null);
        mConfirmPassword.setText(null);
        mPassword.setText(null);

    }
}