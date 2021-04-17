package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button donutButton = findViewById(R.id.donutButton);
        Button coffeeButton = findViewById(R.id.coffeeButton);
        Button currButton = findViewById(R.id.currButton);
        Button viewButton = findViewById(R.id.viewButton);

        donutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donutClick(v);
            }
        });

        coffeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donutClick(v);
            }
        });

        currButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donutClick(v);
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donutClick(v);
            }
        });
    }

    public void donutClick(View v){

    }

    public void coffeeClick(View v){

    }

    public void currentOrderClick(View v){

    }

    public void viewOrderClick(View v){

    }



}
