package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CurrentOrderActivity extends AppCompatActivity {

    private static Order currOrder = new Order();

    private ArrayList<MenuItem> visibleOrderList = currOrder.copyList();

    private ListView itemList;
    private ArrayAdapter<MenuItem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        setTitle("Current Order");

        if(getIntent() != null){
            Intent intent = getIntent();
            ArrayList<Donut> list = intent.getParcelableArrayListExtra("Donut List");
            if(list != null){
                for(int i = 0; i < list.size(); i++){
                    currOrder.add(list.get(i));
                }
                visibleOrderList = currOrder.copyList();
            }
        }

        itemList = findViewById(R.id.itemList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, visibleOrderList);
        itemList.setAdapter(adapter);


    }



}
