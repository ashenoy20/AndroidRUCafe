package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 * A class that keeps track of the items the client ordered
 *
 * Specifically, the class contains a list that holds all the MenuItems the client
 * orders, and gets called to show the order on the listview when the client is on their
 * current order page.
 */
public class Order implements Customizable{

    private ObservableList<MenuItem> list;

    public static final double TAX_RATE = 0.06625;

    /**
     * Constructor that creates an instance of the Order class.
     * Keeps track of specific attributes of an order.
     *
     * This is initialized as an empty list that can hold the menu items.
     */
    public Order(){
        this.list = FXCollections.observableArrayList();
    }

    /**
     * Helper method that helps calculate the subtotal of
     * all the items in the order. This is for
     * updating the price as the client adds/removes more  orders
     * to the list.
     * @return - double that represents the subtotal
     */
    public double calculateSubtotal(){
        ObservableList<MenuItem> list = this.list;

        double subtotal = 0;

        for (MenuItem menuItem : list) {
            subtotal += menuItem.itemPrice();
        }

        return subtotal;
    }

    /**
     * Helper method that helps calculate the subtotal of
     * sales tax that is applicable in the state of New Jersey.
     *
     * @return - double that represents the sales tax of the subtotal
     *           for the current order.
     */
    public double calculateTax(){
        double subtotal = this.calculateSubtotal();
        return subtotal*TAX_RATE;
    }

    /**
     * Helper method that helps calculate the total of
     * current order. Uses the methods above and returns the sum.
     *
     * @return - double that represents the total amount of the current order.
     */
    public double calculateTotal(){
        double subtotal = this.calculateSubtotal();
        double tax = this.calculateTax();

        return tax + subtotal;
    }

    /**
     * A method that just returns the size of the list.
     *
     * @return - A int value that shows how many items are in the order.
     */
    public int getSize(){
        return this.list.size();
    }


    /**
     * A method that connects the list to the listview by passing
     * in a reference to the listview object on the GUI
     * @param list - a listview that will be used to display
     *             the list.
     */
    public void connectToListView(ListView<MenuItem> list){
        list.setItems(this.list);
    }

    /**
     * A method that formats the order into a clear
     * concise String value.
     *
     * @return - A String value that is readable by the client
     */
    @Override
    public String toString(){
        StringBuilder order = new StringBuilder();

        for (MenuItem menuItem : this.list) {
            order.append(menuItem.toString()).append("\n");
        }

        return order.toString();
    }


    /**
     * A method that adds a menu item (donut or coffee) to the contents
     * of the order. Store this new item at the end of the orderItems list.
     * @param obj - An object that in this case represents a Donut or Coffee
     *            that will be added to the list.
     * @return - A boolean value that is true if the item
     *      is successfully added.
     */
    @Override
    public boolean add(Object obj) {
        ObservableList<MenuItem> orderItems = this.list;

        if(obj instanceof Donut){
            Donut donut = (Donut) obj;
            orderItems.add(donut);
            return true;
        }else if(obj instanceof Coffee){
            Coffee coffee = (Coffee) obj;
            orderItems.add(coffee);
            return true;
        }

        return false;

    }

    /**
     * A method that removes a menu item (donut or coffee) from the contents
     * of the order. Deletes this item from the list of items in the order.
     *
     * @param obj - An object that in this case represents a Donut or Coffee
     *             that will be removed from the list.
     *
     *  @return - A boolean value that is true if the item
     *          is successfully removed.
     */
    @Override
    public boolean remove(Object obj) {
        ObservableList<MenuItem> orderItems = this.list;
        if(obj instanceof Donut){
            Donut donut = (Donut) obj;
            orderItems.remove(donut);
            return true;
        }else if(obj instanceof Coffee){
            Coffee coffee = (Coffee) obj;
            orderItems.remove(coffee);
            return true;
        }
        return false;
    }
}
