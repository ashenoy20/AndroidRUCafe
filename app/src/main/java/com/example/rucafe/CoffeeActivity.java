package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class CoffeeActivity extends AppCompatActivity {

    private static Coffee coffee = new Coffee();
    private RadioGroup sizeGroup;
    private Button addOrder;
    private CheckBox[] addOns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        setTitle("Order Coffee");

        addOrder = findViewById(R.id.addToOrder);
        sizeGroup = findViewById(R.id.coffeeSize);
        sizeGroup.check(R.id.small);
        addOns = new CheckBox[]{
                findViewById(R.id.caramelBox),
                findViewById(R.id.creamBox),
                findViewById(R.id.milkBox),
                findViewById(R.id.syrupBox),
                findViewById(R.id.whipCreamBox)};
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

        double subtotal = coffee.itemPrice();

        String textSubtotal = formatter.format(subtotal);
        subtotalView.setText(textSubtotal);
    }

    public void toggleAddIns(View v){


    }


}
