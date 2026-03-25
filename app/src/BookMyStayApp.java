import java.util.*;

// Reservation class
class Reservation {
    String reservationId;
    String roomType;

    Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }
}

// Inventory service
class RoomInventory {

    HashMap<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    void increaseRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) + 1);
    }

    void showInventory() {
        System.out.println("Current Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " : " + inventory.get(type));
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        // confirmed bookings
        HashMap<String, Reservation> bookings = new HashMap<>();

        bookings.put("RES101", new Reservation("RES101", "Single Room"));
        bookings.put("RES102", new Reservation("RES102", "Double Room"));

        // stack for rollback (released rooms)
        Stack<String> rollbackStack = new Stack<>();

        System.out.println("Book My Stay - Hotel Booking System \n");

        String cancelId = "RES101";

        if (bookings.containsKey(cancelId)) {

            Reservation r = bookings.get(cancelId);

            rollbackStack.push(r.roomType);

            inventory.increaseRoom(r.roomType);

            bookings.remove(cancelId);

            System.out.println("Booking Cancelled Successfully");
            System.out.println("Reservation ID: " + cancelId);
            System.out.println("Room Type Released: " + rollbackStack.peek());
            System.out.println();

        } else {
            System.out.println("Cancellation Failed: Reservation not found");
        }

        inventory.showInventory();
    }
}