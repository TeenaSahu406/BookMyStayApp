import java.util.*;

// Reservation request
class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Shared inventory (thread-safe)
class RoomInventory {

    HashMap<String, Integer> rooms = new HashMap<>();

    RoomInventory() {
        rooms.put("Single Room", 2);
        rooms.put("Double Room", 1);
        rooms.put("Suite Room", 1);
    }

    // synchronized critical section
    public synchronized void allocateRoom(Reservation r) {

        int available = rooms.getOrDefault(r.roomType, 0);

        if (available > 0) {
            rooms.put(r.roomType, available - 1);
            System.out.println(Thread.currentThread().getName() +
                    " confirmed booking for " + r.guestName +
                    " (" + r.roomType + ")");
        } else {
            System.out.println(Thread.currentThread().getName() +
                    " failed booking for " + r.guestName +
                    " (" + r.roomType + ") - No rooms available");
        }
    }
}

// Booking processor thread
class BookingProcessor extends Thread {

    Queue<Reservation> queue;
    RoomInventory inventory;

    BookingProcessor(Queue<Reservation> queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {

        while (true) {

            Reservation r;

            synchronized (queue) {
                if (queue.isEmpty()) {
                    break;
                }
                r = queue.poll();
            }

            inventory.allocateRoom(r);
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Arushi", "Single Room"));
        bookingQueue.add(new Reservation("Teena", "Single Room"));
        bookingQueue.add(new Reservation("Aadi", "Single Room"));
        bookingQueue.add(new Reservation("Rahul", "Suite Room"));

        RoomInventory inventory = new RoomInventory();

        System.out.println("Book My Stay - Hotel Booking System \n");

        BookingProcessor t1 = new BookingProcessor(bookingQueue, inventory);
        BookingProcessor t2 = new BookingProcessor(bookingQueue, inventory);

        t1.setName("Thread-1");
        t2.setName("Thread-2");

        t1.start();
        t2.start();
    }
}