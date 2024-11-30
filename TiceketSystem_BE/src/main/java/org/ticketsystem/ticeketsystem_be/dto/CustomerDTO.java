package org.ticketsystem.ticeketsystem_be.dto;

public class CustomerDTO {

    private int customerId;
    private String name;
    private boolean isVIP;

    public CustomerDTO() {
    }

    public CustomerDTO(int customerId, String name, boolean isVIP) {
        this.customerId = customerId;
        this.name = name;
        this.isVIP = isVIP;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }
}
