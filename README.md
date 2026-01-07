# 🏨 Hotel Management System (Java CLI)

![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=java) ![Architecture](https://img.shields.io/badge/Architecture-Layered-blueviolet?style=for-the-badge) ![Status](https://img.shields.io/badge/Status-Completed-success?style=for-the-badge)

### 📊 Project Statistics

![Top Langs](https://github-readme-stats.vercel.app/api/top-langs/?username=Omerbilik54&layout=compact&theme=dracula)

![Repo Cards](https://github-readme-stats.vercel.app/api/pin/?username=Omerbilik54&repo=OTELYONET-MS-STEM-GRUP20&theme=dracula)

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


### 📂 Project Structure (Proje Yapısı)

```text
OTELYONET-MS-STEM-GRUP20/
│
├── 1. src/                      # 💻 Kaynak Kodlar (Source Code)
│   ├── Main.java                # 🏁 Uygulamanın Başlangıç Noktası
│   ├── MenuController.java      # 🎮 Arayüz ve Menü Yönetimi
│   ├── ReservationService.java  # 🧠 İş Mantığı ve Kontroller
│   ├── FileManager.java         # 💾 Dosya Okuma/Yazma (I/O)
│   ├── Room.java                # 🏠 Oda Varlığı (Entity)
│   ├── Customer.java            # 👤 Müşteri Varlığı (Entity)
│   └── Reservation.java         # 📅 Rezervasyon Varlığı (Entity)
│
├── 2. Reports/                  # 📊 Proje Raporları (Documentation)
│   ├── Analiz Raporu Son.pdf    # Gereksinimler ve Use-Case Analizi
│   ├── Tasarım Raporu.pdf       # Mimari ve Sınıf Tasarımları
│   └── Final report.pdf         # Proje Sonuç ve Değerlendirme Raporu
│
├── 3. sinif acikalamalari/      # 📝 Kod Dokümantasyonu (Class Descriptions)
│   ├── file_manager.txt         # FileManager sınıfının çalışma mantığı
│   ├── reservasyon_aciklama.txt # Reservation (Entity) sınıfı açıklamaları
│   ├── reserv_aciklama.txt      # ReservationService (İş Mantığı) açıklamaları
│   ├── Customer_aciklama.txt    # Customer sınıfı ve metot açıklamaları
│   ├── room_kod_aciklamasi.txt  # Room sınıfı detayları
│   └── MenuController.txt       # Arayüz kontrolcüsü ve menü yapısı
│
├── .gitignore                   # Git hariç tutma ayarları
├── README.md                    # 📖 Proje rehberi
├── rooms.txt                    # 📄 Veri: Oda kayıtları
├── customers.txt                # 📄 Veri: Müşteri kayıtları
└── reservations.txt             # 📄 Veri: Rezervasyon kayıtları
