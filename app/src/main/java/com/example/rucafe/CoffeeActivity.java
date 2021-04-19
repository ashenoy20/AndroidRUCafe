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

    private static Coffee coffee;
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
        addOns = new CheckBox[]{findViewById(R.id.checkBox),
                findViewById(R.id.checkBox2),
                findViewById(R.id.checkBox3),
                findViewById(R.id.checkBox4),
                findViewById(R.id.checkBox5)};
        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToOrder(v);
            }
        });

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
        TextView subtotalView = findViewById(R.id.textView2);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        double subtotal = coffee.itemPrice();

        String textSubtotal = formatter.format(subtotal);
        subtotalView.setText(textSubtotal);
    }


}
