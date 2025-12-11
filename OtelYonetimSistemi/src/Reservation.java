import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {

        private int reservationId;
        private int customerId;
        private int roomId;
        private LocalDate checkInDate;
        private LocalDate checkOutDate;
        private double totalPrice;
        private String status; // "Rezerve", "Check-in", "Check-out"

        public Reservation(int reservationId, int customerId, int roomId,
                           LocalDate checkInDate, LocalDate checkOutDate,
                           double totalPrice, String status) {

            this.reservationId = reservationId;
            this.customerId = customerId;
            this.roomId = roomId;
            this.checkInDate = checkInDate;
            this.checkOutDate = checkOutDate;
            this.totalPrice = totalPrice;
            this.status = status;
        }

        // Getters
        public int getReservationId() {
            return reservationId;
        }

        public int getCustomerId() {
            return customerId;
        }

        public int getRoomId() {
            return roomId;
        }

        public LocalDate getCheckInDate() {
            return checkInDate;
        }

        public LocalDate getCheckOutDate() {
            return checkOutDate;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public String getStatus() {
            return status;
        }

        // Durum değişimi
        public void setStatus(String status) {
            this.status = status;
        }

        // Fiyat hesaplama
        public double calculatePrice(double roomPrice) {
            long days = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
            if (days <= 0) {
                return 0;
            }
            return days * roomPrice;
        }

        // TXT formatı
        public String toFileString() {
            return reservationId + "," + customerId + "," + roomId + ","
                    + checkInDate + "," + checkOutDate + ","
                    + totalPrice + "," + status;
        }

        @Override
        public String toString() {
            return "[Rezervasyon: " + reservationId + "] "
                    + "MüşteriID=" + customerId
                    + " | OdaID=" + roomId
                    + " | Tarih: " + checkInDate + " - " + checkOutDate
                    + " | Ücret: " + totalPrice + "₺"
                    + " | Durum: " + status;
        }
    }


