package com.example.rucafe;


import android.os.Parcel;
import android.os.Parcelable;

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
public class Donut extends MenuItem implements Parcelable {
    private int quantity;
    private String type;

    public static final double YEAST_DONUT_PRICE = 1.39;
    public static final double CAKE_DONUT_PRICE = 1.59;
    public static final double DONUT_HOLE_PRICE = 0.33;

    /**
     * Constructor that creates an instance of the Donut class.
     * Keeps track of specific attributes of the donuts.
     *
     * @param type - String that represents the type of donut
     * @param quantity - Integer that represents the amount of donuts
     */
    public Donut(String type, int quantity) {
        super(0);
        this.quantity = quantity;
        this.type = type;
    }

    private Donut(Parcel in) {
        super(0);
        quantity = in.readInt();
        type = in.readString();
    }

    public static final Creator<Donut> CREATOR = new Creator<Donut>() {
        @Override
        public Donut createFromParcel(Parcel in) {
            return new Donut(in);
        }

        @Override
        public Donut[] newArray(int size) {
            return new Donut[size];
        }
    };

    /**
     * Method overridden from the MenuItem class. Depending
     * on the quantity and the type of donut, the subtotal is
     * calculated and set accordingly.
     *
     * @return - double value that returns the subtotal of the donuts
     *          ordered
     */
    @Override
    public double itemPrice() {
        String type = this.getType();
        int quantity = this.getQuantity();

        double total = 0;
        switch (type){
            case "Yeast Donut":
                total = YEAST_DONUT_PRICE*quantity;
                break;
            case "Cake Donut":
                total = CAKE_DONUT_PRICE*quantity;
                break;
            case "Donut Holes":
                total = DONUT_HOLE_PRICE*quantity;
                break;
        }
        super.setItemPrice(total);
        return super.itemPrice();
    }

    /**
     * Getter method that gets the type of donut
     *
     * @return - String that represents the type
     *          of donut
     */
    public String getType(){
        return this.type;
    }

    /**
     * Getter method that gets the quantity of donuts
     *
     * @return - Integer value that returns the quantity
     */
    public int getQuantity(){
        return this.quantity;
    }


    /**
     * A method that formats the donut into a clear
     * concise String value.
     *
     * @return - A String value that is readable by the client
     */
    public String toString(){
        return "(" + getQuantity() + ") "+ getType();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.getQuantity());
        dest.writeString(this.getType());
    }
}
