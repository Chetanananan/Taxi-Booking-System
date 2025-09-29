/**
 * Represents a ZoomerStandard vehicle, a standard option carrying 1-4 passengers
 * with a maximum distance of 20 km. It calculates fares based on peak hours.
 * Student id: 33878587
 * Student email: csom0007@student.monash.edu
 * Version number: 2.0
 * Name: Chetan Arjun Somuse.
 */

public class ZoomerStandard extends ZoomerVehicle{

    /**
     * Default constructor
     */
    public ZoomerStandard()
    {
        super();
    }

    /**
     * Non-default constructor
     */
    public ZoomerStandard(int regNumber, String make)
    {
        super(regNumber, make,VehicleCategory.ZOOMERSTANDARD);
    }

    /**
     * Calculates the fare for a ZoomerStandard ride based on distance, passengers.
     */
    @Override
    public double calcFare(double distance, int passengers,
                           boolean isPeakHour, int largeBags)
    {
        if (passengers < 1 || passengers > 4) {
            System.out.println("ZoomerStandard is only limited to only 1 to " +
                    "4 passengers");
            return 0;
        }
        double rate;

        if (isPeakHour)
        {
            rate = 4;
        } else {
            rate = 2;
        }
        if (distance <= 20)
        {
            return distance * rate * passengers;
        } else {
            return 0;
        }
    }

    /**
     * Returns the vehicle type as ZOOMERSTANDARD.
     */
    @Override
    public VehicleCategory getType()
    {
        return VehicleCategory.ZOOMERSTANDARD;
    }

    /**
     * String representation of the ZoomerStandard Vehicle
     */
    @Override
    public String toString()
    {
        return "ZoomerStandard " + getRegistrationNumber()
                + " - " + getMake();
    }
}
