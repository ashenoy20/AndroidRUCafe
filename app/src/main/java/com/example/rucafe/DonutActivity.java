package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DonutActivity extends AppCompatActivity {

    private static ArrayList<Donut> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        setTitle("Order Donuts");

        ListView donutList = findViewById(R.id.donutList);
        //ArrayAdapter<Donut> adapter = new ArrayAdapter<>(this, )
    }



}
