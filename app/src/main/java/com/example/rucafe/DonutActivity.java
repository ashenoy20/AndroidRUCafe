package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class DonutActivity extends AppCompatActivity {

    private static ArrayList<Donut> list = new ArrayList<>();
    private static final int NO_SELECTION = -1;

    private RadioGroup group;
    private EditText quantity;
    private ListView donutList;

    private ArrayAdapter<Donut> adapter;

    private int selectedPosition = NO_SELECTION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        setTitle("Order Donuts");

        Button addCart = findViewById(R.id.addCart);
        Button removeCart = findViewById(R.id.removeCart);
        Button addOrder = findViewById(R.id.addOrder);
        group = findViewById(R.id.group);
        quantity = findViewById(R.id.quantityText);

        donutList = findViewById(R.id.donutList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        donutList.setAdapter(adapter);
        donutList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View v;
                for(int i = 0; i < donutList.getChildCount(); i++){
                    if(i == position){
                        selectedPosition = i;
                        v = donutList.getChildAt(i);
                        v.setBackgroundResource(R.color.selectedItem);
                    }else{
                        v = donutList.getChildAt(i);
                        v.setBackgroundResource(R.color.unselectedItem);
                    }
                }
            }
        });
        donutList.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart(v);
            }
        });

        removeCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFromCart(v);
            }
        });

        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToOrder(v);
            }
        });

    }


    public void addToCart(View v){
        try{
            String strDonuts = quantity.getText().toString();
            int numDonuts = Integer.parseInt(strDonuts);

            if(numDonuts <= 0) throw new Exception();

            Donut newDonut;
            if(group.getCheckedRadioButtonId() == R.id.yeastButton){
                newDonut = new Donut("Yeast Donut", numDonuts);
            }else if(group.getCheckedRadioButtonId() == R.id.cakeButton){
                newDonut = new Donut("Cake Donut", numDonuts);
            }else if(group.getCheckedRadioButtonId() == R.id.dHolesButton){
                newDonut = new Donut("Donut Holes", numDonuts);
            }else {
                Toast.makeText(this, "Please select a Donut Type", Toast.LENGTH_SHORT).show();
                return;
            }
            newDonut.itemPrice();
            list.add(newDonut);
            adapter.notifyDataSetChanged();
            calculateSubtotal();
        }catch(Exception e){
            Toast.makeText(this, "Please select valid quantity", Toast.LENGTH_SHORT).show();
        }
    }


    public void removeFromCart(View v){
            try {
                if(selectedPosition == NO_SELECTION){
                    Toast.makeText(this, "Please select an item from list", Toast.LENGTH_SHORT).show();
                    return;
                }
                list.remove(selectedPosition);
                adapter.notifyDataSetChanged();
                for(int i = 0; i < donutList.getChildCount(); i++){
                    donutList.getChildAt(i).setBackgroundResource(R.color.unselectedItem);
                }
                calculateSubtotal();
                Toast.makeText(this, "Successfully removed item", Toast.LENGTH_SHORT).show();
                selectedPosition = NO_SELECTION;
            }catch (Exception e){
                Toast.makeText(this, "No items in the list", Toast.LENGTH_SHORT).show();
            }

    }

    public void addToOrder(View v){
        Intent donutInfo = new Intent(this, CurrentOrderActivity.class);
        donutInfo.putExtra("Donut List", list);
        startActivity(donutInfo);

    }


    public void calculateSubtotal(){
        TextView subtotalView = findViewById(R.id.subtotalView);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        double subtotal = 0;
        for(int i = 0; i < list.size(); i++){
            subtotal += list.get(i).itemPrice();
        }
        String textSubtotal = formatter.format(subtotal);
        subtotalView.setText(textSubtotal);
    }






}
