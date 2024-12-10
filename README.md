
# Real-Time Event Ticketing System

This project is a Real-Time Event Ticketing System that combines a Command-Line Interface (CLI) for initial configuration, a Spring Boot backend for managing ticketing logic, and a React frontend for real-time visualization. The system demonstrates advanced multi-threading concepts such as the Producer-Consumer pattern for concurrent ticket handling.

## Overview

This real time ticket management system simulates a dynamic ticketing environment where Vendors (Producers) release tickets, and Customers (Consumers) purchase tickets concurrently.

Key Concepts Applied:
- Multi-threading for simulating concurrent vendor and customer activities.
- Producer-Consumer Design Pattern for managing ticket creation and consumption.
- Synchronization to ensure thread safety and data consistency.
- RESTful APIs with Spring Boot for backend management.
- React.js for an interactive user interface.

## Features

### Command-Line Interface (CLI)

- CLI will Configures the system before execution based on user input.
- Prompts the user to input parameters like:
    - Enter Total Number of Tickets:
    - Enter Ticket Release Rate:
    - Enter Customer Retrieval Rate:
    - Enter Max Ticket Capacity:

### Spring Boot Backend

- Manages core business logic for ticket handling, CRUD operations, and transaction tracking.
- Simulates multi-threaded environments with thread safety ensured through locks.
- Ensures thread safety with synchronized methods for adding and removing tickets.
- RESTful API to connect the frontend with backend services.

### React Frontend

- Provides real-time updates on system status.
- Features:
    - Current Ticket Availability: Shows the available tickets.
    - CRUD operations display: Tracks vendor and customer updates.
    - Start/Stop Controls: Allows dynamic control of the threads in the system

### Synchronization & Thread Safety

- Ensures safe access to shared resources like the TicketPool using synchronized methods and locks.
- Prevents race conditions and guarantees data integrity.

### Error Handling & Logging

- Gracefully handles invalid inputs and runtime errors.
- Logs critical events such as:
    - CRUD operations for Vendors, Customers, and Tickets.
    - TicketPool updates.
    - Tickets purchased by customers
    - System warnings (e.g., ticket pool full/empty)
## Technology Stack

### Frontend
- React.js: A JavaScript library for building dynamic user interfaces.
- Other libraries used in react,
    - React and React DOM - To allow React components to interact with the DOM.
    - React Router for routing - For routing capabilities for navigation between different pages.
    - React axios - For making HTTP requests to the backend APIs.
    - React react-toastify - For displaying toast notifications.

- Bootstrap: For responsive and mobile-friendly UI design.
- Vite for development and building the project.

### Backend
- Spring Boot: A Java framework to create RESTful APIs and manage business logic.
- Java Multi-threading: Implements concurrency using the Runnable interface and thread-safe data structures.

### CLI Interface
- Java Standard IO: Provides a prompt-based interface for initial system setup.

### Tools Used

- Git: Version control for source code management.
- Postman: For testing RESTful APIs during development.
- Swagger: For API documentation and testing.
## System Architecture

This project follows layered architecture:

### Command-Line Interface (CLI)
- Captures system configuration parameters via the ConfigurationDTO object.

### Backend (Spring Boot):
- The backend simulates the real-world behavior of vendors and customers using multi-threading.
- Simulates concurrent operations using the Producer-Consumer pattern,
    - Producers (Vendors): Add tickets to the TicketPool at configurable intervals.
    - Consumers (Customers): Dynamically purchase tickets from the pool.
### Frontend (React):
- Fetches real-time data from the backend.
- Displays ticket availability and logs in a user-friendly interface.

## Setup Instructions

### Prerequisites

Ensure the following tools are installed:

- Java Development Kit (JDK 11+)
- Node.js (v16 or higher)
- Maven :For building Spring Boot applications
- npm :for managing frontend dependencies

### Steps to Run the Project

#### Clone the Repository
https://github.com/TharinduDMadushanka/real-time-event-ticketing-system-iit-oop-cw.git

#### Frontend Setup
- git clone
- cd backend
- mvn clean install
- mvn spring-boot:run

#### Frontend Setup
- npm create vite@latest
- cd directory
- npm install
- npm run dev

## Future Enhancements

- Priority Customers: Introduce a VIP system where high-priority customers can purchase tickets faster without a waiting.

- Notification System: Implement email or SMS notifications for ticket purchases and updates.
