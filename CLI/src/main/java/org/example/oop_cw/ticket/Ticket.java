package org.example.oop_cw.ticket;

public class Ticket {
    private final int ticketId;
    private String customerName;
    private String customerEmail;
    private String customerContact;
    private int customerAge;
    private boolean isAvailable;

    // Constructor
    public Ticket(int ticketId) {
        this.ticketId = ticketId;
        this.isAvailable = true; // Tickets start as available.
    }

    // Getters
    public int getTicketId() {
        return ticketId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public int getCustomerAge() {
        return customerAge;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Method to assign ticket ownership
    public void assignToCustomer(String name, String email, String contact, int age) {
        if (!isAvailable) {
            throw new IllegalStateException("Ticket " + ticketId + " is already assigned to a customer.");
        }

        this.customerName = name;
        this.customerEmail = email;
        this.customerContact = contact;
        this.customerAge = age;
        this.isAvailable = false; // Mark ticket as unavailable.
    }

    // Method to release ticket ownership
    public void releaseTicket() {
        if (isAvailable) {
            throw new IllegalStateException("Ticket " + ticketId + " is already available.");
        }

        this.customerName = null;
        this.customerEmail = null;
        this.customerContact = null;
        this.customerAge = 0;
        this.isAvailable = true; // Mark ticket as available.
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId + ", Available: " + isAvailable +
                (isAvailable ? "" : ", Customer: " + customerName);
    }
}
