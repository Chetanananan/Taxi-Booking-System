/**
 * Represents a ZoomerPro vehicle, a luxury option carrying 1-4 passengers
 * with an added surcharge for large bags. It calculates fares based on
 * peak hours.
 * Student id: 33878587
 * Student email: csom0007@student.monash.edu
 * Version number: 2.0
 * Name: Chetan Arjun Somuse.
 */
public class ZoomerPro extends ZoomerVehicle {
    int largeBags;

    /**
     * Default constructor
     */
    public ZoomerPro()
    {
        super();
    }

    /**
     * Non-default constructor
     */
    public ZoomerPro(int regNumber, String make)
    {
        super(regNumber, make, VehicleCategory.ZOOMERPRO);
    }

    /**
     * Calculates the fare for a ZoomerPro ride, including a surcharge
     * for large bags.
     */
    @Override
    public double calcFare(double distance, int passengers,
                           boolean isPeakHour, int largeBags)
    {
        if (passengers < 1 || passengers > 4)
        {
            System.out.println("ZoomerPro supports 1 to 4 passengers only.");
            return 0;
        }
        double rate;
        if (isPeakHour)
        {
            rate = 5;
        } else
        {
            rate = 3;
        }
        double fare = rate * distance * passengers;
        // Add $5 surcharge per large bag
        fare += largeBags * 5.0;

        return fare;
    }

    /**
     * Returns the number of large bags for the ride.
     */
    public int getLargeBags()
    {
        return largeBags;
    }

    /**
     * Sets the number of large bags for the ride
     */
    public int setLargeBags(int largeBag)
    {
        return largeBag;
    }

    /**
     * Returns the vehicle type as ZOOMERPRO
     */
    @Override
    public VehicleCategory getType()
    {
        return VehicleCategory.ZOOMERPRO;
    }

    /**
     * String representation of the ZoomerPro Vehicle
     */
    @Override
    public String toString()
    {
        return "ZoomerPro " + getRegistrationNumber() + " - " + getMake();
    }
}
