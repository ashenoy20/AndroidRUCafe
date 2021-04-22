package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {

    private static StoreOrders storedOrders = new StoreOrders();
    private static Integer trackOrders = 0;

    private ListView orderList;
    private ArrayAdapter<MenuItem> listViewAdapter;
    private ArrayList<MenuItem> shownList = new ArrayList<>();

    private Spinner orderDropDown;
    private ArrayAdapter<String> spinnerArrayAdapter;
    private static ArrayList<String> orderNumbers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        setTitle("View Orders");
        orderList = findViewById(R.id.orderView);
        orderDropDown = findViewById(R.id.orderNumber);

        if(getIntent().getExtras() != null){
            Order orderInfo = (Order) getIntent().getSerializableExtra("New Order");
            storedOrders.add(orderInfo);
            trackOrders++;
            orderNumbers.add(trackOrders.toString());
            orderDropDown.setSelection(trackOrders - 1);
        }


        listViewAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shownList);
        orderList.setAdapter(listViewAdapter);

        spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, orderNumbers);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        orderDropDown.setAdapter(spinnerArrayAdapter);

        orderDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shownList = storedOrders.getOrder(position).copyList();
                listViewAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, shownList);
                orderList.setAdapter(listViewAdapter);
                calculateAmounts();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void removeOrder(View v){
        TextView totalView = findViewById(R.id.storeOrdersTotal);
        String selectedItem = (String) orderDropDown.getSelectedItem();
        int position = Integer.parseInt(selectedItem) - 1;
        storedOrders.remove(storedOrders.getOrder(position));
        trackOrders--;
        orderNumbers.remove(orderNumbers.size() - 1);
        spinnerArrayAdapter.notifyDataSetChanged();

        if(trackOrders != 0){
            orderDropDown.setSelection(position);
        }else{
            totalView.setText(R.string.zero_subtotal);
            shownList = new ArrayList<>();
            listViewAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, shownList);
            orderList.setAdapter(listViewAdapter);
        }
    }

    public void calculateAmounts(){

        TextView totalView = findViewById(R.id.storeOrdersTotal);
        String selectedItem = (String) orderDropDown.getSelectedItem();
        int position = Integer.parseInt(selectedItem) - 1;
        Order currentOrder = storedOrders.getOrder(position);

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        double total = currentOrder.calculateTotal();

        totalView.setText(formatter.format(total));
    }
}
