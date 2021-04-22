package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * An Activity that keeps track of the user's coffee order.
 * Gives the user the ability to customize their order by
 * choosing from multiple sizes and selecting multiple
 * add ins. The user also has the ability to choose the quantity
 * between 1 and 10.
 *
 * @author Ashish Shenoy, Abimanyu Ananthu
 */
public class CoffeeActivity extends AppCompatActivity {

    private static Coffee coffee = new Coffee();
    private RadioGroup sizeGroup;
    private CheckBox[] addOns;
    private Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        setTitle("Order Coffee");

        sizeGroup = findViewById(R.id.sizeGroup);
        sizeGroup.check(R.id.shortRadio);
        addOns = new CheckBox[]{
                findViewById(R.id.caramelBox),
                findViewById(R.id.creamBox),
                findViewById(R.id.milkBox),
                findViewById(R.id.syrupBox),
                findViewById(R.id.whipCreamBox)};

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Fires when a spinner item is selected
             * @param parent - The AdapterView that contains the view clicked
             * @param view - the view that was clicked
             * @param position - the position the view is in the adapter view
             * @param id - id representing the view
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                coffee.setQuantity(Integer.parseInt((String) spinner.getSelectedItem()));
                calculateSubtotal();
            }

            /**
             *  Skeleton method that wont be used
             * @param parent - The AdapterView that contains the view clicked
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calculateSubtotal();
    }

    /**
     * A method that adds the coffee to the Current Order
     * Activity. This method then clears all the fields
     * once successfully sent.
     *
     * @param v - A view object that has fired this particular
     *        onClick method.
     */
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

    /**
     * Helper method that deselects all the buttons
     */
    private void resetButtons() {
        sizeGroup.clearCheck();
        for (CheckBox addOn : addOns) {
            addOn.setChecked(false);
        }

    }

    /**
     * Helper method that calculates and sets the subtotal
     * when the user customizes their coffee
     */
    public void calculateSubtotal() {
        TextView subtotalView = findViewById(R.id.subtotalValue);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        double subtotal;

        subtotal = coffee.itemPrice();


        String textSubtotal = formatter.format(subtotal);
        subtotalView.setText(textSubtotal);
    }

    /**
     * Method that helps dynamically update the subtotal by
     * listening for specific button/checkbox clicks. Any
     * button that influences price will fire this method when
     * that specific button is pressed
     *
     * @param v - A view object that has fired this particular
     *        onClick method.
     */
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
