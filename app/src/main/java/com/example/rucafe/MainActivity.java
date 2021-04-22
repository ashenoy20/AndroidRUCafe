package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void donutClick(View v){
        Intent intent = new Intent(this, DonutActivity.class);
        this.startActivity(intent);
    }

    public void coffeeClick(View v){
        Intent intent = new Intent(this, CoffeeActivity.class);
        this.startActivity(intent);
    }

    public void currentOrderClick(View v){
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        this.startActivity(intent);
    }

    public void viewOrderClick(View v){
        Intent intent = new Intent(this, OrdersActivity.class);
        this.startActivity(intent);
    }



}
