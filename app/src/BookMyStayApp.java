import java.util.HashMap;

class RoomInventory {

    HashMap<String, Integer> rooms;

    RoomInventory() {
        rooms = new HashMap<>();

        // initialize room availability
        rooms.put("Single Room", 5);
        rooms.put("Double Room", 3);
        rooms.put("Suite Room", 2);
    }

    void showInventory() {
        System.out.println("Room Inventory:");
        for (String room : rooms.keySet()) {
            System.out.println(room + " : " + rooms.get(room));
        }
    }

    int getAvailability(String roomType) {
        return rooms.getOrDefault(roomType, 0);
    }

    void updateAvailability(String roomType, int count) {
        rooms.put(roomType, count);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        System.out.println("Book My Stay - Hotel Booking System \n");

        inventory.showInventory();

        System.out.println("\nChecking availability of Single Room:");
        System.out.println(inventory.getAvailability("Single Room"));
    }
}