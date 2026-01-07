public class Room {
    //UML'de private değişkenler
    private int odaId;
    private String tip;
    private int kapasite;
    private double fiyat;
    private String durum;

    // Kurucu Metot (Constructor) - Yeni oda eklerken kullanılır
    // Varsayılan olarak oda durumu "BOŞ" atanır.
    public Room(int odaId, String tip, int kapasite, double fiyat) {
        this.odaId = odaId;
        this.tip = tip;
        this.kapasite = kapasite;
        this.fiyat = fiyat;
        this.durum = "BOŞ"; // Başlangıçta oda boştur
    }

    // Dosyadan okurken kullanılacak Kurucu Metot (Durum dahil)
    public Room(int odaId, String tip, int kapasite, double fiyat, String durum) {
        this.odaId = odaId;
        this.tip = tip;
        this.kapasite = kapasite;
        this.fiyat = fiyat;
        this.durum = durum;
    }

    // --- UML Diyagramındaki Metotlar ---

    // +getRoomId() : int
    public int getRoomId() {
        return odaId;
    }

    // +getStatus() : String
    public String getStatus() {
        return durum;
    }

    // +setStatus(durum : String)
    // Check-in (FR4.1) ve Check-out (FR4.2) işlemlerinde durumu güncellemek için kullanılır
    public void setStatus(String durum) {
        this.durum = durum;
    }

    // +toFileString() : String
    // Verileri rooms.txt dosyasına yazmak için CSV formatına çevirir
    public String toFileString() {
        // Örnek çıktı: 101,Tek Kişilik,2,500.0,BOŞ
        return odaId + "," + tip + "," + kapasite + "," + fiyat + "," + durum;
    }

    // --- Diğer Gerekli Getter Metotları ---
    // (Oda Listeleme (UC2) işlemi için bu bilgilere erişim gereklidir )

    public String getTip() {
        return tip;
    }

    public int getKapasite() {
        return kapasite;
    }

    public double getFiyat() {
        return fiyat;
    }

   // wrıtıng on screen
    @Override
    public String toString() {
        return "Oda No: " + odaId + " | Tip: " + tip + " | Kapasite: " + kapasite +
                " | Fiyat: " + fiyat + " TL | Durum: " + durum;
    }
}
