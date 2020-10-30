package com.etido.elijah.remoteoffice.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.etido.elijah.remoteoffice.R;
import com.google.android.material.navigation.NavigationView;

public class CompanyMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
//        ,View.OnClickListener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_dashboard_activity);
        Toolbar toolbar = findViewById(R.id.company_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout companyDrawer = findViewById(R.id.company_drawer);
        ActionBarDrawerToggle companytoggle = new ActionBarDrawerToggle(
                this, companyDrawer, toolbar, R.string.nav_open, R.string.nav_close);
        companyDrawer.addDrawerListener(companytoggle);
        companytoggle.syncState();

        NavigationView navigationView =  findViewById(R.id.company_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume ();
        openCompanyDrawer();
    }

    private void openCompanyDrawer() {
        Handler handler = new Handler (Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @SuppressLint("WrongConstant")
            @Override
            public void run() {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.company_drawer);
                drawer.openDrawer(Gravity.START);
            }
        }, 2000);

    }


//        @Override
//    public void onClick(View view){
//        switch (view.getId()){
//            case R.id.login_button:
//                break;
//            case R.id.signup_text:
//                break;
//        }
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.company_jobs) {
//            startActivity(new Intent(this, CompanyJobs.class));
//            return true;
//        } else if (id == R.id.company_workers){
//            startActivity(new Intent(this, CompanyWorkers.class));
//            return true;
//        } else if (id == R.id.company_messages){
//            startActivity(new Intent(this, CompanyMessages.class));
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}