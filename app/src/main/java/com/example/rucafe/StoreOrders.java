package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;

/**
 * A class that holds all the necessary information about the list of orders that are sent to the store.
 *
 * The class keeps track of all the orders the store has. It has the ability to add and delete other
 * orders, return information about it, and has the method to export this list to a text file.
 *
 * @author Abimanyu Ananthu, Ashish Shenoy
 */
public class StoreOrders implements Customizable{

    private ObservableList<Order> list;

    /**
     * Constructor that creates an instance of the StoreOrders class.
     * Keeps track of specific attributes of an StoreOrders list.
     *
     * This is initialized as an empty list that can hold the orders.
     */
    public StoreOrders(){
        this.list = FXCollections.observableArrayList();
    }

    /**
     * Helper method that prints the orders to the listview
     * that is on the GUI.
     *
     * @param list - the listview that will display the order
     * @param index - int that represents the index the specified order within the StoreOrders list
     */
    public void connectOrderToListView(ListView<MenuItem> list, int index){
        this.list.get(index).connectToListView(list);
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
     * A method that just returns the size of the list.
     *
     * @return - A int value that shows how many orders are in the list.
     */
    public int getSize(){
        return this.list.size();
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
        ObservableList<Order> orderItems = this.list;
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
        ObservableList<Order> orderItems = this.list;
        Order targetOrder = (Order) obj;
        if(orderItems.contains(targetOrder)){
            orderItems.remove(targetOrder);
            return true;
        }
        return false;
    }

    /**
     * Takes in a File object as a parameter and writes the list information
     * from the StoreOrders list to the file.
     *
     * @param target - target file that is going to be overwritten
     * @throws IOException - Throws error if something goes wrong when
     *                        writing to file
     */
    public void exportOrders(File target) throws IOException {

            int orderCt = 1;
            StringBuilder orders = new StringBuilder();
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            if (this.getSize() > 0) {
                for (int i = 0; i < this.getSize(); i++){
                    orders.append("Order ")
                            .append(orderCt)
                            .append("\n")
                            .append(this.list.get(i).toString()).append("Total: ")
                            .append(formatter.format(this.list.get(i).calculateTotal()))
                            .append("\n");
                    orderCt++;
                }
                FileWriter writer = new FileWriter(target.getAbsolutePath());
                writer.write(orders.toString());
                writer.close();
            }

    }
}
