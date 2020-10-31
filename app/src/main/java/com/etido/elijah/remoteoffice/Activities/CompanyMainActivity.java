package com.etido.elijah.remoteoffice.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.MenuItem;

import com.etido.elijah.remoteoffice.Fragments.CompanyJobsFragment;
import com.etido.elijah.remoteoffice.Fragments.CompanyMessagesFragment;
import com.etido.elijah.remoteoffice.Fragments.CompanyWorkersFragment;
import com.etido.elijah.remoteoffice.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class CompanyMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
//        ,View.OnClickListener
    BottomNavigationView bottomNavigationView;
    private CompanyJobsFragment companyJobsFragment;
    private CompanyWorkersFragment companyWorkersFragment;
    private CompanyMessagesFragment companyMessagesFragment;

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

        bottomNavigationView = findViewById(R.id.company_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new
                BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item){
                    int id = item.getItemId();
//
                 if (id == R.id.company_jobs) {
                    companyJobsFragment = new CompanyJobsFragment();
                    OpenFragment(companyJobsFragment);
                     return true;
                 }
                 else if (id == R.id.company_workers){
                     companyWorkersFragment = new CompanyWorkersFragment();
                     OpenFragment(companyWorkersFragment);
//                     startActivity(new Intent(this, CompanyWorkers.class));
                     return true;
                 }
                 else if (id == R.id.company_messages){
                     companyMessagesFragment = new CompanyMessagesFragment();
                     OpenFragment(companyMessagesFragment);
                    return true;
                    }

                    return true;
                } });
    }

    private void OpenFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.company_container, fragment);
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