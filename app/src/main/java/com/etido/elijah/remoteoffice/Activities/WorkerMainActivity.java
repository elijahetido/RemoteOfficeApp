package com.etido.elijah.remoteoffice.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.etido.elijah.remoteoffice.Database.CompanyWorkerDatabaseHelper;
import com.etido.elijah.remoteoffice.Fragments.CompanyJobsFragment;
import com.etido.elijah.remoteoffice.Fragments.CompanyMessagesFragment;
import com.etido.elijah.remoteoffice.Fragments.CompanyWorkersFragment;
import com.etido.elijah.remoteoffice.Fragments.WorkerCompaniesFragment;
import com.etido.elijah.remoteoffice.Fragments.WorkerJobsFragment;
import com.etido.elijah.remoteoffice.Fragments.WorkerMessagesFragment;
import com.etido.elijah.remoteoffice.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class WorkerMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    private WorkerJobsFragment workerJobsFragment;
    private WorkerCompaniesFragment workerCompaniesFragment;
    private WorkerMessagesFragment workerMessagesFragment;
    CompanyWorkerDatabaseHelper companyWorkerDatabaseHelper;
    Image workerImage;
    TextView workerName;
    TextView workerEmail;
    String workerEmailHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_dashboard_activity);
        Toolbar toolbar = findViewById(R.id.worker_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout workerDrawer = findViewById(R.id.worker_drawer);
        ActionBarDrawerToggle workertoggle = new ActionBarDrawerToggle(
                this, workerDrawer, toolbar, R.string.nav_open, R.string.nav_close);
        workerDrawer.addDrawerListener(workertoggle);
        workertoggle.syncState();

        NavigationView navigationView =  findViewById(R.id.worker_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        workerJobsFragment = new WorkerJobsFragment();
        openWorkerFragment (workerJobsFragment);

        bottomNavigationView = findViewById(R.id.worker_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                     int id = item.getItemId();
                    if (id == R.id.worker_jobs) {
                        workerJobsFragment = new WorkerJobsFragment();
                        openWorkerFragment(workerJobsFragment);
                        return true;
                    }
                    else if (id == R.id.worker_companies){
                        workerCompaniesFragment = new WorkerCompaniesFragment();
                        openWorkerFragment(workerCompaniesFragment);
//                     startActivity(new Intent(this, CompanyWorkers.class));
                        return true;
                    }
                    else if (id == R.id.worker_messages){
                        workerMessagesFragment = new WorkerMessagesFragment();
                        openWorkerFragment(workerMessagesFragment);
                        return true;
                    }
               return true;
        });
        workerNameEmail();
    }

    private void workerNameEmail() {
        Intent intent = getIntent();
        workerEmailHolder = intent.getStringExtra(LoginActivity.workerEmail);
        NavigationView navigationView =  findViewById(R.id.worker_nav_view);
        View headerView = navigationView.getHeaderView(0);
        workerEmail = headerView.findViewById(R.id.worker_email_header);
        workerEmail.setText(workerEmailHolder);
    }

    private void openWorkerFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.worker_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume ();
//        companyNameEmail();
        openWorkerDrawer();
    }

    private void openWorkerDrawer() {
        Handler handler = new Handler (Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @SuppressLint("WrongConstant")
            @Override
            public void run() {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.worker_drawer);
                drawer.openDrawer(Gravity.START);
            }
        }, 2000);
    }
}