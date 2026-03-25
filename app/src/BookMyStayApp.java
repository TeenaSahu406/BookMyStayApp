import java.util.*;

// Reservation class
class Reservation {
    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room Type: " + roomType);
    }
}

// Booking History class
class BookingHistory {

    List<Reservation> history = new ArrayList<>();

    void addReservation(Reservation r) {
        history.add(r);
    }

    void showHistory() {
        System.out.println("Booking History:\n");
        for (Reservation r : history) {
            r.display();
        }
    }

    void generateReport() {
        System.out.println("\nTotal Bookings: " + history.size());
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        BookingHistory bookingHistory = new BookingHistory();

        // confirmed reservations
        bookingHistory.addReservation(new Reservation("RES101", "Arushi", "Single Room"));
        bookingHistory.addReservation(new Reservation("RES102", "Teena", "Suite Room"));
        bookingHistory.addReservation(new Reservation("RES103", "Aadi", "Double Room"));

        System.out.println("Book My Stay - Hotel Booking System \n");

        // show booking history
        bookingHistory.showHistory();

        // generate report
        bookingHistory.generateReport();
    }
}