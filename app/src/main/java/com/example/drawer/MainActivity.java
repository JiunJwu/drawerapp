package com.example.drawer;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        View header=navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                drawer.closeDrawers();
                switch (menuItem.getItemId()){
                    case R.id.nav_contact:
                        mailToDeveloper();
                        return true;
                    case R.id.nav_logout:
                        Toast.makeText(getApplicationContext(), "nav_logout", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return true;
            }
        });

        TextView usname =header.findViewById(R.id.username);
        TextView usemail =header.findViewById(R.id.userEmail);

        if (getIntent().getSerializableExtra("user") != null) {
            User userdata = (User) getIntent().getSerializableExtra("user");
            usname.setText(userdata.getUserName());
            usemail.setText(userdata.getUserEmail());

        } else {
            User userdata = new User("rr1","rrr");
            usname.setText(userdata.getUserName());
        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private void mailToDeveloper() {
          try {
                Uri uri = Uri.parse("mailto:tchloie@gmail.com");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                String subject = getString(R.string.app_name) + " (" + pInfo.versionName + ")";
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                startActivity(intent);
              } catch (Exception e) {
               Toast.makeText(this, R.string.mailfail, Toast.LENGTH_SHORT).show();
              }
        }
}