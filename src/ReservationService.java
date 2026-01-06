import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class ReservationService{
    // UML diyagramındaki private değişken
    private FileManager fileManager;

    // Kurucu Metot (Constructor)
    public ReservationService(FileManager fileManager) {
       // super(ad,soyad,musteriId,telefon);
        this.fileManager = fileManager;
    }

    // +createReservation(...) : void
    // UC5 - Rezervasyon Yap
    public void createReservation(int musteriId, int odaId, LocalDate giris, LocalDate cikis) {
        // 1. Tarih Geçerlilik Kontrolü
<<<<<<< HEAD
        if (giris.isAfter(cikis) || giris.isEqual(cikis) || giris.isBefore(LocalDate.now())) {
=======
        if (giris.isAfter(cikis) || giris.isEqual(cikis) ||/* giris.isBefore(LocalDate.now())*/) {// Bu değişiklik sayesinde sisteme "Giriş: 2023-01-01" gibi eski bir tarih girseniz bile sistem kabul edecektir.
>>>>>>> aeb9ea05ed501d29fc2eca5e9539c65bfcc95f4f
            System.out.println("Hata: Tarihler geçersiz. Giriş tarihi bugünden önce olamaz ve çıkış tarihinden önce olmalıdır.");
            return;
        }

        // 2. Müşteri Kontrolü
        List<Customer> musteriler = fileManager.readCustomers();
        boolean musteriVar = musteriler.stream().anyMatch(m -> m.getCustomerId() == musteriId);
        if (!musteriVar) {
            System.out.println("Hata: Müşteri bulunamadı.");
            return;
        }

        // 3. Oda ve Uygunluk Kontrolü
        List<Room> odalar = fileManager.readRooms();
        Room secilenOda = odalar.stream().filter(r -> r.getRoomId() == odaId).findFirst().orElse(null);

        if (secilenOda == null) {
            System.out.println("Hata: Oda bulunamadı.");
            return;
        }

        // Çakışan rezervasyon engelleme (FR3.3)
        if (!isDateAvailable(odaId, giris, cikis)) {
            System.out.println("Hata: Oda belirtilen tarihlerde müsait değil.");
            return;
        }

        // 4. Fiyat Hesaplama ve Kayıt (FR3.4)
        long gunSayisi = ChronoUnit.DAYS.between(giris, cikis);
        double toplamUcret = calculatePrice(secilenOda, (int) gunSayisi);
        
        int yeniRezId = fileManager.generateId("reservations.txt");
        
        Reservation yeniRez = new Reservation(yeniRezId, musteriId, odaId, giris, cikis, toplamUcret);
        fileManager.writeReservation(yeniRez);
        
        System.out.println("Başarılı: Rezervasyon oluşturuldu. ID: " + yeniRezId + ", Tutar: " + toplamUcret + " TL");
    }

    // +isDateAvailable(...) : boolean
    // FR3.3 - Çakışan rezervasyon kontrolü
    public boolean isDateAvailable(int odaId, LocalDate giris, LocalDate cikis) {
        List<Reservation> rezervasyonlar = fileManager.readReservations();
        
        for (Reservation r : rezervasyonlar) {
            // Sadece ilgili odaya ait ve "Tamamlandı/İptal" olmayan kayıtları kontrol et
            if (r.getOdaId() == odaId && !r.getDurum().equals("TAMAMLANDI")) {
                // Tarih çakışma mantığı:
                // (TalepGiris < MevcutCikis) VE (TalepCikis > MevcutGiris)
                if (giris.isBefore(r.getCikisTarihi()) && cikis.isAfter(r.getGirisTarihi())) {
                    return false; // Çakışma var
                }
            }
        }
        return true; // Müsait
    }

    // +checkIn(...) : void
    // UC7 - Check-in İşlemi
    public void checkIn(int rezId) {
        List<Reservation> rezervasyonlar = fileManager.readReservations();
        List<Room> odalar = fileManager.readRooms();

        // Rezervasyonu bul
        Reservation rez = rezervasyonlar.stream().filter(r -> r.getReservationId() == rezId).findFirst().orElse(null);
        if (rez == null) {
            System.out.println("Hata: Rezervasyon bulunamadı.");
            return;
        }

        // Tarih Kontrolü (Bugün giriş günü mü?)
<<<<<<< HEAD
        if (!rez.getGirisTarihi().equals(LocalDate.now())) {
            System.out.println("Hata: Giriş tarihi bugün değil (" + rez.getGirisTarihi() + ").");
            return;
        }

=======
     //   if (!rez.getGirisTarihi().equals(LocalDate.now())) {
    //        System.out.println("Hata: Giriş tarihi bugün değil (" + rez.getGirisTarihi() + ").");
    //        return;
     //   }
/* 
 * Bu değişiklik sayesinde, giriş tarihi geçen hafta olan bir rezervasyon için bile "Check-in" diyerek odayı anında "DOLU" yapariz.
 * */
>>>>>>> aeb9ea05ed501d29fc2eca5e9539c65bfcc95f4f
        // Oda Durumu Kontrolü
        Room oda = odalar.stream().filter(r -> r.getRoomId() == rez.getOdaId()).findFirst().orElse(null);
        if (oda != null && !oda.getStatus().equals("BOŞ")) {
            System.out.println("Hata: Oda şu an dolu veya temizlenmemiş.");
            return;
        }

        // İşlem Başarılı: Güncellemeleri yap
        if (oda != null) {
            oda.setStatus("DOLU"); // FR4.1
            rez.setDurum("AKTIF"); // Rezervasyon artık aktif konaklama oldu
            
            // Dosyaları güncelle (Tüm listeyi yeniden yazarak)
            fileManager.updateAllRooms(odalar);
            fileManager.updateAllReservations(rezervasyonlar);
            
            System.out.println("Check-in işlemi tamamlandı. Oda durumu 'DOLU' yapıldı.");
        }
    }

    // +checkOut(...) : void
    // UC8 - Check-out İşlemi
    public void checkOut(int rezId) {
        List<Reservation> rezervasyonlar = fileManager.readReservations();
        List<Room> odalar = fileManager.readRooms();

        // Aktif Rezervasyonu bul
        Reservation rez = rezervasyonlar.stream().filter(r -> r.getReservationId() == rezId).findFirst().orElse(null);
        
        if (rez == null) {
            System.out.println("Hata: Rezervasyon bulunamadı."); 
            return;
        }
        
        // Sadece AKTIF (Check-in yapılmış) rezervasyonlar check-out olabilir [cite: 228]
        if (!rez.getDurum().equals("AKTIF")) {
            System.out.println("Hata: Bu rezervasyon için henüz check-in yapılmamış veya işlem zaten bitmiş.");
            return;
        }

        // Oda Durumu Kontrolü
        Room oda = odalar.stream().filter(r -> r.getRoomId() == rez.getOdaId()).findFirst().orElse(null);
        if (oda == null) {
             System.out.println("Hata: Oda verisine ulaşılamadı.");
             return;
        }

        // İşlem Başarılı: Güncellemeleri yap
        oda.setStatus("BOŞ"); // FR4.2
        rez.setDurum("TAMAMLANDI"); // İşlem bitti
        
        // Dosyaları güncelle
        fileManager.updateAllRooms(odalar);
        fileManager.updateAllReservations(rezervasyonlar);
        
        System.out.println("Check-out tamamlandı. Oda 'BOŞ', Rezervasyon 'TAMAMLANDI'.");
    }

    // +calculatePrice(...) : double
    // FR3.4 - Otomatik ücret hesaplama
    public double calculatePrice(Room oda, int gun) {
        if (gun < 1) gun = 1; // En az 1 gün
        return oda.getFiyat() * gun;
    }
}
