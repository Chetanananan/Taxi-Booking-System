import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a ride booking in the system, containing details such as the customer
 * , vehicle, booking date, start and destination locations,
 * distance, number of passengers, and any large bags
 * Student id: 33878587
 * Student email: csom0007@student.monash.edu
 * Version number: 5.0
 * Name: Chetan Arjun Somuse.
 */
public class RideBooking
{
    private int rideID;
    private Customer customer;
    private ZoomerVehicle vehicle;
    private LocalDate bookingDate;
    private String startLocation;
    private String destination;
    private int distance;
    private int passengers;
    private int largeBags;
    private LocalTime startTime;

    /**
     * Default constructor
     */
    public RideBooking()
    {
        this.rideID = 0;
        this.customer = null;
        this.vehicle = null;
        this.startLocation = null;
        this.destination = null;
        this.distance = 0;
        this.passengers = 0;
        this.bookingDate = null;
        this.largeBags = 0;
        this.startTime = null;
    }

    /**
     * Non default constructor
     */
    public RideBooking(int rideID, Customer customer, ZoomerVehicle vehicle,
                       LocalDate bookingDate,
                       String startLocation, String destination,
                       int distance, int passengers, int largeBags)
    {
        this.rideID = rideID;
        this.customer = customer;
        this.vehicle = vehicle;
        this.bookingDate = bookingDate;
        this.startLocation = startLocation;
        this.destination = destination;
        this.distance = distance;
        this.passengers = passengers;
        this.largeBags = largeBags;
    }

    /**
     * Returns the ride's unique ID.
     */
    public int getRideID()
    {
        return rideID;
    }

    /**
     * Returns the customer associated with the ride.
     */
    public Customer getCustomer()
    {
        return customer;
    }

    /**
     * Returns the vehicle used for the ride.
     */
    public ZoomerVehicle getVehicle()
    {
        return vehicle;
    }

    /**
     * Returns the booking date of the ride.
     */
    public LocalDate getBookingDate()
    {
        return bookingDate;
    }

    /**
     * Returns the starting location of the ride.
     */
    public String getStartLocation()
    {
        return startLocation;
    }

    /**
     * Returns the destination of the ride.
     */
    public String getDestination()
    {
        return destination;
    }

    /**
     * Returns the distance of the ride in kilometers.
     */
    public int getDistance()
    {
        return distance;
    }

    /**
     * Returns the number of passengers for the ride.
     */
    public int getPassengers()
    {
        return passengers;
    }

    /**
     * Returns the number of large bags on the ride.(Only for zoomerpro)
     */
    public int getLargeBags()
    {
        return largeBags;
    }

    /**
     * Returns the start time of the ride.
     */
    public LocalTime getStartTime()
    {
        return startTime;
    }

    /**
     * Sets the ride's unique ID.
     */
    public void setRideID(int rideID)
    {
        this.rideID = rideID;
    }

    /**
     * Sets the customer for the ride.
     */
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    /**
     * Sets the vehicle for the ride.
     */
    public void setVehicle(ZoomerVehicle vehicle)
    {
        this.vehicle = vehicle;
    }

    /**
     * Sets the booking date of the ride.
     */
    public void setBookingDate(LocalDate bookingDate)
    {
        this.bookingDate = bookingDate;
    }

    /**
     * Sets the starting location of the ride.
     */
    public void setStartLocation(String startLocation)
    {
        this.startLocation = startLocation;
    }

    /**
     * Sets the destination of the ride.
     */
    public void setDestination(String destination)
    {
        this.destination = destination;
    }

    /**
     * Sets the distance of the ride.
     */
    public void setDistance(int distance)
    {
        this.distance = distance;
    }

    /**
     * Sets the passengers of the ride.
     */
    public void setPassengers(int passengers)
    {
        this.passengers = passengers;
    }

    /**
     * Sets the number of large bags of the ride.
     */
    public void setLargeBags(int largeBags)
    {
        this.largeBags = largeBags;
    }

    /**
     * Sets the start time of the ride.
     */
    public void setStartTime(LocalTime startTime)
    {
        this.startTime = startTime;
    }

    /**
     * Returns a string representation of the ride details.
     */
    @Override
    public String toString()
    {
        return "Ride ID: " + rideID +
                ", Customer: " + customer.getName() +
                ", Vehicle: " + vehicle.getMake() +
                ", Date and Time: " + bookingDate +
                ", Start: " + startLocation +
                ", Destination: " + destination +
                ", Distance: " + distance + " km, Passengers: " + passengers +
                (largeBags > 0 ? ", Large Bags: " + largeBags : "");
    }


}