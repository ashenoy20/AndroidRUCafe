package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class CoffeeActivity extends AppCompatActivity {

    private static Coffee coffee = new Coffee();
    private RadioGroup sizeGroup;
    private Button addOrder;
    private CheckBox[] addOns;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        setTitle("Order Coffee");

        addOrder = findViewById(R.id.addToOrder);
        sizeGroup = findViewById(R.id.sizeGroup);
        sizeGroup.check(R.id.shortRadio);
        addOns = new CheckBox[]{
                findViewById(R.id.caramelBox),
                findViewById(R.id.creamBox),
                findViewById(R.id.milkBox),
                findViewById(R.id.syrupBox),
                findViewById(R.id.whipCreamBox)};

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, R.array.coffeeQuant);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = findViewById(R.id.spinner);
        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToOrder(v);
            }
        });
        calculateSubtotal();
    }

    public void addToOrder(View v) {
        try{
            coffee.setSize(sizeGroup.getChildAt(sizeGroup.getCheckedRadioButtonId()).toString());
        }
        catch (Exception e){
            Toast.makeText(this, "This will never happen.", Toast.LENGTH_SHORT).show();
        }

        resetButtons();
    }

    public void resetButtons() {
        sizeGroup.clearCheck();
        for (int i = 0; i < addOns.length; i++) {
            addOns[i].setChecked(false);
        }

    }

    public void calculateSubtotal() {
        TextView subtotalView = findViewById(R.id.subtotalValue);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        double subtotal;

        subtotal = coffee.itemPrice();


        String textSubtotal = formatter.format(subtotal);
        subtotalView.setText("Subtotal: "+textSubtotal);
    }

    public void toggleAddIns(View v){
        for (int i = 0; i < addOns.length; i++) {
            if (addOns[i].isChecked())
                coffee.add(addOns[i].getText());
            else
                coffee.remove(addOns[i].getText());
        }

        if(sizeGroup.getCheckedRadioButtonId() == R.id.shortRadio){
            coffee.setSize("Short");
        }else if(sizeGroup.getCheckedRadioButtonId() == R.id.tallRadio){
            coffee.setSize("Tall");
        }else if(sizeGroup.getCheckedRadioButtonId() == R.id.grandeRadio){
            coffee.setSize("Grande");
        }else if(sizeGroup.getCheckedRadioButtonId() == R.id.ventiRadio) {
            coffee.setSize("Venti");
        }

        coffee.setQuantity(Integer.parseInt((String) spinner.getSelectedItem()));

        calculateSubtotal();
    }


}
