package com.example.rucafe;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that holds all the necessary information
 * about the Coffee item the client is ordering
 *
 * The class keeps track of a list of add-ins the client
 * adds or removes from their coffee, the amount of the same
 * coffee they want to buy, and the size by choosing from 4
 * options offered.
 * @author Abimanyu Ananthu, Ashish Shenoy
 */

public class Coffee extends MenuItem implements Customizable, Serializable {

    private ArrayList<String> addIns;
    private String size;
    private int quantity;

    public static final double MINIMUM_COFFEE_PRICE = 1.99;
    public static final double SIZE_UPGRADE_COST = 0.5;
    public static final double ADD_IN_COST = 0.2;

    public static final int TALL = 1;
    public static final int GRANDE = 2;
    public static final int VENTI = 3;



    /**
     * This is the Coffee constructor that takes is
     * responsible for initializing values, such as
     * the add-ins to an empty list, size to "Short"
     * by default, and quantity to 1 by default.
     */
    public Coffee() {
        super(MINIMUM_COFFEE_PRICE);
        this.addIns = new ArrayList<>();
        this.size = "Short";
        this.quantity = 1;
    }




    /**
     * Method Overridden from the MenuItem class that
     * calculates and sets the price for the Coffee object.
     * Calculates the subtotal according to how many add-ins are
     * present in the list, the size of the coffee, and the quantity
     * @return - double value that represents the subtotal cost
     */
    @Override
    public double itemPrice() {
        String coffeeSize = this.getSize();
        int numAddIns = this.getAddIns().size();

        double addInPrice = numAddIns*ADD_IN_COST;
        double sizePrice = 0;
        switch (coffeeSize){
            case "Tall":
                sizePrice = TALL*SIZE_UPGRADE_COST;
                break;
            case "Grande":
                sizePrice = GRANDE*SIZE_UPGRADE_COST;
                break;
            case "Venti":
                sizePrice = VENTI*SIZE_UPGRADE_COST;
                break;
        }

        double price = MINIMUM_COFFEE_PRICE + addInPrice + sizePrice;
        double totalPrice = price * this.getQuantity();
        super.setItemPrice(totalPrice);

        return super.itemPrice();
    }

    /**
     * Getter method that gets the list of add-ins
     * in the coffee
     *
     * @return - A String ArrayList that contains the add-ins of the
     * coffee
     */
    public ArrayList<String> getAddIns(){
        return this.addIns;
    }

    /**
     * Getter method for getting the size of the coffee
     *
     * @return - A String representing the size of the coffee
     */
    public String getSize(){
        return this.size;
    }

    /**
     * Getter method for getting the quantity of the coffee
     *
     * @return - Integer value representing the quantity of
     * coffee
     */
    public int getQuantity(){
        return this.quantity;
    }

    /**
     * Setter method that is responsible for setting the size of
     * the coffee
     *
     * @param size - A String value that will be the new size of the
     *             coffee object.
     */
    public void setSize(String size){
        this.size = size;
    }

    /**
     * Setter method that is responsible for setting the quantity of
     * the coffee
     *
     * @param quantity - A integer value that will be the new quantity of
     *                 the coffee object.
     */

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    /**
     * Implemented method from the Customizable interface that
     * is responsible for making the additions to the add-in
     * list.
     * @param obj - An Object that gets type casted to a String
     *            as add-ins are in the form of String inputs
     * @return - boolean value to show whether or not the addition
     *          was a success, true if the value was added, false
     *          otherwise.
     */
    @Override
    public boolean add(Object obj) {
        ArrayList<String> list = this.getAddIns();
        String addIn = (String) obj;
        if(list.contains(addIn)){
            return false;
        }
        list.add(addIn);
        return true;
    }

    /**
     * Implemented method from the Customizable interface that
     * is responsible for making the deletions to the add-in
     * list.
     * @param obj - An Object that gets type casted to a String
     *            as add-ins are in the form of String inputs
     * @return - boolean value to show whether or not removing
     *          the add-in was a success, true if the value was
     *          added, false otherwise.
     */
    @Override
    public boolean remove(Object obj) {
        ArrayList<String> list = this.getAddIns();
        String addIn = (String) obj;
        if(!list.contains(addIn)){
            return false;
        }
        list.remove(addIn);
        return true;
    }

    /**
     * A method that creates a String that shows all the attributes
     * of the Coffee object.
     *
     * @return - A String that shows all the attributes in a clear
     * readable manner.
     */
    public String toString(){
        ArrayList<String> list = this.getAddIns();
        String size = this.size;
        int quantity = this.quantity;

        return "("+ quantity + ") " + size + " Coffee " + list;
    }

}
