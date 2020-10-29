package com.etido.elijah.remoteoffice.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.etido.elijah.remoteoffice.R;
import com.google.android.material.navigation.NavigationView;

public class CompanyMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}