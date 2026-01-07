import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
//    import java.io.*;
//import java.util.*;
//import java.time.LocalDate;

   // public class FileManager {
        // UML diyagramındaki private dosya yolu değişkenleri
        private String odaDosyasi = "rooms.txt";
        private String musteriDosyasi = "customers.txt";
        private String rezervasyonDosyasi = "reservations.txt";

        // --- ODA İŞLEMLERİ (Room) ---

        // +readRooms() : List<Room>
        // UC2 - Oda Listeleme için verileri okur
        public List<Room> readRooms() {
            List<Room> odalar = new ArrayList<>();
            File file = new File(odaDosyasi);

            if (!file.exists()) return odalar; // Dosya yoksa boş liste dön

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    // Format: id,tip,kapasite,fiyat,durum
                    if (data.length >= 5) {
                        int id = Integer.parseInt(data[0]);
                        String tip = data[1];
                        int kap = Integer.parseInt(data[2]);// kap->kapasite
                        double fiyat = Double.parseDouble(data[3]);
                        String durum = data[4];

                        odalar.add(new Room(id, tip, kap, fiyat, durum));
                    }
                }
            } catch (IOException e) {
                System.out.println("Dosya okuma hatası: " + e.getMessage());
            }
            return odalar;
        }

        // +writeRoom(oda : Room)
        // UC1 - Yeni oda ekler (Append mode)
        public void writeRoom(Room oda) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(odaDosyasi, true))) {
                bw.write(oda.toFileString());
                bw.newLine();
            } catch (IOException e) {
                System.out.println("Dosya yazma hatası: " + e.getMessage());
            }
        }

        // --- MÜŞTERİ İŞLEMLERİ (Customer) ---

        // +readCustomers() : List<Customer>
        // UC4 - Müşteri Listeleme için verileri okur
        public List<Customer> readCustomers() {
            List<Customer> musteriler = new ArrayList<>();
            File file = new File(musteriDosyasi);

            if (!file.exists()) return musteriler;

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    // Format: id,ad,soyad,telefon
                    if (data.length >= 4) {
                        int id = Integer.parseInt(data[0]);
                        String ad = data[1];
                        String soyad = data[2];
                        String tel = data[3];

                        musteriler.add(new Customer(id, ad, soyad, tel));
                    }
                }
            } catch (IOException e) {
                System.out.println("Dosya okuma hatası: " + e.getMessage());
            }
            return musteriler;
        }

        // +writeCustomer(musteri : Customer)
        // UC3 - Yeni müşteri ekler
        public void writeCustomer(Customer musteri) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(musteriDosyasi, true))) {
                bw.write(musteri.toFileString());
                bw.newLine();
            } catch (IOException e) {
                System.out.println("Dosya yazma hatası: " + e.getMessage());
            }
        }

        // --- REZERVASYON İŞLEMLERİ (Reservation) ---

        // +readReservations() : List<Reservation>
        // UC6 - Rezervasyon Listeleme
        public List<Reservation> readReservations() {
            List<Reservation> rezervasyonlar = new ArrayList<>();
            File file = new File(rezervasyonDosyasi);

            if (!file.exists()) return rezervasyonlar;

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    // Format: rezId,musId,odaId,giris,cikis,ucret,durum
                    if (data.length >= 7) {
                        int rId = Integer.parseInt(data[0]);
                        int mId = Integer.parseInt(data[1]);
                        int oId = Integer.parseInt(data[2]);
                        LocalDate giris = LocalDate.parse(data[3]);
                        LocalDate cikis = LocalDate.parse(data[4]);
                        double ucret = Double.parseDouble(data[5]);
                        String durum = data[6];

                        rezervasyonlar.add(new Reservation(rId, mId, oId, giris, cikis, ucret, durum));
                    }
                }
            } catch (IOException e) {
                System.out.println("Dosya okuma hatası: " + e.getMessage());
            }
            return rezervasyonlar;
        }

        // +writeReservation(rez : Reservation)
        // UC5 - Yeni rezervasyon ekler
        public void writeReservation(Reservation rez) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(rezervasyonDosyasi, true))) {
                //true->her yeni kayıt eklediğinde eski kayıtların hepsi silinir!
                bw.write(rez.toFileString());
                bw.newLine();
            } catch (IOException e) {
                System.out.println("Dosya yazma hatası: " + e.getMessage());
            }
        }



        // +generateId(dosyaYolu : String) : int
        // Otomatik artan(Auto-Increment) ID üretir (UC1, UC3, UC5 için gereklidir)
        public int generateId(String dosyaYolu) {
            int maxId = 0;
            File file = new File(dosyaYolu);
            if (dosyaYolu.equals(odaDosyasi)) {
                maxId = 100;// Odalar 101, 102...
            }else if(dosyaYolu.equals(rezervasyonDosyasi ) ){
                maxId = 5000;// Rezervasyonlar 5001, 5002...
            }

            if (!file.exists()) return maxId +1; // Dosya henüz yoksa; oda için 101, diğerleri için 1 döner

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length > 0) {
                        try {
                            int currentId = Integer.parseInt(data[0]);
                            if (currentId > maxId) {
                                maxId = currentId;
                            }
                        } catch (NumberFormatException e) {
                            // Header veya bozuk satır varsa atla, yani hata varsa atla
                        }
                    }
                }
            } catch (IOException e) {
                return maxId +1;
            }
            return maxId + 1;
        }

        // --- GÜNCELLEME İÇİN EK METOTLAR (Check-in/Check-out Gereksinimi) ---
        // Not: UML diyagramında sadece 'write' (ekleme) var ancak Check-in (FR4.1) ve
        // Check-out (FR4.2) sırasında dosyadaki veriyi GÜNCELLEMEK (Overwrite) gerekir.
        // Bu metotlar, dosyanın tamamını güncel liste ile yeniden yazar.

        public void updateAllRooms(List<Room> odalar) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(odaDosyasi, false))) { // false = overwrite
                for (Room oda : odalar) {
                    bw.write(oda.toFileString());//liste bastan sona tekarar yaz
                    bw.newLine();
                }
            } catch (IOException e) {
                System.out.println("Oda güncelleme hatası: " + e.getMessage());
            }
        }

        public void updateAllReservations(List<Reservation> rezervasyonlar) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(rezervasyonDosyasi, false))) {
                for (Reservation rez : rezervasyonlar) {
                    bw.write(rez.toFileString());
                    bw.newLine();
                }
            } catch (IOException e) {
                System.out.println("Rezervasyon güncelleme hatası: " + e.getMessage());
            }
        }
    }


