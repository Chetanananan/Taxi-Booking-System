/**
 * Abstract base class representing a Zoomer vehicle with details such as
 * registration number, make, category, and total
 * kilometers driven. Also supports monthly
 * summaries for rides, distances, and revenue, and has
 * an abstract method for calculating fare based on vehicle-specific rules.
 * Student id: 33878587
 * Student email: csom0007@student.monash.edu
 * Version number: 7.0
 * Name: Chetan Arjun Somuse.
 */
public abstract class ZoomerVehicle implements FareCalculate{

    protected int registrationNumber;
    protected String make;
    protected VehicleCategory category;
    protected int totalKilometers;

    //For monthly summary
    private int totalRides = 0;
    private int totalKm = 0;
    private double totalMoneyMade = 0.0;

    /**
     * Default constructor
     */
    public ZoomerVehicle()
    {
        this.registrationNumber = 0;
        this.make = "";
        this.category = null;
        this.totalKilometers = 0;
    }

    /**
     * Non-default constructor
     */
    public ZoomerVehicle(int registrationNumber, String make,
                         VehicleCategory category)
    {
        this.registrationNumber = registrationNumber;
        this.make = make;
        this.category = category;
        this.totalKilometers = 0;
    }

    /**
     * Adds kilometers to the vehicle's total kilometers driven.
     */
    public void addKms(int kmDriven)
    {
        this.totalKilometers += kmDriven;
    }

    /**
     * Returns the make of the vehicle.
     */
    public String getMake(){
        return this.make;
    }

    /**
     * Returns the registration number of the vehicle.
     */
    public int getRegistrationNumber()
    {
        return this.registrationNumber;
    }

    /**
     * Returns the category of the vehicle.
     */
    public VehicleCategory getCategory()
    {
        return this.category;
    }

    /**
     * Returns the total kilometers driven by the vehicle.
     */
    public int getTotalKilometres()
    {
        return this.totalKilometers;
    }

    /**
     * Returns the total rides driven by the vehicle.
     */
    public int getTotalRides()
    {
        return totalRides;
    }

    /**
     * Returns the total kilometers driven by the vehicle in the current month.
     */
    public int getTotalKm()
    {
        return totalKm;
    }

    /**
     * Returns the total money made by the vehicle in the current month.
     */
    public double getTotalMoneyMade()
    {
        return totalMoneyMade;
    }

    /**
     * Abstract method to get the specific type of the vehicle.
     */
    public abstract VehicleCategory getType();

    /**
     * Adds a ride summary to the vehicle's monthly total,
     * updating distance and money made.
     */
    public void addMonthlySummary(int distance, double revenue)
    {
        this.totalRides++;
        this.totalKm += distance;
        this.totalMoneyMade += revenue;
    }

    /**
     * Calculates the fare for a ride, to be implemented by
     * ZoomerLite, ZoomerStandard, ZoomerPro.
     */
    @Override
    public abstract double calcFare(double dist, int passengers,
                                    boolean isPeakHr, int largeBags);

    /**
     * Resets the vehicle's monthly summary for rides, distance, and revenue.
     */
    public void resetMonthlySummary()
    {
        this.totalRides = 0;
        this.totalKm = 0;
        this.totalMoneyMade = 0.0;
    }

    /**
     * Returns a string representation of the vehicle,
     * including category, registration number, and make.
     */
    @Override
    public String toString()
    {
        return category + " " + registrationNumber + " - " + make;
    }

}
