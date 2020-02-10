package com.Sunokitaab.sunokitaab;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.Sunokitaab.sunokitaab.Download_Audio.DownloadedFragment;
import com.Sunokitaab.sunokitaab.login_signup.signIn;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainUi extends AppCompatActivity {

    BottomNavigationView navigation;
    FrameLayout frameLayout;

//    HomeFragment homeFragment;
//    FavesFragment favesFragment;
    SearchFragment searchFragment;
    DownloadedFragment downloadedFragment;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);
        getSupportActionBar().setTitle("SunoKitaab");

        if (ActivityCompat.checkSelfPermission(MainUi.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.checkSelfPermission(MainUi.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainUi.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
            else
            {
                ActivityCompat.requestPermissions(MainUi.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }

        onNewIntent(getIntent());

        navigation = findViewById(R.id.nav_view);
        frameLayout = findViewById(R.id.nav_host_fragment);

//        homeFragment = new HomeFragment();
//        favesFragment = new FavesFragment();
        searchFragment = new SearchFragment();
        downloadedFragment = new DownloadedFragment();

        loadFragment(searchFragment);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
//                    case R.id.navigation_home:
//                        loadFragment(homeFragment);
//                        break;

                    case R.id.navigation_search:
                        loadFragment(searchFragment);
                        break;

//                    case R.id.navigation_favorites:
//                        loadFragment(favesFragment);
//                        break;

                    case R.id.navigation_downloaded:
                        loadFragment(downloadedFragment);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if(intent != null){
            String data = intent.getStringExtra("data");
            Log.i("tag", "onNewIntent: "+data);

        }
    }

    private boolean loadFragment(Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
//            case R.id.navigation_home:
//                fragment = new HomeFragment();
//                break;

            case R.id.navigation_search:
                fragment = new SearchFragment();
                break;

//            case R.id.navigation_favorites:
//                fragment = new FavesFragment();
//                break;

            case R.id.navigation_downloaded:
                fragment = new DownloadedFragment();
                break;
        }

        return loadFragment(fragment);
    }


    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_ui);
//
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//        .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logOut:
               FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainUi.this, signIn.class));
                finish();
                Toast.makeText(MainUi.this, "Logged Out", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    }
}
