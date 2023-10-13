package practice;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Appointment {
    private String description;
    private String reminder;

    public Appointment(String description, String reminder) {
        this.description = description;
        this.reminder = reminder;
    }

    public String getDescription() {
        return description;
    }

    public String getReminder() {
        return reminder;
    }
}

public class AppointmentScheduler {
    private Map<String, List<Appointment>> appointments;

    public AppointmentScheduler() {
        appointments = new HashMap<>();
    }

    public void scheduleAppointment(String date, String description, String reminder) {
        Appointment appointment = new Appointment(description, reminder);
        appointments.computeIfAbsent(date, k -> new ArrayList<>()).add(appointment);
        System.out.println("Appointment scheduled for " + date + ": " + description);
    }

    public void viewAppointments(String date) {
        List<Appointment> appointmentList = appointments.get(date);
        if (appointmentList != null && !appointmentList.isEmpty()) {
            System.out.println("Appointments for " + date + ":");
            for (Appointment appointment : appointmentList) {
                System.out.println("- " + appointment.getDescription() + " (Reminder: " + appointment.getReminder() + ")");
            }
        } else {
            System.out.println("No appointments scheduled for " + date);
        }
    }

    public static void main(String[] args) {
        AppointmentScheduler scheduler = new AppointmentScheduler();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Appointment Scheduler Menu:");
            System.out.println("1. Schedule Appointment");
            System.out.println("2. View Appointments");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter appointment description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter reminder: ");
                    String reminder = scanner.nextLine();
                    scheduler.scheduleAppointment(date, description, reminder);
                    break;
                case 2:
                    System.out.print("Enter date to view appointments (YYYY-MM-DD): ");
                    date = scanner.nextLine();
                    scheduler.viewAppointments(date);
                    break;
                case 3:
                    System.out.println("Exiting appointment scheduler. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
