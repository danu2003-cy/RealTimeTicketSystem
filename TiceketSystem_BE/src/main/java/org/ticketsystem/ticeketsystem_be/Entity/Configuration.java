package org.ticketsystem.ticeketsystem_be.Entity;

import javax.persistence.*;

@Entity
@Table(name = "configuration_data")
public class Configuration {

        @Id
        @Column(name = "config_id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int configID;

        @Column(name = "total_tickets",nullable = false)
        private int totalTickets;

        @Column(name = "ticket_release_rate",nullable = false)
        private int ticketReleaseRate;

        @Column(name = "customer_retrieval_rate",nullable = false)
        private int customerRetrievalRate;

        @Column(name = "max_ticket_capacity",nullable = false)
        private int maxTicketCapacity;

    public Configuration() {
    }

    public Configuration(int configID, int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.configID = configID;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getConfigID() {
        return configID;
    }

    public void setConfigID(int configID) {
        this.configID = configID;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }
}
