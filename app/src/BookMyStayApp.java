import java.util.HashMap;

// Room class (domain model)
class Room {
    String type;
    double price;

    Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    void display() {
        System.out.println("Room Type: " + type + " | Price: " + price);
    }
}

// Inventory class (state holder)
class RoomInventory {
    HashMap<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0);
        inventory.put("Suite Room", 2);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Room single = new Room("Single Room", 2000);
        Room doubleRoom = new Room("Double Room", 3500);
        Room suite = new Room("Suite Room", 6000);

        System.out.println("Book My Stay - Hotel Booking System \n");
        System.out.println("Available Rooms:\n");

        if (inventory.getAvailability("Single Room") > 0) {
            single.display();
            System.out.println("Available: " + inventory.getAvailability("Single Room"));
            System.out.println();
        }

        if (inventory.getAvailability("Double Room") > 0) {
            doubleRoom.display();
            System.out.println("Available: " + inventory.getAvailability("Double Room"));
            System.out.println();
        }

        if (inventory.getAvailability("Suite Room") > 0) {
            suite.display();
            System.out.println("Available: " + inventory.getAvailability("Suite Room"));
        }
    }
}