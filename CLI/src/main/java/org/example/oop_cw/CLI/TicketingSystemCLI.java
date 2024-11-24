package org.example.oop_cw.CLI;

import com.google.gson.Gson;
import org.example.oop_cw.ticket.Customer;
import org.example.oop_cw.ticket.TicketPool;
import org.example.oop_cw.ticket.Vendor;
import org.example.oop_cw.Config.SystemConfig;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class TicketingSystemCLI {
    private static SystemConfig configuration = new SystemConfig();
    private static boolean running = false;
    private static TicketPool ticketPool;
    private static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        promptConfigurationUpdate();
        runCommandLoop();
    }

    private static void promptConfigurationUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to load the existing configuration? (yes/no)");
        String input = scanner.nextLine().trim().toLowerCase();

        if (input.equals("yes")) {
            loadConfiguration();
        } else {
            System.out.println("Starting fresh configuration setup.");
            configureSystem();
            saveConfiguration();
        }
    }

    private static void loadConfiguration() {
        try (Reader reader = new FileReader("config.json")) {
            Gson gson = new Gson();
            configuration = gson.fromJson(reader, SystemConfig.class);

            if (configuration == null) {
                System.out.println("Invalid configuration file. Proceeding with new setup.");
                configuration = new SystemConfig();
                configureSystem();
                saveConfiguration();
            }

            initializeTicketPool();
            System.out.println("Configuration loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file not found. Proceeding with new setup.");
            configuration = new SystemConfig();
            configureSystem();
            saveConfiguration();
        } catch (IOException e) {
            System.out.println("Error loading configuration: " + e.getMessage());
        }
    }

    private static void saveConfiguration() {
        try (Writer writer = new FileWriter("config.json")) {
            Gson gson = new Gson();
            gson.toJson(configuration, writer);
            System.out.println("Configuration saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving configuration: " + e.getMessage());
        }
    }

    private static void configureSystem() {
        Scanner scanner = new Scanner(System.in);

        configuration.setTotalTickets(getIntInput(scanner, "Enter total number of tickets:", 1, Integer.MAX_VALUE));
        configuration.setTicketReleaseRate(getIntInput(scanner, "Enter ticket release rate (ms):", 10, 10000));
        configuration.setCustomerRetrievalRate(getIntInput(scanner, "Enter customer retrieval rate (ms):", 10, 10000));
        configuration.setMaxTicketCapacity(getIntInput(scanner, "Enter maximum ticket capacity:", 1, configuration.getTotalTickets()));

        initializeTicketPool();
    }

    private static void initializeTicketPool() {
        ticketPool = new TicketPool(configuration.getMaxTicketCapacity());
    }

    private static int getIntInput(Scanner scanner, String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt + " ");
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.out.println("Please enter a value between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return value;
    }

    private static void runCommandLoop() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Commands: 'start' to begin, 'stop' to halt, 'exit' to quit.");

        while (true) {
            System.out.print("Command: ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "start":
                    if (!running) {
                        startTicketHandling();
                    } else {
                        System.out.println("System is already running.");
                    }
                    break;
                case "stop":
                    if (running) {
                        stopTicketHandling();
                    } else {
                        System.out.println("System is not running.");
                    }
                    break;
                case "exit":
                    stopTicketHandling();
                    saveConfiguration();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unknown command. Please try 'start', 'stop', or 'exit'.");
            }
        }
    }

    private static void startTicketHandling() {
        running = true;
        System.out.println("Starting ticket handling...");

        // Create vendor threads to initialize the ticket pool
        for (int i = 1; i <= 1; i++) { // Only one vendor needed to fill the pool
            executor.submit(new Vendor(i, configuration.getMaxTicketCapacity(), ticketPool));
        }

        // Create customer threads to buy tickets
        for (int i = 1; i <= configuration.getTotalTickets(); i++) {
            executor.submit(new Customer(i, ticketPool, configuration.getCustomerRetrievalRate()));
        }

        // Monitor the ticket pool and stop the system when tickets are sold out
        while (!ticketPool.isSoldOut()) {
            try {
                Thread.sleep(100); // Small delay to prevent busy-waiting
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        stopTicketHandling();
        System.out.println("All tickets have been sold. System is stopping.");
    }

    private static void stopTicketHandling() {
        running = false;
        executor.shutdownNow();
        System.out.println("System has been stopped.");
    }



    public static boolean isRunning() {
        return running;
    }
}
