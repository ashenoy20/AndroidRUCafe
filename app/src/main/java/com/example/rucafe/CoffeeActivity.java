package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class CoffeeActivity extends AppCompatActivity {

    private static Coffee coffee = new Coffee();
    private RadioGroup sizeGroup;
    private CheckBox[] addOns;
    private Spinner spinner;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        setTitle("Order Coffee");

        Button addOrder = findViewById(R.id.addToOrder);
        sizeGroup = findViewById(R.id.sizeGroup);
        sizeGroup.check(R.id.shortRadio);
        addOns = new CheckBox[]{
                findViewById(R.id.caramelBox),
                findViewById(R.id.creamBox),
                findViewById(R.id.milkBox),
                findViewById(R.id.syrupBox),
                findViewById(R.id.whipCreamBox)};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, R.array.coffeeQuant);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                coffee.setQuantity(Integer.parseInt((String) spinner.getSelectedItem()));
                calculateSubtotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calculateSubtotal();
    }

    public void addCoffeeToOrder(View v) {
        try{
            Intent donutInfo = new Intent(this, CurrentOrderActivity.class);
            donutInfo.putExtra("Coffee Order", coffee);
            coffee = new Coffee();
            startActivity(donutInfo);
        }
        catch (Exception e){
            Toast.makeText(this, "This will never happen.", Toast.LENGTH_SHORT).show();
        }

        resetButtons();
    }

    public void resetButtons() {
        sizeGroup.clearCheck();
        for (CheckBox addOn : addOns) {
            addOn.setChecked(false);
        }

    }

    public void calculateSubtotal() {
        TextView subtotalView = findViewById(R.id.subtotalValue);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        double subtotal;

        subtotal = coffee.itemPrice();


        String textSubtotal = formatter.format(subtotal);
        subtotalView.setText(textSubtotal);
    }

    public void toggleOptions(View v){
        for (CheckBox addOn : addOns) {
            if (addOn.isChecked())
                coffee.add(addOn.getText());
            else
                coffee.remove(addOn.getText());
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

        calculateSubtotal();
    }


}
