package com.example.tugas1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private boolean isSurplusDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.button);

        displayProductInfo();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isSurplusDisplayed) {
                    displaySurplus();
                } else {
                    displayProductInfo();
                }
            }
        });
    }

    public void displaySurplus() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager
                .findFragmentById(R.id.fragment_container);
        if (simpleFragment != null) {
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }

        NewFragment newFragment = NewFragment.newInstance();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,
                newFragment).addToBackStack(null).commit();

        mButton.setText("Mantap!");
        isSurplusDisplayed = true;
    }

    public void displayProductInfo() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        NewFragment newFragment = (NewFragment) fragmentManager
                .findFragmentById(R.id.fragment_container);
        if (newFragment != null) {
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.remove(newFragment).commit();
        }

        SimpleFragment simpleFragment = SimpleFragment.newInstance();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,
                simpleFragment).addToBackStack(null).commit();

        mButton.setText("Lihat Kelebihan");
        isSurplusDisplayed = false;
    }

}