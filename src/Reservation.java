import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    // UML diyagramında belirtilen private değişkenler
    private int rezervasyonId;
    private int musteriId;
    private int odaId;
    private LocalDate girisTarihi;
    private LocalDate cikisTarihi;
    private double toplamUcret;
    private String durum; // Örn: "REZERVE", "AKTIF" (Check-in sonrası), "TAMAMLANDI"

    // Kurucu Metot (Constructor) - Yeni rezervasyon oluştururken
<<<<<<< HEAD
    public Reservation(int rezervasyonId, int musteriId, int odaId, LocalDate girisTarihi, LocalDate cikisTarihi,
            double toplamUcret) {
=======
    public Reservation(int rezervasyonId, int musteriId, int odaId, LocalDate girisTarihi, LocalDate cikisTarihi, double toplamUcret) {
>>>>>>> aeb9ea05ed501d29fc2eca5e9539c65bfcc95f4f
        this.rezervasyonId = rezervasyonId;
        this.musteriId = musteriId;
        this.odaId = odaId;
        this.girisTarihi = girisTarihi;
        this.cikisTarihi = cikisTarihi;
        this.toplamUcret = toplamUcret;
        this.durum = "REZERVE"; // Varsayılan başlangıç durumu
    }

    // Dosyadan okurken kullanılacak Kurucu Metot (Durum bilgisi dahil)
<<<<<<< HEAD
    public Reservation(int rezervasyonId, int musteriId, int odaId, LocalDate girisTarihi, LocalDate cikisTarihi,
            double toplamUcret, String durum) {
=======
    public Reservation(int rezervasyonId, int musteriId, int odaId, LocalDate girisTarihi, LocalDate cikisTarihi, double toplamUcret, String durum) {
>>>>>>> aeb9ea05ed501d29fc2eca5e9539c65bfcc95f4f
        this.rezervasyonId = rezervasyonId;
        this.musteriId = musteriId;
        this.odaId = odaId;
        this.girisTarihi = girisTarihi;
        this.cikisTarihi = cikisTarihi;
        this.toplamUcret = toplamUcret;
        this.durum = durum;
    }

    // --- UML Diyagramındaki Metotlar ---

    // +getReservationId() : int
    public int getReservationId() {
        return rezervasyonId;
    }

    // +calculatePrice() : double
    // UML'de bu metod Reservation sınıfında görünmektedir.
<<<<<<< HEAD
    // Sınıf içinde birim fiyat tutulmadığı için bu metod mevcut toplam ücreti
    // döner.
    // (Not: Asıl hesaplama ReservationService içindeki calculatePrice metodunda
    // yapılır.)
=======
    // Sınıf içinde birim fiyat tutulmadığı için bu metod mevcut toplam ücreti döner.
    // (Not: Asıl hesaplama ReservationService içindeki calculatePrice metodunda yapılır.)
>>>>>>> aeb9ea05ed501d29fc2eca5e9539c65bfcc95f4f
    public double calculatePrice() {
        return toplamUcret;
    }

    // +toFileString() : String
    // Verileri reservations.txt dosyasına yazmak için CSV formatına çevirir.
<<<<<<< HEAD

    // Örnek: 1001,101,201,2023-10-01,2023-10-05,2000.0,REZERVE
    // Bu metot verileri dosyaya "1,1,1,2025-12-24..." formatında yazar.
    public String toFileString() {
        return rezervasyonId + "," +
                musteriId + "," +
                odaId + "," +
                girisTarihi + "," +
                cikisTarihi + "," +
                toplamUcret + "," +
                durum;
=======
    public String toFileString() {
        // Örnek: 1001,101,201,2023-10-01,2023-10-05,2000.0,REZERVE
        return "1.Reservation:"+rezervasyonId + "," + "MusteriId:"+musteriId + ","+"OdaId:" + odaId + "," +
               "\n"+"girisTarihi:"+girisTarihi + ","+"cikisTarih:" + cikisTarihi +","+"\n"+ "toplamUcreti:"+","  + toplamUcret + "durum:"+ "," + durum;
>>>>>>> aeb9ea05ed501d29fc2eca5e9539c65bfcc95f4f
    }

    // --- Getter ve Setter Metotları ---
    // (Check-in, Check-out ve Listeleme işlemleri için gereklidir)

    public int getMusteriId() {
        return musteriId;
    }

    public int getOdaId() {
        return odaId;
    }

    public LocalDate getGirisTarihi() {
        return girisTarihi;
    }

    public LocalDate getCikisTarihi() {
        return cikisTarihi;
    }
<<<<<<< HEAD

=======
    
>>>>>>> aeb9ea05ed501d29fc2eca5e9539c65bfcc95f4f
    public String getDurum() {
        return durum;
    }

<<<<<<< HEAD
    // Check-in (UC7) ve Check-out (UC8) sırasında durum güncellemek için (FR4.1,
    // FR4.2)
=======
    // Check-in (UC7) ve Check-out (UC8) sırasında durum güncellemek için (FR4.1, FR4.2)
>>>>>>> aeb9ea05ed501d29fc2eca5e9539c65bfcc95f4f
    public void setDurum(String durum) {
        this.durum = durum;
    }

    // Listeleme (UC6) sırasında ekrana düzgün yazdırmak için toString
    @Override
    public String toString() {
<<<<<<< HEAD
        return "Rez No: " + rezervasyonId +
                " | Müşteri ID: " + musteriId +
                " | Oda ID: " + odaId +
                " | Giriş: " + girisTarihi +
                " | Çıkış: " + cikisTarihi +
                " | Ücret: " + toplamUcret + " TL" +
                " | Durum: " + durum;
=======
        return "Rez No: " + rezervasyonId + 
               " | Müşteri ID: " + musteriId + 
               " | Oda ID: " + odaId + 
               " | Giriş: " + girisTarihi + 
               " | Çıkış: " + cikisTarihi + 
               " | Ücret: " + toplamUcret + " TL" +
               " | Durum: " + durum;
>>>>>>> aeb9ea05ed501d29fc2eca5e9539c65bfcc95f4f
    }
}
