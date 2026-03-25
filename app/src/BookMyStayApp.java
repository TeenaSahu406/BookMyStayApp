import java.util.LinkedList;
import java.util.Queue;

// Reservation class (represents booking request)
class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        // Queue to store booking requests (FIFO)
        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Guest booking requests
        bookingQueue.add(new Reservation("Arushi", "Single Room"));
        bookingQueue.add(new Reservation("Teena", "Suite Room"));
        bookingQueue.add(new Reservation("Aadi", "Double Room"));

        System.out.println("Book My Stay - Hotel Booking System \n");
        System.out.println("Booking Requests in Queue:\n");

        // Display queue without processing allocation
        for (Reservation r : bookingQueue) {
            r.display();
        }
    }
}