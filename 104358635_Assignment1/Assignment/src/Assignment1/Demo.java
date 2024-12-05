package Assignment1;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Display cinema setup and collect user inputs
        System.out.println("***** Welcome to Melbourne Cinema Booking System (MCBS) *****\n");
        System.out.print("Enter the total number of rows in the cinema: ");
        int numRows = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the show's date:  ");
        String showDate = scanner.nextLine();

        // Get seat prices for different categories from the user
        System.out.print("Enter the following price for Standard seats:");
        double standardPrice = scanner.nextDouble();
        System.out.print("Enter the following price for Pensioner seats: :");
        double pensionerPrice = scanner.nextDouble();
        System.out.print("Enter the following price for Frequent seats:");
        double frequentPrice = scanner.nextDouble();
        // Create a CinemaBookingSystem object with user-provided data
        Cinema cinema = new Cinema(numRows, showDate, standardPrice, pensionerPrice, frequentPrice);

        while (true) {
        	// Display the main menu
            displayMenu();
            int choice = scanner.nextInt();
            // Process the user's choice
            switch (choice) {
                case 1:
                    cinema.displayAvailableSeats();
                    break;
                case 2:
                	// Book seats for the show
                    cinema.displayAvailableSeats();
                    bookSeats(cinema, scanner);
                    break;
                case 3:
                	// Refund previously booked seats
                    cinema.displayAvailableSeats();
                    refundSeats(cinema, scanner);
                    break;
                case 4:
                	// Display booking statistics to the user
                    cinema.displayStatistics();
                    break;
                case 5:
                	// Exit the booking system and close the program
                    System.out.println("Thank you for using MCBS!");
                    scanner.close();
                    return;
                default:
                	// Invalid choice, ask the user to try again
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    // Display the main menu 
    private static void displayMenu() {
    	System.out.println("\n                                                   ");
        System.out.println("*      Melbourne Cinema Booking System (MCBS)        ");
        System.out.println("                                                     ");
        System.out.println("1. Display Available Seats");
        System.out.println("2. Book Seats");
        System.out.println("3. Refund Seats");
        System.out.println("4. Display Statistics");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }
    //  book seats based on user input
    private static void bookSeats(Cinema cinema, Scanner scanner) {
        System.out.print("Enter the number of seats to book: ");
        int numSeatsToBook = scanner.nextInt();
        cinema.bookSeats(numSeatsToBook);
    }
    // refund seats based on user input
    private static void refundSeats(Cinema cinema, Scanner scanner) {
        System.out.print("Enter the number of seats to refund: ");
        int numSeatsToRefund = scanner.nextInt();
        cinema.refundSeats(numSeatsToRefund);
    }
}
