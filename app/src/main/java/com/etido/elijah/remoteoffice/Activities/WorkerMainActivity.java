package com.etido.elijah.remoteoffice.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.MenuItem;

import com.etido.elijah.remoteoffice.R;
import com.google.android.material.navigation.NavigationView;

public class WorkerMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_dashboard_activity);
        Toolbar toolbar = findViewById(R.id.worker_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout workerDrawer = findViewById(R.id.worker_drawer);
        ActionBarDrawerToggle companytoggle = new ActionBarDrawerToggle(
                this, workerDrawer, toolbar, R.string.nav_open, R.string.nav_close);
        workerDrawer.addDrawerListener(companytoggle);
        companytoggle.syncState();

        NavigationView navigationView =  findViewById(R.id.worker_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume ();
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