package com.example.rucafe;


import java.io.Serializable;

/**
 * A class that acts as a parent class to any menu item
 * in the cafe. Contains the absolute basic fields that any
 * menu item has.
 */
public class MenuItem implements Serializable {
    private double price;

    /**
     * Constructor that creates an instance of the MenuItem
     * class.
     * @param price - A double value that represents the price
     *              of the menu item
     */
    public MenuItem(double price){
        this.price = price;
    }

    /**
     * A method that simply returns the item price
     *
     * @return - A double value that represents the price of
     *          the menu item
     */
    public double itemPrice(){
        return this.price;
    }

    /**
     * A setter method that sets the price of the menu item
     *
     * @param price - A double value that will be the new price of the
     *              menu item
     */
    public void setItemPrice(double price){
        this.price = price;
    }


}
