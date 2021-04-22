package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class CurrentOrderActivity extends AppCompatActivity {

    private static final int NO_SELECTION = -1;

    private static Order currOrder = new Order();

    private ArrayList<MenuItem> visibleOrderList = currOrder.copyList();

    private ListView itemList;
    private ArrayAdapter<MenuItem> adapter;
    private int selectedPosition = NO_SELECTION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        setTitle("Current Order");

        if(getIntent().getExtras() != null){
            Intent intent = getIntent();

            Coffee orderedCoffee = (Coffee) intent.getSerializableExtra("Coffee Order");
            ArrayList<Donut> list = (ArrayList<Donut>) intent.getSerializableExtra("Donut List");

            if(list != null){
                for(int i = 0; i < list.size(); i++){
                    currOrder.add(list.get(i));
                }
                visibleOrderList = currOrder.copyList();
            }

            if(orderedCoffee != null){
                currOrder.add(orderedCoffee);
                visibleOrderList = currOrder.copyList();
            }
        }
        calculateAmounts();

        itemList = findViewById(R.id.itemList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, visibleOrderList);
        itemList.setAdapter(adapter);
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(int i = itemList.getFirstVisiblePosition(); i <= itemList.getLastVisiblePosition(); i++){
                    if(i == position){
                        selectedPosition = position;
                        Toast.makeText(getApplicationContext(), "Selected item at position: " + position, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        itemList.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

    }


    public void removeFromOrder(View v){
        try {
            if(selectedPosition == NO_SELECTION){
                Toast.makeText(this, "Please select an item from list", Toast.LENGTH_SHORT).show();
                return;
            }
            MenuItem removedItem = visibleOrderList.remove(selectedPosition);
            currOrder.remove(removedItem);
            adapter.notifyDataSetChanged();
            calculateAmounts();
            Toast.makeText(this, "Successfully removed item", Toast.LENGTH_SHORT).show();
            selectedPosition = NO_SELECTION;
        }catch (Exception e){
            Toast.makeText(this, "No items in the list", Toast.LENGTH_SHORT).show();
        }
    }

    public void calculateAmounts(){
        TextView subtotalView = findViewById(R.id.orderSubTotal);
        TextView taxView = findViewById(R.id.taxValue);
        TextView totalView = findViewById(R.id.totalValue);

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        double subtotalData = currOrder.calculateSubtotal();
        double taxData = currOrder.calculateTax();
        double totalData = currOrder.calculateTotal();

        subtotalView.setText(formatter.format(subtotalData));
        taxView.setText(formatter.format(taxData));
        totalView.setText(formatter.format(totalData));
    }


    public void placeOrder(View v){
        if(visibleOrderList.size() != 0){
            Intent intent = new Intent(this, OrdersActivity.class);
            intent.putExtra("New Order", currOrder);
            startActivity(intent);
            currOrder = new Order();
        }else{
            Toast.makeText(this, "Add items to your order before placing one!", Toast.LENGTH_SHORT).show();
        }

    }



}
