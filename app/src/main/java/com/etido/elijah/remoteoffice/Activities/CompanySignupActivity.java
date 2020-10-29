package com.etido.elijah.remoteoffice.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.etido.elijah.remoteoffice.DataModel.Company;
import com.etido.elijah.remoteoffice.Database.CompanyWorkerDatabaseHelper;
import com.etido.elijah.remoteoffice.R;
import com.google.android.material.snackbar.Snackbar;

public class CompanySignupActivity extends AppCompatActivity {
    private EditText mCompanyName;
    private Spinner mCategory;
    private EditText mEmail;
    private EditText mConfirmPassword;
    private EditText mPassword;
    private Button mSubmit;
    CompanyWorkerDatabaseHelper companyWorkerDatabaseHelper;
    private ConstraintLayout mRegisterCompany;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_company);
        companyWorkerDatabaseHelper = new CompanyWorkerDatabaseHelper(this);
        initializeViews();

        mSubmit.setOnClickListener(v -> {
            validate();
            sendDataToDatabase();
        });
    }

    private void initializeViews() {
        mCompanyName = findViewById(R.id.company_name);
        mCategory = findViewById(R.id.category_company);
        mEmail = findViewById(R.id.company_email);
        mConfirmPassword = findViewById(R.id.confirm_company_password);
        mPassword = findViewById(R.id.company_password);
        mRegisterCompany = findViewById(R.id.register_company);
        mSubmit = findViewById(R.id.company_submit_button);

    }

    private boolean validate() {
        boolean valid;

        String companyName = mCompanyName.getText().toString();
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        String category =  mCategory.getSelectedItem().toString();
        String confirmPassword =  mConfirmPassword.getText().toString();

        if(companyName.isEmpty() || category.isEmpty()){
            valid = false;
            mCompanyName.setError("Please enter a Company name and Category!");
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
            if(password.length() > 8 ){
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

    private void sendDataToDatabase() {
        String companyName = mCompanyName.getText().toString();
        String category = mCategory.getSelectedItem().toString().trim();
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        if(!companyWorkerDatabaseHelper.checkCompany(email, password)){

            Company company = new Company();
            company.setCompanyName(companyName);
            company.setEmail(email);
            company.setCategory(category);
            company.setPassword(password);

//            companyWorkerDatabaseHelper.addWorker(new Worker(fullName, email, password, category));
            companyWorkerDatabaseHelper.addCompany(company);
            Snackbar.make(mRegisterCompany, "Signed Up successfully", Snackbar.LENGTH_LONG).show();
            clearSignupTextFields();

            Intent companyIntent = new Intent(CompanySignupActivity.this, LoginActivity.class);
            startActivity(companyIntent);
        } else{
            Snackbar.make(mRegisterCompany, "Registration was not successful", Snackbar.LENGTH_LONG).show();
        }
    }

    private void clearSignupTextFields() {
        mCompanyName.setText(null);
        mEmail.setText(null);
        mConfirmPassword.setText(null);
        mPassword.setText(null);
    }


}