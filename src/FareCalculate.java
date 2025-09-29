/**
 * Interface for calculating the fare based on distance, passenger count,
 * peak hour status, and the number of large bags.
 * Student id: 33878587
 * Student email: csom0007@student.monash.edu
 * Version number:1.0
 * Name: Chetan Arjun Somuse
 */
public interface FareCalculate
{
    /**
     * Calculates the fare for a ride based on provided parameters.
     */
    double calcFare(double distance, int passengers,
                    boolean isPeakHour, int largeBags);
}
