import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * BookingSystem manages customer and vehicle registrations, ride bookings,
 * and generates daily and monthly reports for a taxi service.
 * Student id: 33878587
 * Student email: csom0007@student.monash.edu
 * Version number:4.0
 * Name: Chetan Arjun Somuse
 */
public class BookingSystem
{
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<ZoomerVehicle> vehicles = new ArrayList<>();
    private ArrayList<RideBooking> rides = new ArrayList<>();
    private int rideCount = 0;

    /**
     * Books a ride for a customer, validates input, and calculates fare.
     */
    public void bookRide() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Customer ID: ");
        int customerID = Integer.parseInt(scanner.nextLine());

        Customer customer = findCustomerByID(customerID);
        if (customer == null) {
            System.out.println("Customer not found. Booking failed.");
            return;
        }

        // Display available vehicles
        System.out.println("\nAvailable Vehicles:");
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + ". " + vehicles.get(i));
        }

        System.out.print("Select a vehicle by number: ");
        int vehicleIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (vehicleIndex < 0 || vehicleIndex >= vehicles.size()) {
            System.out.println("Invalid selection. Booking failed.");
            return;
        }

        ZoomerVehicle vehicle = vehicles.get(vehicleIndex);

        System.out.print("Enter Start Location: ");
        String startLocation = scanner.nextLine();
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter Distance (km): ");
        int distance = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Number of Passengers: ");
        int passengers = Integer.parseInt(scanner.nextLine());

        int largeBags = 0;
        if (vehicle instanceof ZoomerPro) {
            System.out.print("Enter Number of Large Bags: ");
            largeBags = Integer.parseInt(scanner.nextLine());
        }

        // Ask the user for the booking date
        LocalDate bookingDate = null;
        while (bookingDate == null) {
            try {
                System.out.print("Enter the date of the ride (YYYY-MM-DD): ");
                String dateInput = scanner.nextLine();
                bookingDate = LocalDate.parse(dateInput);
            } catch (DateTimeParseException e) { //https://docs.oracle.com/javase/10/docs/api/java/time/format/DateTimeParseException.html
                System.out.println("Invalid date format. " +
                        "Please enter the date " +
                        "in YYYY-MM-DD format.");
            }
        }

        boolean isPeakTime = checkPeakTime();
        double fare = vehicle.calcFare(distance, passengers, isPeakTime,
                largeBags);

        // If fare is 0.0, print error message and cancel booking
        if (fare == 0.0)
        {
            System.out.println("Booking failed. Vehicles have limitations.");
            return;
        }

        // Create new RideBooking
        RideBooking ride = new RideBooking(++rideCount,
                customer, vehicle, bookingDate,
                startLocation, destination, distance, passengers, largeBags);
        rides.add(ride);
        customer.addRide(ride);

        System.out.println("Ride booked successfully with fare: $" + fare);
    }

    /**
     * Checks if the current time is within peak hours (7-9 AM or 5-7 PM).
     */
    private boolean checkPeakTime()
    {
        int hour = java.time.LocalTime.now().getHour();
        return (hour >= 7 && hour < 9) || (hour >= 17 && hour < 19); // https://stackoverflow.com/questions/25539891/how-to-get-the-current-hour-with-new-date-time-api-in-java-8
    }

    /**
     * Displays the daily report of rides for a specific(y) or current date(n)
     */
    private void displayDailyReport()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to enter a specific date for the report? "
                + "(y/n): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        LocalDate reportDate = null;
        if (choice.equals("y")) {
            while (reportDate == null)
            {
                try
                {
                    System.out.print("Enter the report date (YYYY-MM-DD): ");
                    String dateInput = scanner.nextLine();
                    reportDate = LocalDate.parse(dateInput); //Local date parse inpt

                }
                catch (DateTimeParseException e)
                {
                    System.out.println("Invalid date format. Please enter " +
                            "the date in YYYY-MM-DD format.");
                }
            }
        } else
        {
            reportDate = LocalDate.now();

        }

        // Display rides booked on the reportDate
        System.out.println("\nDaily Ride Report for " + reportDate);
        boolean hasRides = false;

        for (RideBooking ride : rides)
        {
            if (ride.getBookingDate().equals(reportDate))
            {
                System.out.println(ride);
                hasRides = true;
            }
        }

        if (!hasRides) {
            System.out.println("No rides were booked on " + reportDate);
        }
    }

    /**
     * Displays a monthly summary report for all rides and totals
     * for each vehicle.
     */
    private void displayMonthlySummary()
    {
        Scanner scanner = new Scanner(System.in);

        // Get the year and month from the user
        System.out.print("Enter the year for the summary: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the month for the summary (1-12): ");
        int month = Integer.parseInt(scanner.nextLine());

        System.out.println("\nMonthly Summary Report for " + year + "-" +
                month);

        // Reset summary fields in each vehicle
        for (ZoomerVehicle vehicle : vehicles)
        {
            vehicle.resetMonthlySummary();
        }

        // Variable to check if any rides were found for the specified month
        boolean anyRides = false;
        for (RideBooking ride : rides) {
            LocalDate bookingDate = ride.getBookingDate();
            if (bookingDate.getYear() == year && bookingDate.getMonthValue()
                    == month) {
                ZoomerVehicle vehicle = ride.getVehicle();
                boolean isPeakTime = checkPeakTime();  // Check if the ride
                // was during peak time
                double fare = vehicle.calcFare(ride.getDistance(),
                        ride.getPassengers(), isPeakTime, ride.getLargeBags());
                vehicle.addMonthlySummary(ride.getDistance(), fare);
                System.out.println("Ride ID: " + ride.getRideID() +
                        ", Vehicle: " + vehicle.getMake() +
                        ", Start Location: " + ride.getStartLocation() +
                        ", Destination: " + ride.getDestination() +
                        ", Distance: " + ride.getDistance() + " km" +
                        ", Passengers: " + ride.getPassengers() +
                        ", Fare: $" + fare);

                anyRides = true;
            }
        }
        System.out.println("\nVehicle Totals for " + year + "-" + month);
        for (ZoomerVehicle vehicle : vehicles)
        {
            // Only show vehicles with rides in the specified month
            if (vehicle.getTotalRides() > 0)
            {
                System.out.println("Vehicle: " + vehicle.getMake() +
                        ", Total Rides: " + vehicle.getTotalRides() +
                        ", Total Kilometres: " + vehicle.getTotalKm() +
                        ", Total Money made: $" + vehicle.getTotalMoneyMade());
            }
        }

        if (!anyRides) {
            System.out.println("No rides were booked for this month.");
        }
    }

    /**
     *  Finds a customer by their unique ID and returns the Customer object
     */
    private Customer findCustomerByID(int customerID)
    {
        for (Customer customer : customers)
        {
            if (customer.getCustomerNumber() == customerID)
            {
                return customer;
            }
        }
        System.out.println("Customer not found.");
        return null;
    }

    /**
     * Generates a unique customer ID based on the current number of customers.
     */
    private int generateUniqueCustomerID()
    {
        return customers.size() + 1;
    }

    /**
     * Checks if the provided registration number is unique among vehicles.
     */
    private boolean isRegistrationNumberUnique(int regNumber)
    {
        for (ZoomerVehicle vehicle : vehicles)
        {
            if (vehicle.getRegistrationNumber() == regNumber)
            {
                return false; // Registration number already exists
            }
        }
        return true; // Registration number is unique

    }

    /**
     * Registers a new customer with a unique ID and adds them to the
     * customer list.
     */
    public void registerCustomer()
    {

        Scanner inp = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String name = inp.nextLine();

        int customerID = generateUniqueCustomerID();
        Customer newCustomer = new Customer(name, customerID);
        customers.add(newCustomer);

        System.out.println("Customer registered successfully:");
        System.out.println(newCustomer);
    }

    /**
     * Registers a new vehicle by validating inputs and selecting the vehicle
     * category.
     */
    public void registerVehicle()
    {
        Scanner scanner = new Scanner(System.in);
        int regNumber = 0;
        boolean validRegNumber = false;

        while (validRegNumber == false)
        {
            System.out.print("Enter vehicle registration number: ");
            if (scanner.hasNextInt())
            {
                regNumber = scanner.nextInt();
                if (isRegistrationNumberUnique(regNumber))
                {
                    validRegNumber = true;
                    scanner.nextLine();
                } else
                {
                    System.out.println("A vehicle with this registration " +
                            "number" +
                            " already exists. Please enter a unique " +
                            "registration number.");
                }
            } else
            {
                System.out.println("Please enter a valid integer for " +
                        "the registration number.");
                scanner.next();
            }
        }

        System.out.print("Enter vehicle make: ");
        String make = scanner.nextLine();

        System.out.println("Select Vehicle Category " +
                "(1: LITE, 2: STANDARD, 3: PRO): ");
        int categoryChoice;
        if (scanner.hasNextInt())
        {
            categoryChoice = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("Invalid input." +
                    " Registration failed.");
            return;
        }

        VehicleCategory category;
        switch (categoryChoice)
        {
            case 1:
                category = VehicleCategory.ZOOMERLITE;
                break;
            case 2:
                category = VehicleCategory.ZOOMERSTANDARD;
                break;
            case 3:
                category = VehicleCategory.ZOOMERPRO;
                break;
            default:
                System.out.println("Invalid category. Registration failed.");
                return;
        }

        ZoomerVehicle vehicle;
        switch (category)
        {
            case ZOOMERLITE:
                vehicle = new ZoomerLite(regNumber, make);
                break;
            case ZOOMERSTANDARD:
                vehicle = new ZoomerStandard(regNumber, make);
                break;
            case ZOOMERPRO:
                vehicle = new ZoomerPro(regNumber, make);
                break;
            default:
                return;
        }

        vehicles.add(vehicle);
        vehicle.addKms(vehicle.totalKilometers);
        System.out.println("Vehicle registered successfully as "
                + category + ".");
    }

    /**
     * Displays the main menu and handles user input to access
     * various system functions.
     */
    public void startMenu()
    {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running)
        {
            int option = 0;
            boolean validOption = false;

            // Loop until a valid number (1-6) is entered
            while (!validOption) {
                // Display the menu
                System.out.println("1. Register new customer");
                System.out.println("2. Register new Zoomer vehicle");
                System.out.println("3. Book a new ride");
                System.out.println("4. Display daily report for any day");
                System.out.println("5. Display monthly summary");
                System.out.println("6. Exit");

                System.out.print("Select an option from 1 to 6 only : ");

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();
                    if (option >= 1 && option <= 6) {
                        validOption = true;
                    } else {
                        System.out.println("Please e" +
                                "nter a number between 1 and 6.");
                    }
                } else {
                    System.out.println("Error: Please enter a valid integer.");
                    scanner.next();
                }
            }
            if (option == 1)
            {
                registerCustomer();
            } else if (option == 2)
            {
                registerVehicle();
            } else if (option == 3)
            {
                bookRide();
            } else if (option == 4)
            {
                displayDailyReport();
            } else if (option == 5)
            {
                displayMonthlySummary();
            } else if (option == 6)
            {
                running = false;
                System.out.println("Exiting the program. Thank you!");

            }
        }
    }

}
