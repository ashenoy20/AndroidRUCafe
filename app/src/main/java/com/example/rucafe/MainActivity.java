package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


/**
 * The Main Activity of the Android app that is the gateway
 * to child activities. Serves as the main screen itself and
 * showcases buttons for navigation to other child activities
 *
 * @author Ashish Shenoy, Abimanyu Ananthu
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the "Order Donut" button is
     * clicked. Serves to start the Donut Activity for the user.
     *
     * @param v - A view object that has fired this particular
     *          onClick method.
     */
    public void donutClick(View v){
        Intent intent = new Intent(this, DonutActivity.class);
        this.startActivity(intent);
    }

    /**
     * This method is called when the "Order Coffee" button is
     * clicked. Serves to start the Coffee Activity for the user.
     *
     * @param v - A view object that has fired this particular
     *          onClick method.
     */
    public void coffeeClick(View v){
        Intent intent = new Intent(this, CoffeeActivity.class);
        this.startActivity(intent);
    }

    /**
     * This method is called when the "Current Order" button is
     * clicked. Serves to start the Current Order for the user.
     *
     * @param v - A view object that has fired this particular
     *          onClick method.
     */
    public void currentOrderClick(View v){
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        this.startActivity(intent);
    }

    /**
     * This method is called when the "View Orders" button is
     * clicked. Serves to start the Orders Activity for the user.
     *
     * @param v - A view object that has fired this particular
     *          onClick method.
     */
    public void viewOrderClick(View v){
        Intent intent = new Intent(this, OrdersActivity.class);
        this.startActivity(intent);
    }



}
