import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuController {

    private Scanner scanner = new Scanner(System.in);
    private FileManager fileManager;
    private ReservationService reservationService;

    public MenuController() {
        this.fileManager = new FileManager();
        this.reservationService = new ReservationService(fileManager);
    }

    // ===========================
    // ANA MENÜ
    // ===========================
    public void run() {
        int choice;

        do {
            System.out.println("\n=== OTEL YÖNETİM SİSTEMİ ===");
            System.out.println("1 - Oda Ekle");
            System.out.println("2 - Oda Listele");
            System.out.println("3 - Müşteri Ekle");
            System.out.println("4 - Müşteri Listele");
            System.out.println("5 - Rezervasyon Yap");
            System.out.println("6 - Rezervasyon Listele");
            System.out.println("7 - Check-in");
            System.out.println("8 - Check-out");
            System.out.println("0 - Çıkış");
            System.out.print("Seçiminiz: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    listRooms();
                    break;
                case 3:
                    addCustomer();
                    break;
                case 4:
                    listCustomers();
                    break;
                case 5:
                    createReservation();
                    break;
                case 6:
                    listReservations();
                    break;
                case 7:
                    checkIn();
                    break;
                case 8:
                    checkOut();
                    break;
                case 0:
                    System.out.println("Sistem kapatılıyor...");
                    break;
                default:
                    System.out.println("Geçersiz seçim!");
                    break;
            }

        } while (choice != 0);
    }

    // ===========================
    // ODA EKLE
    // ===========================
    private void addRoom() {
        System.out.print("Oda tipi: ");
        String type = scanner.nextLine();

        System.out.print("Kapasite: ");
        int capacity = scanner.nextInt();

        System.out.print("Fiyat: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        int id = fileManager.generateId("rooms.txt");

        Room room = new Room(id, type, capacity, price, "Boş");
        fileManager.writeRoom(room);

        System.out.println("Oda başarıyla eklendi!");
    }

    // ===========================
    // ODA LİSTELE
    // ===========================
    private void listRooms() {
        List<Room> rooms = fileManager.readRooms();
        rooms.forEach(System.out::println);
    }

    // ===========================
    // MÜŞTERİ EKLE
    // ===========================
    private void addCustomer() {
        System.out.print("Ad: ");
        String name = scanner.nextLine();

        System.out.print("Soyad: ");
        String surname = scanner.nextLine();

        System.out.print("Telefon: ");
        String phone = scanner.nextLine();

        int id = fileManager.generateId("customers.txt");

        Customer customer = new Customer(id, name, surname, phone);
        fileManager.writeCustomer(customer);

        System.out.println("Müşteri başarıyla eklendi!");
    }

    // ===========================
    // MÜŞTERİ LİSTELE
    // ===========================
    private void listCustomers() {
        List<Customer> customers = fileManager.readCustomers();
        customers.forEach(System.out::println);
    }

    // ===========================
    // REZERVASYON OLUŞTUR
    // ===========================
    private void createReservation() {

        System.out.print("Müşteri ID: ");
        int customerId = scanner.nextInt();

        System.out.print("Oda ID: ");
        int roomId = scanner.nextInt();

        System.out.print("Giriş tarihi (YYYY-MM-DD): ");
        LocalDate checkIn = LocalDate.parse(scanner.next());

        System.out.print("Çıkış tarihi (YYYY-MM-DD): ");
        LocalDate checkOut = LocalDate.parse(scanner.next());

        System.out.print("Oda fiyatı: ");
        double price = scanner.nextDouble();

        Reservation r = reservationService.createReservation(
                customerId, roomId, checkIn, checkOut, price
        );

        if (r != null) {
            System.out.println("Rezervasyon başarıyla oluşturuldu!");
            System.out.println(r);
        }
    }

    // ===========================
    // REZERVASYON LİSTELE
    // ===========================
    private void listReservations() {
        List<Reservation> reservations = fileManager.readReservations();
        reservations.forEach(System.out::println);
    }

    // ===========================
    // CHECK-IN
    // ===========================
    private void checkIn() {
        System.out.print("Rezervasyon ID: ");
        int id = scanner.nextInt();

        reservationService.checkIn(id);
    }

    // ===========================
    // CHECK-OUT
    // ===========================
    private void checkOut() {
        System.out.print("Rezervasyon ID: ");
        int id = scanner.nextInt();

        reservationService.checkOut(id);
    }
}
