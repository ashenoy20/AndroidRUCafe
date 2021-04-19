package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CurrentOrderActivity extends AppCompatActivity {

    private static Order currOrder = new Order();


    private ListView itemList;
    private ArrayAdapter<MenuItem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        setTitle("Current Order");

        itemList = findViewById(R.id.itemList);
        adapter = currOrder.linkAdapterToOrder(this, android.R.layout.simple_list_item_1);

        addIncomingDonuts();

    }


    public void addIncomingDonuts(){
        Intent intent = getIntent();
        Bundle donuts = intent.getBundleExtra("Bundle");
        ArrayList<Donut> list = (ArrayList<Donut>) donuts.getSerializable("Donut List");
        if(list != null){
            for(int i = 0; i < list.size(); i++){
                currOrder.add(list.get(i));
            }
            adapter.notifyDataSetChanged();
        }else{
            return;
        }
    }


}
