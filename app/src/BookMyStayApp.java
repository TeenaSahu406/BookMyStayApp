import java.util.*;

class Service {
    String name;
    double price;

    Service(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        // Map to store reservation ID -> list of services
        HashMap<String, List<Service>> addOnServices = new HashMap<>();

        String reservationId = "RES101";

        // Creating services
        Service breakfast = new Service("Breakfast", 500);
        Service spa = new Service("Spa", 1500);
        Service airportPickup = new Service("Airport Pickup", 800);

        // Add services to reservation
        List<Service> services = new ArrayList<>();
        services.add(breakfast);
        services.add(spa);
        services.add(airportPickup);

        addOnServices.put(reservationId, services);

        // Display selected services
        System.out.println("Book My Stay - Hotel Booking System v7.0\n");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Selected Add-On Services:");

        double totalCost = 0;

        for (Service s : addOnServices.get(reservationId)) {
            System.out.println("- " + s.name + " : " + s.price);
            totalCost += s.price;
        }

        System.out.println("\nTotal Add-On Cost: " + totalCost);
    }
}