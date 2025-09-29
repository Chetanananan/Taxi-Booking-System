import java.util.ArrayList;

/**
 * Represents a customer with a unique ID, name, and ride booking history.
 * Student id: 33878587
 * Student email: csom0007@student.monash.edu
 * Version number:2.0
 * Name- Chetan Arjun Somuse
 */
public class Customer
{
    private int customerNumber;
    private String name;
    private ArrayList<RideBooking> rideHistory;

    /**
     * Default constructor
     */
    public Customer()
    {
        this.customerNumber = 0;
        this.name = "";
        this.rideHistory = new ArrayList<>();
    }

    /**
     * Non default constructor
     */
    public Customer(String name, int customerNumber)
    {
        this.customerNumber = customerNumber;
        this.name = name;
        this.rideHistory = new ArrayList<>();
    }

    /**
     * Returns the customer's unique ID number.(Implemented in the
     * booking System)
     */
    public int getCustomerNumber()
    {
        return customerNumber;
    }

    /**
     * Returns the customer's name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the customer's ride booking history.
     */
    public ArrayList<RideBooking> getRideHistory()
    {
        return rideHistory;
    }

    /**
     * Add a ride to the customer's history
     */
    public void addRide(RideBooking ride)
    {
        rideHistory.add(ride);
    }

    /**
     * Returns a string representation of the customer's details.
     */
    @Override
    public String toString()
    {
        return "Customer ID: " + customerNumber + ", Name: " + name;
    }
}