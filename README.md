# 🏨 Hotel Management System (Java CLI)

![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=java) ![Architecture](https://img.shields.io/badge/Architecture-Layered-blueviolet?style=for-the-badge) ![Status](https://img.shields.io/badge/Status-Completed-success?style=for-the-badge)

> **"No more spaghetti code."**

Bir otelin günlük operasyonlarını (Reservation, Check-in/Out, Room Management) **Komut Satırı (CLI)** üzerinden yöneten, **Nesne Yönelimli (OOP)** ve **Katmanlı Mimari** ile geliştirilmiş "rock-solid" bir otomasyon projesi.

---

## 🔥 Why This Project? (Neden Bu Proje?)

Bu proje, otellerdeki kağıt-kalem karmaşasını bitirip, resepsiyonistlere "Next-Next" kolaylığında bir deneyim sunuyor.

* **Zero Database Overhead:** Veritabanı kurulumuyla uğraşmak yok. Her şey `TXT` dosyalarında (File I/O) güvenle saklanır.
* **Pure Java:** 3. parti kütüphane bağımlılığı yok. Saf ve güçlü Java mantığı.
* **Crash-Proof:** Hatalı girişlere (Exception Handling) karşı tam korumalı.

---

## 🚀 Features (Özellikler)

Sistem, gerçek bir otel senaryosunu simüle eder:

### 🛏️ Room Management
* **Add Room:** Sisteme yeni oda tanımla (Single, Double, Suite).
* **List Rooms:** Tüm envanteri ve durumlarını (Available, Occupied) anlık gör.

### 👥 Customer Operations
* **Smart Registration:** Müşteri kaydı oluştur ve ID ile saniyeler içinde bul.
* **Validation:** Eksik veya hatalı veri girişini engeller.

### 📅 Reservation System (The Core)
* **Conflict Detection:** Çakışan tarihleri otomatik algılar (No double-booking!).
* **Auto-Pricing:** Gün sayısına ve oda tipine göre fiyatı otomatik hesaplar.
* **Availability Check:** Sadece uygun odaları önerir.

### 🔑 Check-in & Check-out Flow
* **Check-in:** Misafir geldiğinde odayı `OCCUPIED` moduna çeker.
* **Check-out:** Çıkışta odayı temizler ve `AVAILABLE` moduna döndürür.

---

## 🛠️ Under the Hood (Teknik Altyapı)

Proje, spagetti koddan uzak, modüler bir yapıda tasarlandı:

* **Language:** Java 17+
* **Architecture:** Layered (Controller -> Service -> File Manager)
* **Data Persistence:** File I/O (`rooms.txt`, `customers.txt`, `reservations.txt`)
* **Design:** OOP Principles & SOLID

### 📂 Project Structure
```text
OTELYONETIMSISTEMI/
├── src/
│   ├── Main.java                # 🏁 App Entry Point (Uygulamanın Başlangıcı)
│   ├── MenuController.java      # 🎮 UI & Menü Yönetimi (Kullanıcıyla konuşan kısım)
│   ├── FileManager.java         # 💾 File I/O (TXT Okuma/Yazma işlemleri)
│   ├── ReservationService.java  # 🧠 Business Logic (Çakışma kontrolü, hesaplama)
│   ├── Room.java                # 🏠 Oda Varlığı (Entity)
│   ├── Customer.java            # 👤 Müşteri Varlığı (Entity)
│   └── Reservation.java         # 📅 Rezervasyon Varlığı (Entity)
├── rooms.txt                    # 📄 Oda Veritabanı
├── customers.txt                # 📄 Müşteri Veritabanı
├── reservations.txt             # 📄 Rezervasyon Geçmişi
└── README.md                    # 📖 Proje Dokümantasyonu
