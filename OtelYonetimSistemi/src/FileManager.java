import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
        private final String roomsFile = "rooms.txt";
        private final String customersFile = "customers.txt";
        private final String reservationsFile = "reservations.txt";

        // =====================
        // ROOM METHODS
        // =====================

        public List<Room> readRooms() {
            List<Room> rooms = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(roomsFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    int id = Integer.parseInt(data[0]);
                    String type = data[1];
                    int capacity = Integer.parseInt(data[2]);
                    double price = Double.parseDouble(data[3]);
                    String status = data[4];

                    rooms.add(new Room(id, type, capacity, price, status));
                }
            } catch (IOException e) {
                System.out.println("Oda dosyası bulunamadı, yeni dosya oluşturulacak.");
            }

            return rooms;
        }

        public void writeRoom(Room room) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(roomsFile, true))) {
                writer.write(room.toFileString());
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Oda yazılamadı!");
            }
        }

        // =====================
        // CUSTOMER METHODS
        // =====================

        public List<Customer> readCustomers() {
            List<Customer> customers = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(customersFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String surname = data[2];
                    String phone = data[3];

                    customers.add(new Customer(id, name, surname, phone));
                }
            } catch (IOException e) {
                System.out.println("Müşteri dosyası bulunamadı, yeni dosya oluşturulacak.");
            }

            return customers;
        }

        public void writeCustomer(Customer customer) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(customersFile, true))) {
                writer.write(customer.toFileString());
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Müşteri yazılamadı!");
            }
        }

        // =====================
        // RESERVATION METHODS
        // =====================

        public List<Reservation> readReservations() {
            List<Reservation> reservations = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(reservationsFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] d = line.split(",");

                    int id = Integer.parseInt(d[0]);
                    int cId = Integer.parseInt(d[1]);
                    int rId = Integer.parseInt(d[2]);
                    LocalDate checkIn = LocalDate.parse(d[3]);
                    LocalDate checkOut = LocalDate.parse(d[4]);
                    double price = Double.parseDouble(d[5]);
                    String status = d[6];

                    reservations.add(new Reservation(id, cId, rId, checkIn, checkOut, price, status));
                }
            } catch (IOException e) {
                System.out.println("Rezervasyon dosyası bulunamadı.");
            }

            return reservations;
        }

        public void writeReservation(Reservation res) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(reservationsFile, true))) {
                writer.write(res.toFileString());
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Rezervasyon yazılamadı!");
            }
        }

        // =====================
        // ID GENERATOR
        // =====================

        public int generateId(String filePath) {
            int lastId = 0;

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String current;
                String previous = null;

                while ((current = reader.readLine()) != null) {
                    previous = current;
                }

                if (previous != null) {
                    lastId = Integer.parseInt(previous.split(",")[0]);
                }

            } catch (IOException e) {
                return 1; // file does not exist
            }

            return lastId + 1;
        }

    public void updateRoom(Room selectedRoom) {
    }

    public void updateReservation(Reservation r) {
    }
}


