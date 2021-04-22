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

/**
 * An Activity class that keeps track of the user's current
 * order. The user has the ability to remove unwanted items in
 * their current order. The subtotal, the tax, and the total
 * is displayed dynamically when the user makes a deletion.
 * Once the user is satisfied with their items in the current
 * order, they can place their order that will be sent to
 * the Orders Activity
 *
 * @author Ashish Shenoy, Abimanyu Ananthu
 */
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
            /**
             * Fires when a list item is clicked
             * @param parent - The AdapterView that contains the view clicked
             * @param view - the view that was clicked
             * @param position - the position the view is in the adapter view
             * @param id - id representing the view
             */
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

    /**
     * This method will remove the selected item form the
     * list view. This method fires when the user clicks the
     * "Remove Item" button. This method will display a Toast
     * if the user hasn't selected an item before clicking
     * the button
     *
     * @param v - A view object that has fired this particular
     *        onClick method.
     */
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

    /**
     * A method that will place the order, which means it
     * will send the current Order object to the
     * Orders Activity. This method will display
     * a toast if the order list is empty.
     *
     * @param v - A view object that has fired this particular
     *        onClick method.
     */
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

    /**
     * A method responsible for calculating and setting
     * the text views for subtotal, tax, and the total.
     */
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



}
