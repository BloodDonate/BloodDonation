package com.example.jonathandorvilier.dondesang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.jonathandorvilier.dondesang.adapter.BloodPageAdapter;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager vpPager;
 PagerSlidingTabStrip tabsStrip;
     DrawerLayout drawer;
     ActionBarDrawerToggle toggle;
     NavigationView navigationView;
     FloatingActionButton fab;
     Toolbar toolbar;
    View header;
    TextView tvNomLogin, tvTelLogin;
   String id_user, nom_user, telephone_user, birthday_user, sexe_user, gsanguin_user, username;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        sharedPreferences = getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);



        /*id_user=getIntent().getStringExtra("id_user");
        nom_user=getIntent().getStringExtra("nom_user");
        telephone_user=getIntent().getStringExtra("telephone_user");
        birthday_user=getIntent().getStringExtra("birthday_user");
        sexe_user=getIntent().getStringExtra("sexe_user");
        gsanguin_user=getIntent().getStringExtra("gsanguin_user");
        username=getIntent().getStringExtra("username"); */

        id_user=sharedPreferences.getString("id_user", null);
        nom_user=sharedPreferences.getString("nom_user", null);
        telephone_user=sharedPreferences.getString("telephone_user", null);
        birthday_user=sharedPreferences.getString("birthday_user", null);
        sexe_user=sharedPreferences.getString("sexe_user", null);
        gsanguin_user=sharedPreferences.getString("gsanguin_user", null);
        username=sharedPreferences.getString("username", null);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View view) {
                 Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                              .setAction("Action", null).show();
                          }
        });

                      vpPager = (ViewPager) findViewById(R.id.viewpager);
               vpPager.setAdapter(new BloodPageAdapter(getSupportFragmentManager()));
                        tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
              tabsStrip.setIndicatorColor(Color.RED);
             // Attach the view pager to the tab strip
                      tabsStrip.setViewPager(vpPager);

                       drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
               toggle = new ActionBarDrawerToggle(
                       this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
               drawer.setDrawerListener(toggle);
              toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        header = navigationView.getHeaderView(0);
        tvNomLogin = (TextView) header.findViewById(R.id.tvNomLogin);
        tvTelLogin = (TextView) header.findViewById(R.id.tvTelLogin);
        tvNomLogin.setText(nom_user);
        tvTelLogin.setText(telephone_user);

    }

    @Override
    public void onBackPressed() {
               drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
                       drawer.closeDrawer(GravityCompat.START);
                  } else {
                      super.onBackPressed();
                   }
           }

    @Override
   public boolean onCreateOptionsMenu(Menu menu) {
               // Inflate the menu; this adds items to the action bar if it is present.
                        getMenuInflater().inflate(R.menu.main, menu);
                return true;
    }

    @Override
 public boolean onOptionsItemSelected(MenuItem item) {
              // Handle action bar item clicks here. The action bar will
                     // automatically handle clicks on the Home/Up button, so long
                              // as you specify a parent activity in AndroidManifest.xml.
                                     int id = item.getItemId();
                      //noinspection SimplifiableIfStatement
                             if (id == R.id.action_settings) {
                       return true;
                   }

             return super.onOptionsItemSelected(item);
           }


    @SuppressWarnings("StatementWithEmptyBody")
  @Override
 public boolean onNavigationItemSelected(MenuItem item) {
               // Handle navigation view item clicks here.
                       int id = item.getItemId();

                       if (id == R.id.nav_list_demande) {
                     // Handle the camera action
                           } else if (id == R.id.nav_question) {
                           } else if (id == R.id.nav_vos_don) {

                       } else if (id == R.id.nav_aide) {
                           Intent i = new Intent(MainActivity.this, activity_Rules.class);
                           startActivity(i);
                           overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                          } else if (id == R.id.nav_partage) {

                            } else if (id == R.id.nav_params) {

                           }else if (id == R.id.nav_logout) {
                           Intent i = new Intent(MainActivity.this, Login.class);
                           editor = sharedPreferences.edit();
                           editor.clear();
                           editor.apply();
                           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                               finishAffinity();
                           }else{
                               i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // this will clear all the stack
                           }
                           startActivity(i);
                       }

                        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
              drawer.closeDrawer(GravityCompat.START);
              return true;
          }

/*
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }*/
}
