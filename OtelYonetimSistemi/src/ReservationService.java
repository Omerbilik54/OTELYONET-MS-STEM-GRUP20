import java.time.LocalDate;
import java.util.List;

public class ReservationService {

    private FileManager fileManager;

    public ReservationService(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    // =========================================
    // REZERVASYON OLUŞTURMA
    // =========================================
    public Reservation createReservation(int customerId, int roomId,
                                         LocalDate checkIn, LocalDate checkOut,
                                         double price) {

        List<Room> rooms = fileManager.readRooms();
        List<Reservation> reservations = fileManager.readReservations();

        // Oda var mı?
        Room selectedRoom = null;
        for (Room r : rooms) {
            if (r.getRoomId() == roomId) {
                selectedRoom = r;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Oda bulunamadı!");
            return null;
        }

        // Tarih kontrolü
        if (!isDateAvailable(roomId, checkIn, checkOut)) {
            System.out.println("Bu tarih aralığında oda zaten dolu!");
            return null;
        }

        // ID oluştur
        int id = fileManager.generateId("reservations.txt");

        Reservation res = new Reservation(
                id,
                customerId,
                roomId,
                checkIn,
                checkOut,
                price,
                "Rezerve"
        );

        fileManager.writeReservation(res);

        // Oda durumunu güncelle
        selectedRoom.setStatus("Rezerve");
        fileManager.updateRoom(selectedRoom);

        System.out.println("Rezervasyon başarıyla oluşturuldu.");
        return res;
    }


    // =========================================
    // TARİH ÇAKIŞMASI KONTROLÜ
    // =========================================
    public boolean isDateAvailable(int roomId, LocalDate checkIn, LocalDate checkOut) {

        List<Reservation> reservations = fileManager.readReservations();

        for (Reservation r : reservations) {
            if (r.getRoomId() == roomId) {

                boolean overlap =
                        !(checkOut.isBefore(r.getCheckInDate()) ||
                                checkIn.isAfter(r.getCheckOutDate()));

                if (overlap) {
                    return false;
                }
            }
        }
        return true;
    }


    // =========================================
    // CHECK-IN
    // =========================================
    public void checkIn(int reservationId) {

        List<Reservation> reservations = fileManager.readReservations();
        List<Room> rooms = fileManager.readRooms();

        for (Reservation r : reservations) {
            if (r.getReservationId() == reservationId) {

                r.setStatus("Check-in");

                // Oda güncelle
                for (Room room : rooms) {
                    if (room.getRoomId() == r.getRoomId()) {
                        room.setStatus("Dolu");
                        fileManager.updateRoom(room);
                        break;
                    }
                }

                fileManager.updateReservation(r);
                System.out.println("Check-in işlemi tamamlandı.");
                return;
            }
        }

        System.out.println("Rezervasyon bulunamadı!");
    }


    // =========================================
    // CHECK-OUT
    // =========================================
    public void checkOut(int reservationId) {

        List<Reservation> reservations = fileManager.readReservations();
        List<Room> rooms = fileManager.readRooms();

        for (Reservation r : reservations) {
            if (r.getReservationId() == reservationId) {

                r.setStatus("Tamamlandı");

                // Oda güncelle
                for (Room room : rooms) {
                    if (room.getRoomId() == r.getRoomId()) {
                        room.setStatus("Boş");
                        fileManager.updateRoom(room);
                        break;
                    }
                }

                fileManager.updateReservation(r);
                System.out.println("Check-out başarıyla tamamlandı.");
                return;
            }
        }

        System.out.println("Rezervasyon bulunamadı!");
    }
}
