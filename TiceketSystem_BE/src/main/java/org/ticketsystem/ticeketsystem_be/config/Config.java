package org.ticketsystem.ticeketsystem_be.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ticketsystem.ticeketsystem_be.dto.ConfigurationDTO;


import java.util.Scanner;

@Configuration
public class Config {

    @Bean
    public ConfigurationDTO systemConfiguration() {
        ConfigurationDTO systemConfigDTO = new ConfigurationDTO();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Total Number of Tickets: ");
        systemConfigDTO.setTotalTickets(scanner.nextInt());

        System.out.print("Enter Ticket Release Rate: ");
        systemConfigDTO.setTicketReleaseRate(scanner.nextInt());

        System.out.print("Enter Customer Retrieval Rate: ");
        systemConfigDTO.setCustomerRetrievalRate(scanner.nextInt());

        System.out.print("Enter Max Ticket Capacity: ");
        systemConfigDTO.setMaxTicketCapacity(scanner.nextInt());

        System.out.println("Configuration Initialized...!");
        return systemConfigDTO;
    }
}
