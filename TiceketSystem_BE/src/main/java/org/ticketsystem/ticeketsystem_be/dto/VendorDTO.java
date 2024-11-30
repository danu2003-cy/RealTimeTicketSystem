package org.ticketsystem.ticeketsystem_be.dto;


public class VendorDTO {

    private int id;
    private String name;
    private int ticketsPerRelease;

    public VendorDTO() {
    }

    public VendorDTO(int id, String name, int ticketsPerRelease) {
        this.id = id;
        this.name = name;
        this.ticketsPerRelease = ticketsPerRelease;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTicketsPerRelease() {
        return ticketsPerRelease;
    }

    public void setTicketsPerRelease(int ticketsPerRelease) {
        this.ticketsPerRelease = ticketsPerRelease;
    }
}
