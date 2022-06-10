package com.example.mainpage.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mainpage.R;
import com.example.mainpage.fragment.ChatFragment;
import com.example.mainpage.fragment.HomeFragment;
import com.example.mainpage.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private HomeFragment fragmentHome = new HomeFragment();
    private ProfileFragment fragmentProfile = new ProfileFragment();
    private ChatFragment fragmentChat = new ChatFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.tab_home:
                    transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();

                    break;
                case R.id.tab_chat:
                    transaction.replace(R.id.frameLayout, fragmentChat).commitAllowingStateLoss();
                    break;
                case R.id.tab_profile:
                    transaction.replace(R.id.frameLayout, fragmentProfile).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }
}
