package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class DonutActivity extends AppCompatActivity {

    private static ArrayList<Donut> list = new ArrayList<>();

    private Button addCart;
    private Button removeCart;
    private Button addOrder;
    private RadioGroup group;
    private EditText quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        setTitle("Order Donuts");

        addCart = findViewById(R.id.addCart);
        removeCart = findViewById(R.id.removeCart);
        addOrder = findViewById(R.id.addOrder);
        group = findViewById(R.id.group);
        quantity = findViewById(R.id.quantityText);

        ListView donutList = findViewById(R.id.donutList);
        ArrayAdapter<Donut> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        donutList.setAdapter(adapter);

        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart(v);
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
            finish();
            startActivity(getIntent());
        }catch(Exception e){
            Toast.makeText(this, "Please select valid quantity", Toast.LENGTH_SHORT).show();
        }
    }






}
