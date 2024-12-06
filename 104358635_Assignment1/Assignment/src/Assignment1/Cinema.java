//test
package Assignment1;

import java.util.Scanner;

public class Cinema {
    private int numRows;               
    private String showDate;           
    private double standardPrice;      
    private double pensionerPrice;     
    private double frequentPrice;      
    private char[] seats;              


 // Constructor to create a movie object
    public Cinema(int numRows, String showDate, double standardPrice, double pensionerPrice, double frequentPrice) {
        this.setNumRows(numRows);
        this.setShowDate(showDate);
        this.setStandardPrice(standardPrice);
        this.setPensionerPrice(pensionerPrice);
        this.setFrequentPrice(frequentPrice);
        setSeats(new char[numRows * 10]);// Initialize seat array
        initializeSeats();// Set all seats as unreserved
    }
    // Methods to get and set prices and show date
    public double getFrequentPrice() {
        return frequentPrice;// Get price for a frequent visitor's seat
    }

    public void setFrequentPrice(double frequentPrice) {
        this.frequentPrice = frequentPrice;// Set price for a frequent visitor's seat
    }

    public double getPensionerPrice() {
        return pensionerPrice;// Get price for a senior citizen's seat
    }

    public void setPensionerPrice(double pensionerPrice) {
        this.pensionerPrice = pensionerPrice;// Set price for a senior citizen's seat
    }

    public String getShowDate() {
        return showDate;// Get the movie screening date
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;// Set the movie screening date
    }

    public double getStandardPrice() {
        return standardPrice;// Get price for a regular seat
    }

    public void setStandardPrice(double standardPrice) {
        this.standardPrice = standardPrice;// Set price for a regular seat

    }
   // Initialize seats with '-' to show unreserved
    private void initializeSeats() {
        for (int i = 0; i < getSeats().length; i++) {
            getSeats()[i] = '-';
        }
    }
// Display available seats in a grid
    void displayAvailableSeats() {
        System.out.println("\n");
        System.out.println("                         SCREEN                              ");
        System.out.println("                                                             ");
        System.out.println("                                                             ");
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < 10; j++) {
                int seatNum = (i * 10) + j + 1;
                char status = getSeats()[(i * 10) + j];
                System.out.print(seatNum + ":" + status + " ");
                if (j == 4) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println("Number of seats available : " + countAvailableSeats());
    }
// Count the number of available seats
    private int countAvailableSeats() {
        int count = 0;
        for (char status : getSeats()) {
            if (status == '-') {
                count++;
            }
        }
        return count;
    }
// Reserved number of seats
    public void bookSeats(int numSeatsToBook) {
        int S = 0;
        int P = 0;
        int F = 0;
        boolean success;// indicate reservation success
        if (numSeatsToBook <= 0 || numSeatsToBook > countAvailableSeats()) {
            success = false;// Reservation not successful
        } else {
            Scanner scanner = new Scanner(System.in);
            // Loop through seats to be booked
            for (int i = 0; i < numSeatsToBook; i++) {
                int seatType = 0;
                // Prompt user to enter seat number
                System.out.print("Enter seat number " + (i + 1) + ": ");
                int seatNum = scanner.nextInt();
                if (seatNum < 1 || seatNum > getNumRows() * 10 || getSeats()[seatNum - 1] != '-') {
                    System.out.println("The number of seats booked is incorrect. Please try once more");
                    i--;
                } else {
                    System.out.print("Enter the price category for " + seatNum
                            + "(standard - 1, pensioner - 2, frequent patrons - 3): ");
                    seatType = scanner.nextInt();// Read seat type from user
                    scanner.nextLine();// Consume newline
                    switch (seatType) {
                        case 1:
                            getSeats()[seatNum - 1] = 'S';// Reserve as regular seat
                            S++;
                            break;
                        case 2:
                            getSeats()[seatNum - 1] = 'P';// Reserve as senior 
                            P++;
                            break;
                        case 3:
                            getSeats()[seatNum - 1] = 'F';// Reserve as frequent 
                            F++;
                            break;
                        default:
                            System.out.println("The number of seats booked is incorrect. Please try once more");
                            i--;
                            continue;
                    }
                }
            }
            success = true;
        }
        if (success) {// Check if reservation successful and display message
            System.out.println("Booking successful!");
            displayBookingReceipt(numSeatsToBook, S, P, F);// Display booking 
        } else {
            System.out.println("The number of seats booked is incorrect. Please try once more.");
        }
    }

