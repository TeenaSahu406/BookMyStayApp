import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    InvalidBookingException(String message) {
        super(message);
    }
}

// Inventory class
class RoomInventory {

    HashMap<String, Integer> rooms = new HashMap<>();

    RoomInventory() {
        rooms.put("Single Room", 2);
        rooms.put("Double Room", 1);
        rooms.put("Suite Room", 0);
    }

    void bookRoom(String roomType) throws InvalidBookingException {

        if (!rooms.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        int available = rooms.get(roomType);

        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for: " + roomType);
        }

        rooms.put(roomType, available - 1);
        System.out.println("Booking confirmed for " + roomType);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        String[] requests = {
                "Single Room",
                "Suite Room",
                "Luxury Room"
        };

        System.out.println("Book My Stay - Hotel Booking System\n");

        for (String room : requests) {

            try {
                inventory.bookRoom(room);
            } 
            catch (InvalidBookingException e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
    }
}