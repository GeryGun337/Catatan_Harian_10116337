package com.example.catatan_harian_10116337.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.catatan_harian_10116337.R;
import com.example.catatan_harian_10116337.fragment.infoFragment;
import com.example.catatan_harian_10116337.fragment.noteFragment;
import com.example.catatan_harian_10116337.fragment.profileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
/**
 * 10116337 Gery Gunawan AKB IF-3
 */

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        //menampilkan halaman yang pertama muncul
        getFragmentPage(new noteFragment());

        //insialisasi bottom nav
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()){
                    case
                            R.id.info:
                        fragment = new infoFragment();
                        break;
                    case
                            R.id.note:
                        fragment = new noteFragment();
                        break;
                    case
                            R.id.profile:
                        fragment = new profileFragment();
                        break;
                }
                return getFragmentPage(fragment);
            }
        });
    }
    private boolean getFragmentPage(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.page_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}



