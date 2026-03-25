import java.io.*;
import java.util.*;

// Reservation class
class Reservation implements Serializable {
    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println(reservationId + " | " + guestName + " | " + roomType);
    }
}

// System state (inventory + booking history)
class SystemState implements Serializable {
    HashMap<String, Integer> inventory;
    List<Reservation> bookings;

    SystemState(HashMap<String, Integer> inventory, List<Reservation> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

public class BookMyStayApp {

    static final String FILE_NAME = "hotel_data.ser";

    public static void main(String[] args) {

        HashMap<String, Integer> inventory = new HashMap<>();
        List<Reservation> bookingHistory = new ArrayList<>();

        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);

        bookingHistory.add(new Reservation("RES101", "Arushi", "Single Room"));
        bookingHistory.add(new Reservation("RES102", "Teena", "Suite Room"));

        System.out.println("Book My Stay - Hotel Booking System \n");

        // Save state (serialization)
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            out.writeObject(new SystemState(inventory, bookingHistory));
            out.close();
            System.out.println("System state saved successfully.\n");
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }

        // Load state (deserialization)
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
            SystemState state = (SystemState) in.readObject();
            in.close();

            System.out.println("Recovered Inventory:");
            for (String type : state.inventory.keySet()) {
                System.out.println(type + " : " + state.inventory.get(type));
            }

            System.out.println("\nRecovered Booking History:");
            for (Reservation r : state.bookings) {
                r.display();
            }

        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
        }
    }
}