    public void refundSeats(int numSeatsToRefund) {
        int S = 0;
        int P = 0;
        int F = 0;
        boolean success;
        if (numSeatsToRefund <= 0 || numSeatsToRefund > (getNumRows() * 10) - countAvailableSeats()) {
            success = false;
        } else {
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < numSeatsToRefund; i++) {
                int seatType = 0;
                System.out.print("Enter seat number " + (i + 1) + ": ");
                int seatNum = scanner.nextInt();
                if (seatNum < 1 || seatNum > getNumRows() * 10 || getSeats()[seatNum - 1] == '-') {
                    System.out.println("The number of seats booked is incorrect. Please try once more.");
                    i--;
                } else {
                    System.out.print("Enter the price category for " + seatNum
                            + "(standard - 1, pensioner - 2, frequent patrons - 3): ");
                    seatType = scanner.nextInt();
                    scanner.nextLine();
                    switch (seatType) {
                        case 1:
                            getSeats()[seatNum - 1] = '-';
                            S++;
                            break;
                        case 2:
                            getSeats()[seatNum - 1] = '-';
                            P++;
                            break;
                        case 3:
                            getSeats()[seatNum - 1] = '-';
                            F++;
                            break;
                        default:
                            System.out.println("incorrect Seat type. Please try once more.");
                            i--;
                            continue;
                    }
                }
            }
            success = true;
        }
        if (success) {
            System.out.println("Refund successful!");
            displayRefundReceipt(numSeatsToRefund, S, P, F);
        } else {
            System.out.println("Invalid number of seats to refund. Please try again.");
        }
    }

    public void displayBookingReceipt(int numSeatsBooked, int S, int P, int F) {
        double totalAmount = (getFrequentPrice() * S) + (getPensionerPrice() * P) + (getFrequentPrice() * F);
        System.out.println("\nReceipt");
        System.out.println("*******");
        System.out.println("Date: " + getShowDate());
        System.out.println("Number of seats booked: " + numSeatsBooked);
        System.out.println(S + " * Standard @ $" + getStandardPrice() + " = $" + getFrequentPrice() * S);
        System.out.println(P + " * Pensioner @ $" + getPensionerPrice() + " = $" + getPensionerPrice() * P);
        System.out.println(F + " * Frequent Patrons @ $" + getFrequentPrice() + " = $" + getFrequentPrice() * F);
        System.out.println("Total: $" + totalAmount);
    }

    void displayRefundReceipt(int numSeatsRefunded, int S, int P, int F) {
        double totalAmount = (getFrequentPrice() * S) + (getPensionerPrice() * P) + (getFrequentPrice() * F);
        System.out.println("\nReceipt");
        System.out.println("*******");
        System.out.println("Date: " + getShowDate());
        System.out.println("Number of seats refunded: " + numSeatsRefunded);
        System.out.println(S + " * Standard @ $" + getStandardPrice() + " = $" + getFrequentPrice() * S);
        System.out.println(P + " * Pensioner @ $" + getPensionerPrice() + " = $" + getPensionerPrice() * P);
        System.out.println(F + " * Frequent Patrons @ $" + getFrequentPrice() + " = $" + getFrequentPrice() * F);
        System.out.println("Total: $" + totalAmount);
    }

    public void displayStatistics() {
        int numSeatsBooked = (getNumRows() * 10) - countAvailableSeats();
        double percentageBooked = (numSeatsBooked / (double) (getNumRows() * 10)) * 100;
        double averagePrice = (getStandardPrice() + getStandardPrice() + getFrequentPrice()) / 3;
        System.out.println("\n=== Statistics ===");
        System.out.println("Number of seats booked: " + numSeatsBooked);
        System.out.println("Percentage of seats booked: " + String.format("%.2f", percentageBooked) + "%");
        System.out.println("Average price of booked seats: $" + averagePrice);
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public char[] getSeats() {
        return seats;
    }

    public void setSeats(char[] seats) {
        this.seats = seats;
    }
}
