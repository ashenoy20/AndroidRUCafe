package com.example.rucafe;


import java.util.ArrayList;

/**
 * A class that holds all the necessary information about the list of orders that are sent to the store.
 *
 * The class keeps track of all the orders the store has. It has the ability to add and delete other
 * orders, return information about it, and has the method to export this list to a text file.
 *
 * @author Abimanyu Ananthu, Ashish Shenoy
 */
public class StoreOrders implements Customizable{

    private ArrayList<Order> list;

    /**
     * Constructor that creates an instance of the StoreOrders class.
     * Keeps track of specific attributes of an StoreOrders list.
     *
     * This is initialized as an empty list that can hold the orders.
     */
    public StoreOrders(){
        this.list = new ArrayList<>();
    }


    /**
     * A method that returns the amount of the total of a given order. This order is found using the index number
     * that is parameterized.
     *
     * @param index - int that represents the index of order within store order list.
     *
     * @return - double total of the order that index corresponds to.
     */

    public double getTotal(int index){
        return this.list.get(index).calculateTotal();
    }



    /**
     * A method that just returns order based on the index sent in.
     *
     * @param index - int that represents the index of order within store order list.
     *
     * @return - A int value that shows how many items are in the order.
     */

    public Order getOrder(int index){
        return this.list.get(index);
    }



    /**
     * A method that adds an order to the store order list.
     * Stores this order at the end of the list.
     *
     *  @param obj - An object that in this case represents a Order
     *               that will be added to the list.
     *  @return - A boolean value that is true if the order
     *  is successfully added.
     */

    @Override
    public boolean add(Object obj) {
        ArrayList<Order> orderItems = this.list;
        Order addedOrder = (Order) obj;
        orderItems.add(addedOrder);
        return true;
    }



    /**
     * A method that removes an order from the store order list.
     * Deletes this order from the list.
     *
     * @param obj - An object that in this case represents a Order
     *            that will be removed from the list.
     *
     *  @return - A boolean value that is true if the order
     *  is successfully removed.
     */

    @Override
    public boolean remove(Object obj) {
        ArrayList<Order> orderItems = this.list;
        Order targetOrder = (Order) obj;
        if(orderItems.contains(targetOrder)){
            orderItems.remove(targetOrder);
            return true;
        }
        return false;
    }



}
