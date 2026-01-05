public class Customer {

   // public class Customer {
        // private değişkenler
        private int musteriId;
        private String ad;
        private String soyad;
        private String telefon;

        // Kurucu Metot (Constructor)
        // Yeni müşteri oluşturulurken veya dosyadan veri okunurken kullanılır
        public Customer(int musteriId, String ad, String soyad, String telefon) {
            this.musteriId = musteriId;
            this.ad = ad;
            this.soyad = soyad;
            this.telefon = telefon;
        }

        // --- UML Diyagramındaki Metotlar ---

        // +getCustomerId() : int
        public int getCustomerId() {
            return musteriId;
        }

        // +toFileString() : String
        // Verileri customers.txt dosyasına yazmak için CSV formatına çevirir.
        public String toFileString() {
            // Örnek çıktı: 101,Ahmet,Yılmaz,5551234567
            return musteriId + "," + ad + "," + soyad + "," + telefon;
        }

        // --- Diğer Gerekli Getter Metotları ---
        // (Müşteri Listeleme (UC4) ve doğrulama işlemleri için gereklidir)

        public String getAd() {
            return ad;
        }

        public String getSoyad() {
            return soyad;
        }

        public String getTelefon() {
            return telefon;
        }

        // Ekranda listeleme yaparken (UC4) düzgün görünmesi için toString metodu
        @Override
        public String toString() {
            return "ID: " + musteriId + " | Ad Soyad: " + ad + " " + soyad + " | Tel: " + telefon;
        }
}


