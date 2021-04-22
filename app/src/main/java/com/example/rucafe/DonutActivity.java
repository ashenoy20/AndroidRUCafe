package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * This class drives the Donut Activity. This Activity is responsible for
 * keeping track of a list of donuts that the user adds/removes from their
 * cart. In addition, the activity keeps track off the subtotal dynamically
 * as the user add/removes items from the list. Lastly, the user is able to
 * add the list and send the data to their current orders page.
 *
 * @author Ashish Shenoy, Abimanyu Anathu
 */
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

        group = findViewById(R.id.group);
        quantity = findViewById(R.id.quantityText);

        donutList = findViewById(R.id.donutList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        donutList.setAdapter(adapter);
        donutList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Fires when a list item is clicked
             * @param parent - The AdapterView that contains the view clicked
             * @param view - the view that was clicked
             * @param position - the position the view is in the adapterview
             * @param id - id representing the view
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for(int i = donutList.getFirstVisiblePosition(); i <= donutList.getLastVisiblePosition(); i++){
                    if(i == position){
                        selectedPosition = position;
                        Toast.makeText(getApplicationContext(), "Selected item at position: " + position, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        donutList.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);


    }

    /**
     * This method is responsible for adding the particular donut
     * to the cart, which is represented as a list view. This method
     * fires when the user clicks the "Add to Cart" button. Responds
     * with a success Toast if added successfully, otherwise a Toast
     * will indicate that the addition has failed.
     *
     * @param v - A view object that has fired this particular
     *        onClick method.
     */
    public void addToCart(View v){
        try{
            String strDonuts = quantity.getText().toString();
            int numDonuts = Integer.parseInt(strDonuts);

            if(numDonuts <= 0) throw new Exception();

            Donut newDonut;
            if(group.getCheckedRadioButtonId() == R.id.glazedButton){
                newDonut = new Donut("Glazed Donut", numDonuts);
            }else if(group.getCheckedRadioButtonId() == R.id.chocolateButton){
                newDonut = new Donut("Chocolate Donut", numDonuts);
            }else if(group.getCheckedRadioButtonId() == R.id.glazedMunchButton){
                newDonut = new Donut("Glazed Munchkins", numDonuts);
            }else if(group.getCheckedRadioButtonId() == R.id.cinnamonButton) {
                newDonut = new Donut("Cinnamon Donut", numDonuts);
            }else{
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

    /**
     * This method is responsible for removing the particular donut
     * to the cart. This method fires when the user clicks the "Remove Item"
     * button. Method will successfully remove an item from the list
     * if the user has pressed on a list item beforehand, otherwise a
     * Toast will display the issue.
     *
     * @param v - A view object that has fired this particular
     *        onClick method.
     */
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
    /**
     * This method is responsible for adding the donut list to
     * the current order list in the Current Order Activity. If
     * there are no items in the list, the user will be prompted
     * to add an item. This method fires when the "Add to Order"
     * button is clicked. After sending the data, the Current
     * Order Activity will be opened to see if the data has passed
     * successfully.
     *
     * @param v - A view object that has fired this particular
     *        onClick method.
     */
    public void addToOrder(View v){
        if(list.size() != 0){
            Intent donutInfo = new Intent(this, CurrentOrderActivity.class);
            donutInfo.putExtra("Donut List", list);
            list = new ArrayList<>();
            startActivity(donutInfo);
        }else{
            Toast.makeText(this, "No items in the list", Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * Responsible for calculating the subtotal based on
     * the items in the list. Also responsible for updating
     * the text view responsible for displaying the subtotal
     */
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
