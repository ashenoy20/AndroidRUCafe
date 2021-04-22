package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * An Activity responsible for displaying all the orders
 * placed by the user. Using a drop down, the can toggle
 * between orders and the changes are dynamically shown
 * in the list view and the total.
 *
 * @author Ashish Shenoy, Abimanyu Ananthu
 */
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
            /**
             * Fires when a adapter item is selected
             * @param parent - The AdapterView that contains the view clicked
             * @param view - the view that was clicked
             * @param position - the position the view is in the adapter view
             * @param id - id representing the view
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shownList = storedOrders.getOrder(position).copyList();
                listViewAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, shownList);
                orderList.setAdapter(listViewAdapter);
                calculateAmounts(position);
            }

            /**
             *  Skeleton method that wont be used
             * @param parent - The AdapterView that contains the view clicked
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /**
     * A method that removes the order the user is currently
     * displaying on the list view. This method will fire
     * when the user clicks the "Remove Order" button.
     * Displays a Toast when there is no order in the drop
     * down.
     * @param v - A view object that has fired this particular
     *        onClick method.
     */
    public void removeOrder(View v){
        if(trackOrders == 0){
            Toast.makeText(this, "Please place an order before removing", Toast.LENGTH_SHORT).show();
            return;
        }
        TextView totalView = findViewById(R.id.storeOrdersTotal);
        String selectedItem = (String) orderDropDown.getSelectedItem();
        int position = Integer.parseInt(selectedItem) - 1;
        storedOrders.remove(storedOrders.getOrder(position));
        trackOrders--;
        orderNumbers.remove(orderNumbers.size() - 1);
        spinnerArrayAdapter.notifyDataSetChanged();

        if(trackOrders != 0){
            if(position == 0){
                shownList = storedOrders.getOrder(position).copyList();
                listViewAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, shownList);
                orderList.setAdapter(listViewAdapter);
                calculateAmounts(position);
            }else{
                orderDropDown.setSelection(position - 1 , true);
            }

        }else{
            totalView.setText(R.string.zero_subtotal);
            shownList = new ArrayList<>();
            listViewAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, shownList);
            orderList.setAdapter(listViewAdapter);
        }
    }

    /**
     * A method responsible for calculating and setting
     * the text views for the total.
     */
    public void calculateAmounts(int position){

        TextView totalView = findViewById(R.id.storeOrdersTotal);
        String selectedItem = (String) orderDropDown.getItemAtPosition(position);
        int arrayPosition = Integer.parseInt(selectedItem) - 1;
        Order currentOrder = storedOrders.getOrder(arrayPosition);

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        double total = currentOrder.calculateTotal();

        totalView.setText(formatter.format(total));
    }
}
