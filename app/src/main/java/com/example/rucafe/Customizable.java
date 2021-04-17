package com.example.rucafe;

/**
 * An interface that requires the class that implemented
 * this interface to override methods pertaining to the
 * addition and deletion of certain attributes based off
 * the parameter inputs
 *
 * @author Abimanyu Ananthu, Ashish Shenoy
 */
public interface Customizable {
    /**
     * This method is responsible to add the contents of
     * the parameter to an attribute of the class that is
     * implementing this method
     *
     * @param obj - An Object that can be type casted to a different
     *            type based off what the class that implements this method
     *            requires
     * @return - a boolean value to determine whether adding the param was a
     *          success or failure. True when it was successfully added, false otherwise
     */
    boolean add(Object obj);

    /**
     * This method is responsible to remove the contents
     * the parameter value from an attribute of the class that is
     * implementing this method
     *
     * @param obj - An Object that can be type casted to a different
     *            type based off what the class that implements this method
     *            requires
     * @return - a boolean value to determine whether deletion was a
     *          success or failure. True when it was successfully removed, false otherwise
     */
    boolean remove(Object obj);
}
