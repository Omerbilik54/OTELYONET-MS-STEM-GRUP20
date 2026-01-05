import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class MenuController {
    //  public class MenuController {
        // UML diyagramında belirtilen private değişkenler
        private ReservationService reservationService;
        private FileManager fileManager;
        private  Scanner scanner;

        // Kurucu Metot (Constructor)
        public MenuController() {
            this.scanner = new Scanner(System.in);
            this.fileManager = new FileManager(); // Veri yönetimi başlatılır
            this.reservationService = new ReservationService(fileManager); // Servis başlatılır
        }

        // +calistir() : void
        // Uygulamanın ana döngüsüdür (FR5.1, UC9)
        public void calistir() {
            System.out.println("=== OTEL YÖNETİM SİSTEMİNE HOŞ GELDİNİZ ===");
            boolean calisiyor = true;

            while (calisiyor) {
                menuGoster();
                System.out.print("Seçiminiz: ");
                int secim = -1;

                try {
                    secim = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Hata: Lütfen sayısal bir değer giriniz.");
                    continue;
                }

                switch (secim) {
                    case 1: // Oda Ekle
                    case 2: // Oda Listele
                        odalslemleri(secim);
                        break;
                    case 3: // Müşteri Ekle
                    case 4: // Müşteri Listele
                        musterilslemleri(secim);
                        break;
                    case 5: // Rezervasyon Yap
                    case 6: // Rezervasyon Listele
                    case 7: // Check-in
                    case 8: // Check-out
                        rezervasyonIslemleri(secim);
                        break;
                    case 9: // Çıkış
                        System.out.print("Çıkmak istediğinize emin misiniz? (E/H): ");
                        String onay = scanner.nextLine();
                        if (onay.equalsIgnoreCase("E")) {
                            System.out.println("Sistem kapatılıyor. İyi günler!");
                            calisiyor = false;
                        }
                        break;
                    default:
                        System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
                }
                System.out.println("--------------------------------------------------");
            }
            scanner.close();
        }

        // +menuGoster() : void
        // Kullanıcıya tüm seçenekleri sunar (FR5.1)
        public void menuGoster() {
            System.out.println("\n--- ANA MENÜ ---");
            System.out.println("1. Oda Ekle");
            System.out.println("2. Oda Listele");
            System.out.println("3. Müşteri Ekle");
            System.out.println("4. Müşteri Listele");
            System.out.println("5. Rezervasyon Yap");
            System.out.println("6. Rezervasyon Listele");
            System.out.println("7. Check-in (Giriş)");
            System.out.println("8. Check-out (Çıkış)");
            System.out.println("9. Çıkış");
        }

        // +odalslemleri() : void
        // Not: UML'de parametresiz görünse de, düz menü yapısında (1-9) seçimi işlemek için
        // parametre alacak şekilde veya iç mantıkla kullanıyoruz.
        public void odalslemleri(int secim) {
            if (secim == 1) { // UC1 - Oda Ekle
                System.out.println("\n[Oda Ekleme]");
                System.out.print("Oda Tipi (Örn: Single, Double): ");
                String tip = scanner.nextLine();

                System.out.print("Kapasite: ");
                int kapasite = Integer.parseInt(scanner.nextLine());

                System.out.print("Fiyat: ");
                double fiyat = Double.parseDouble(scanner.nextLine());

                // Yeni ID üretmek için mevcut listeyi kontrol etmemiz gerekebilir
                // Basitlik adına MenuController'da generateId olduğunu varsayıyoruz veya list size + 1
                int yeniId = fileManager.generateId("rooms.txt");

                Room yeniOda = new Room(yeniId, tip, kapasite, fiyat);
                fileManager.writeRoom(yeniOda);
                System.out.println("Oda başarıyla eklendi. ID: " + yeniId);

            } else if (secim == 2) { // UC2 - Oda Listele [cite: 74-90]
                System.out.println("\n[Oda Listesi]");
                List<Room> odalar = fileManager.readRooms();
                if (odalar.isEmpty()) {
                    System.out.println("Listelenecek oda bulunamadı.");
                } else {
                    for (Room oda : odalar) {
                        System.out.println(oda);
                    }
                }
            }
        }

        // +musterilslemleri() : void
        public void musterilslemleri(int secim) {
            if (secim == 3) { // UC3 - Müşteri Ekle [cite: 91-106]
                System.out.println("\n[Müşteri Ekleme]");
                System.out.print("Ad: ");
                String ad = scanner.nextLine();
                System.out.print("Soyad: ");
                String soyad = scanner.nextLine();
                System.out.print("Telefon: ");
                String tel = scanner.nextLine();

                int yeniId = fileManager.generateId("customers.txt");
                Customer yeniMusteri = new Customer(yeniId, ad, soyad, tel);
                fileManager.writeCustomer(yeniMusteri);
                System.out.println("Müşteri başarıyla eklendi. ID: " + yeniId);

            } else if (secim == 4) { // UC4 - Müşteri Listele [cite: 107-117]
                System.out.println("\n[Müşteri Listesi]");
                List<Customer> musteriler = fileManager.readCustomers();
                if (musteriler.isEmpty()) {
                    System.out.println("Kayıtlı müşteri bulunamadı.");
                } else {
                    for (Customer m : musteriler) {
                        System.out.println(m);
                    }
                }
            }
        }

        // +rezervasyonIslemleri() : void
        public void rezervasyonIslemleri(int secim) {
            try {
                switch (secim) {
                    case 5: // UC5 - Rezervasyon Yap
                        System.out.println("\n[Rezervasyon Oluştur]");
                        System.out.print("Müşteri ID: ");
                        int musId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Oda ID: ");
                        int odaId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Giriş Tarihi (YYYY-MM-DD): ");
                        LocalDate giris = LocalDate.parse(scanner.nextLine());
                        System.out.print("Çıkış Tarihi (YYYY-MM-DD): ");
                        LocalDate cikis = LocalDate.parse(scanner.nextLine());

                        // İş mantığı ReservationService üzerinden yürütülür
                        reservationService.createReservation(musId, odaId, giris, cikis);
                        break;

                    case 6: // UC6 - Rezervasyon Listele
                        System.out.println("\n[Rezervasyon Listesi]");
                        List<Reservation> rezervasyonlar = fileManager.readReservations();
                        if (rezervasyonlar.isEmpty()) {
                            System.out.println("Kayıtlı rezervasyon yok.");
                        } else {
                            for (Reservation r : rezervasyonlar) {
                                System.out.println(r);
                            }
                        }
                        break;

                    case 7: // UC7 - Check-in
                        System.out.println("\n[Check-in İşlemi]");
                        System.out.print("Check-in yapılacak Rezervasyon ID: ");
                        int checkInId = Integer.parseInt(scanner.nextLine());
                        reservationService.checkIn(checkInId);
                        break;

                    case 8: // UC8 - Check-out
                        System.out.println("\n[Check-out İşlemi]");
                        System.out.print("Check-out yapılacak Rezervasyon ID: ");
                        int checkOutId = Integer.parseInt(scanner.nextLine());
                        reservationService.checkOut(checkOutId);
                        break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Hata: Tarih formatı geçersiz. (YYYY-MM-DD) kullanın.");
            } catch (Exception e) {
                System.out.println("Bir hata oluştu: " + e.getMessage());
            }
        }
    }


