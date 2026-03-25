import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory {

    HashMap<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    void reduceRoom(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Arushi", "Single Room"));
        bookingQueue.add(new Reservation("Teena", "Suite Room"));
        bookingQueue.add(new Reservation("Aadi", "Double Room"));

        RoomInventory inventory = new RoomInventory();

        Set<String> allocatedRooms = new HashSet<>();
        HashMap<String, Set<String>> roomAllocation = new HashMap<>();

        int roomIdCounter = 1;

        System.out.println("Book My Stay - Hotel Booking System \n");

        while (!bookingQueue.isEmpty()) {

            Reservation r = bookingQueue.poll();

            if (inventory.getAvailability(r.roomType) > 0) {

                String roomId = r.roomType.substring(0,2).toUpperCase() + roomIdCounter++;
                allocatedRooms.add(roomId);

                roomAllocation.putIfAbsent(r.roomType, new HashSet<>());
                roomAllocation.get(r.roomType).add(roomId);

                inventory.reduceRoom(r.roomType);

                System.out.println("Reservation Confirmed");
                System.out.println("Guest: " + r.guestName);
                System.out.println("Room Type: " + r.roomType);
                System.out.println("Room ID: " + roomId);
                System.out.println();
            } 
            else {
                System.out.println("No rooms available for " + r.guestName + " (" + r.roomType + ")");
                System.out.println();
            }
        }
    }
}