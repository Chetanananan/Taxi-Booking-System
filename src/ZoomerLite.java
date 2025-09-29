/**
 * Represents a ZoomerLite vehicle, a budget option limited to carry 1 passenger and
 * maximum distance of 10 km. It calculates fares based on peak hours.
 * Student id: 33878587
 * Student email: csom0007@student.monash.edu
 * Version number: 2.0
 * Name: Chetan Arjun Somuse.
 */
public class ZoomerLite extends ZoomerVehicle
{
    /**
     * Default constructor
     */
    public ZoomerLite()
    {
        super();
    }

    /**
     * Returns the type of the vehicle.
     */
    @Override
    public VehicleCategory getType()
    {
        return VehicleCategory.ZOOMERLITE;
    }

    /**
     * Non-default constructor
     */
    public ZoomerLite(int regNum, String name)
    {
        super(regNum, name, VehicleCategory.ZOOMERLITE);
    }

    /**
     * method to calculate fare based on the distance and peak hour, passenger
     * must be one in order to book this category.
     */
    @Override
    public double calcFare(double distance, int passengers,
                           boolean isPeakHour, int largeBags)
    {
        if (passengers > 1)
        {
            System.out.println("ZoomerLite is only limited to only 1 passenger");
            return 0;
        }
        double rate;

        if (isPeakHour)
        {
            rate = 4;
        } else
        {
            rate = 2;
        }
        if (distance <= 10)
        {
            return distance * rate;
        } else
        {
            return 0;
        }
    }

    /**
     * String representation of the Zoomerlite vehicle.
     */
    @Override
    public String toString()
    {
        return "ZoomerLite " + getRegistrationNumber() + " - " + getMake();
    }

}
