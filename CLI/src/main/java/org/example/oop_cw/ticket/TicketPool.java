package org.example.oop_cw.ticket;

import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {
    private int tickets = 0;
    private int maxCapacity;
    private final ReentrantLock lock = new ReentrantLock();
    private boolean soldOut = false;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public boolean addTickets(int count) {
        lock.lock();
        try {
            if (soldOut) {
                System.out.println("All tickets are sold out. Vendors cannot add tickets.");
                return false;
            }
            if (tickets >= maxCapacity) {
                System.out.println("Cannot add tickets: Pool is at max capacity.");
                return false;
            }
            tickets = Math.min(tickets + count, maxCapacity);
            System.out.println("Tickets added: " + tickets + ", Total tickets: " + tickets);
            return true;
        } finally {
            lock.unlock();
        }
    }

    public boolean removeTicket() {
        lock.lock();
        try {
            if (tickets > 0) {
                tickets--;
                System.out.println("Ticket removed. Tickets remaining: " + tickets);
                if (tickets == 0) {
                    soldOut = true;
                    System.out.println("All tickets are sold out.");
                }
                return true;
            } else {
                System.out.println("No tickets available.");
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean isSoldOut() {
        lock.lock();
        try {
            return soldOut;
        } finally {
            lock.unlock();
        }
    }

    public int getAvailableTickets() {
        lock.lock();
        try {
            return tickets;
        } finally {
            lock.unlock();
        }
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
