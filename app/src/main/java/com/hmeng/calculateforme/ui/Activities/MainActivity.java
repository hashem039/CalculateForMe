package com.hmeng.calculateforme.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.hmeng.calculateforme.Helpers.LocaleHelper;
import com.hmeng.calculateforme.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    //Configuration options for NavigationUI methods that interact with implementations of the app bar pattern such as Toolbar, CollapsingToolbarLayout, and ActionBar
    private AppBarConfiguration mAppBarConfiguration;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //Represents a standard navigation menu for application. The menu contents can be populated by a menu resource file.
        //NavigationView is typically placed inside a DrawerLayout.
        NavigationView navigationView = findViewById(R.id.nav_view);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
       // Navigation flows and destinations are determined by the navigation graph owned by the controller.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //bottom
        BottomNavigationView navView = findViewById(R.id.bottom_nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        final NavController navController2 = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController2);
/*        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.nav_gallery: {
                        transaction.replace(R.id.container, new SummaryFragment()).commit();
                        navController2.navigate(R.id.nav_gallery);
                        Toast.makeText(getApplicationContext(),"Hashem at Galaery",Toast.LENGTH_LONG).show();
                        break;
                    }
                    case R.id.nav_home: {
                        transaction.replace(R.id.container, new HomeFragment()).commit();
                        navController2.navigate(R.id.nav_home);
                        Toast.makeText(getApplicationContext(),"Hashem at Home",Toast.LENGTH_LONG).show();
                        break;
                    }
                    case R.id.nav_slideshow: {
                        transaction.replace(R.id.container, new SlideshowFragment()).commit();
                        navController2.navigate(R.id.nav_slideshow);
                        Toast.makeText(getApplicationContext(),"Hashem at slideshow",Toast.LENGTH_LONG).show();
                        break;
                    }
                }
                return true;
            }
        });*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.setLocale(newBase));
    }
}