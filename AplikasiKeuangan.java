import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AplikasiKeuangan {
    private List<Pengguna> daftarPengguna = new ArrayList<>();
    private List<Transaksi> daftarTransaksi = new ArrayList<>();
    private List<Kategori> daftarKategori = new ArrayList<>();
    private List<Anggaran> daftarAnggaran = new ArrayList<>();
    private Pengguna penggunaAktif;
    private Scanner scanner = new Scanner(System.in);

    public void inisialisasiData() {
        // Data dummy pengguna
        daftarPengguna.add(new Pengguna("USER001", "John Doe", "john@email.com", "IDR", "1234"));
        
        // Data dummy kategori
        daftarKategori.add(new Kategori("KAT001", "Gaji", "PEMASUKAN", 0));
        daftarKategori.add(new Kategori("KAT002", "Makanan", "PENGELUARAN", 1000000));
        daftarKategori.add(new Kategori("KAT003", "Transportasi", "PENGELUARAN", 500000));
        daftarKategori.add(new Kategori("KAT004", "Hiburan", "PENGELUARAN", 300000));
        
        // Data dummy transaksi
        daftarTransaksi.add(new Transaksi("TRX001", "Gaji Bulanan", 5000000, "PEMASUKAN", LocalDate.now(), "KAT001"));
        daftarTransaksi.add(new Transaksi("TRX002", "Makan Siang", 50000, "PENGELUARAN", LocalDate.now(), "KAT002"));
        
        System.out.println("Data berhasil diinisialisasi!");
    }

    public boolean loginDenganPin(String pin) {
        for (Pengguna p : daftarPengguna) {
            if (p.verifikasiPin(pin)) {
                penggunaAktif = p;
                return true;
            }
        }
        return false;
    }

    public void kelolaTransaksi() {
        System.out.println("\n=== KELOLA TRANSAKSI ===");
        System.out.println("1. Tambah Transaksi");
        System.out.println("2. Lihat Semua Transaksi");
        System.out.println("3. Edit Transaksi");
        System.out.print("Pilih opsi: ");
        int opsi = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (opsi) {
            case 1:
                tambahTransaksi();
                break;
            case 2:
                lihatSemuaTransaksi();
                break;
            case 3:
                editTransaksi();
                break;
            default:
                System.out.println("Opsi tidak valid!");
        }
    }

    private void tambahTransaksi() {
        System.out.println("\n--- Tambah Transaksi Baru ---");
        System.out.print("Deskripsi: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Jumlah: ");
        double jumlah = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        System.out.print("Tipe (PEMASUKAN/PENGELUARAN): ");
        String tipe = scanner.nextLine();
        
        // Tampilkan kategori yang tersedia
        System.out.println("\nKategori Tersedia:");
        for (Kategori k : daftarKategori) {
            if (k.getTipeKategori().equals(tipe)) {
                System.out.println(k.getCategoryId() + " - " + k.getNamaKategori());
            }
        }
        System.out.print("Pilih ID Kategori: ");
        String kategoriId = scanner.nextLine();
        
        String transactionId = "TRX" + (daftarTransaksi.size() + 1);
        Transaksi transaksiBaru = new Transaksi(transactionId, deskripsi, jumlah, tipe, LocalDate.now(), kategoriId);
        daftarTransaksi.add(transaksiBaru);
        
        System.out.println("Transaksi berhasil ditambahkan!");
    }

    private void lihatSemuaTransaksi() {
        System.out.println("\n--- SEMUA TRANSAKSI ---");
        for (Transaksi t : daftarTransaksi) {
            Kategori k = Kategori.getKategoriById(daftarKategori, t.getKategoriId());
            String namaKategori = (k != null) ? k.getNamaKategori() : "Unknown";
            System.out.printf("%s | %s | %s | %,.2f | %s\n", 
                t.getTransactionId(), t.getTanggal(), t.getDeskripsi(), 
                t.getJumlah(), namaKategori);
        }
    }

    private void editTransaksi() {
        lihatSemuaTransaksi();
        System.out.print("Masukkan ID Transaksi yang akan diedit: ");
        String id = scanner.nextLine();
        
        for (Transaksi t : daftarTransaksi) {
            if (t.getTransactionId().equals(id)) {
                System.out.print("Deskripsi baru: ");
                String deskripsi = scanner.nextLine();
                System.out.print("Jumlah baru: ");
                double jumlah = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                System.out.print("Kategori ID baru: ");
                String kategoriId = scanner.nextLine();
                
                t.editTransaksi(deskripsi, jumlah, kategoriId);
                System.out.println("Transaksi berhasil diupdate!");
                return;
            }
        }
        System.out.println("Transaksi tidak ditemukan!");
    }

    public void kelolaKategori() {
        System.out.println("\n=== KELOLA KATEGORI ===");
        System.out.println("1. Tambah Kategori");
        System.out.println("2. Lihat Semua Kategori");
        System.out.println("3. Edit Kategori");
        System.out.print("Pilih opsi: ");
        int opsi = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (opsi) {
            case 1:
                tambahKategori();
                break;
            case 2:
                lihatSemuaKategori();
                break;
            case 3:
                editKategori();
                break;
            default:
                System.out.println("Opsi tidak valid!");
        }
    }

    private void tambahKategori() {
        System.out.println("\n--- Tambah Kategori Baru ---");
        System.out.print("Nama Kategori: ");
        String nama = scanner.nextLine();
        System.out.print("Tipe (PEMASUKAN/PENGELUARAN): ");
        String tipe = scanner.nextLine();
        System.out.print("Batas Anggaran (0 untuk pemasukan): ");
        double batas = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        
        String categoryId = "KAT" + (daftarKategori.size() + 1);
        Kategori kategoriBaru = new Kategori(categoryId, nama, tipe, batas);
        daftarKategori.add(kategoriBaru);
        
        System.out.println("Kategori berhasil ditambahkan!");
    }

    private void lihatSemuaKategori() {
        System.out.println("\n--- SEMUA KATEGORI ---");
        for (Kategori k : daftarKategori) {
            System.out.printf("%s | %s | %s | %,.2f\n", 
                k.getCategoryId(), k.getNamaKategori(), k.getTipeKategori(), k.getBatasAnggaran());
        }
    }

    private void editKategori() {
        lihatSemuaKategori();
        System.out.print("Masukkan ID Kategori yang akan diedit: ");
        String id = scanner.nextLine();
        
        for (Kategori k : daftarKategori) {
            if (k.getCategoryId().equals(id)) {
                System.out.print("Nama baru: ");
                String nama = scanner.nextLine();
                System.out.print("Batas anggaran baru: ");
                double batas = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                
                k.editKategori(nama, batas);
                System.out.println("Kategori berhasil diupdate!");
                return;
            }
        }
        System.out.println("Kategori tidak ditemukan!");
    }

    public void kelolaAnggaran() {
        System.out.println("\n=== MONITORING ANGGARAN ===");
        System.out.println("1. Lihat Status Anggaran");
        System.out.println("2. Atur/Update Anggaran");
        System.out.print("Pilih opsi: ");
        int opsi = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (opsi) {
            case 1:
                lihatStatusAnggaran();
                break;
            case 2:
                aturAnggaran();
                break;
            default:
                System.out.println("Opsi tidak valid!");
        }
    }

    private void lihatStatusAnggaran() {
        System.out.print("Bulan (1-12): ");
        int bulan = scanner.nextInt();
        System.out.print("Tahun: ");
        int tahun = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        List<Anggaran> anggaranBulanan = Anggaran.getAnggaranBulanan(daftarAnggaran, bulan, tahun);
        
        System.out.println("\n--- STATUS ANGGARAN BULAN " + bulan + "/" + tahun + " ---");
        for (Anggaran a : anggaranBulanan) {
            Kategori k = Kategori.getKategoriById(daftarKategori, a.getKategoriId());
            if (k != null) {
                // Hitung total pengeluaran untuk kategori ini
                double totalPengeluaran = 0;
                for (Transaksi t : daftarTransaksi) {
                    if (t.getKategoriId().equals(a.getKategoriId()) && 
                        t.getTipe().equals("PENGELUARAN") &&
                        t.getTanggal().getMonthValue() == bulan &&
                        t.getTanggal().getYear() == tahun) {
                        totalPengeluaran += t.getJumlah();
                    }
                }
                a.setJumlahTerpakai(totalPengeluaran);
                
                double persentase = (totalPengeluaran / a.getJumlahAnggaran()) * 100;
                String status = a.cekStatusAnggaran();
                String icon = status.equals("AMAN") ? "" : status.equals("WASPADA") ? "" : "";
                
                System.out.printf("%s %s: %,.2f / %,.2f (%.1f%%) - %s\n", 
                    icon, k.getNamaKategori(), totalPengeluaran, a.getJumlahAnggaran(), persentase, status);
            }
        }
    }

    private void aturAnggaran() {
        System.out.println("\n--- ATUR ANGGARAN ---");
        lihatSemuaKategori();
        
        System.out.print("Pilih ID Kategori: ");
        String kategoriId = scanner.nextLine();
        System.out.print("Bulan (1-12): ");
        int bulan = scanner.nextInt();
        System.out.print("Tahun: ");
        int tahun = scanner.nextInt();
        System.out.print("Jumlah Anggaran: ");
        double jumlah = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        
        // Cek apakah anggaran sudah ada
        for (Anggaran a : daftarAnggaran) {
            if (a.getKategoriId().equals(kategoriId) && a.getBulan() == bulan && a.getTahun() == tahun) {
                a.updateAnggaran(jumlah);
                System.out.println("Anggaran berhasil diupdate!");
                return;
            }
        }
        
        // Jika belum ada, buat baru
        String budgetId = "BUD" + (daftarAnggaran.size() + 1);
        Anggaran anggaranBaru = new Anggaran(budgetId, kategoriId, bulan, tahun, jumlah);
        daftarAnggaran.add(anggaranBaru);
        System.out.println("Anggaran berhasil ditambahkan!");
    }

    public void kelolaLaporan() {
        System.out.println("\n=== KELOLA LAPORAN ===");
        System.out.println("1. Buat Laporan Harian");
        System.out.println("2. Buat Laporan Bulanan");
        System.out.println("3. Ekspor Laporan");
        System.out.print("Pilih opsi: ");
        int opsi = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (opsi) {
            case 1:
                buatLaporanHarian();
                break;
            case 2:
                buatLaporanBulanan();
                break;
            case 3:
                eksporLaporan();
                break;
            default:
                System.out.println("Opsi tidak valid!");
        }
    }

    private void buatLaporanHarian() {
        System.out.print("Tanggal (YYYY-MM-DD): ");
        String tanggalStr = scanner.nextLine();
        LocalDate tanggal = LocalDate.parse(tanggalStr);
        
        List<Transaksi> transaksiHarian = Transaksi.getTransaksiByPeriode(daftarTransaksi, tanggal, tanggal);
        LaporanKeuangan laporan = new LaporanKeuangan("LAP001", "HARIAN", tanggal, tanggal);
        laporan.generateLaporan(transaksiHarian);
        laporan.tampilkanLaporan();
    }

    private void buatLaporanBulanan() {
        System.out.print("Bulan (1-12): ");
        int bulan = scanner.nextInt();
        System.out.print("Tahun: ");
        int tahun = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        LocalDate start = LocalDate.of(tahun, bulan, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        
        List<Transaksi> transaksiBulanan = Transaksi.getTransaksiByPeriode(daftarTransaksi, start, end);
        LaporanKeuangan laporan = new LaporanKeuangan("LAP002", "BULANAN", start, end);
        laporan.generateLaporan(transaksiBulanan);
        laporan.tampilkanLaporan();
    }

    private void eksporLaporan() {
        System.out.println("1. Ekspor ke PDF");
        System.out.println("2. Ekspor ke CSV");
        System.out.print("Pilih format: ");
        int format = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        LaporanKeuangan laporan = new LaporanKeuangan("LAP003", "EKSPOR", LocalDate.now(), LocalDate.now());
        
        if (format == 1) {
            laporan.eksporKePDF();
        } else if (format == 2) {
            laporan.eksporKeCSV();
        } else {
            System.out.println("Format tidak valid!");
        }
    }

    public void tampilkanRingkasan() {
        RingkasanKeuangan ringkasan = new RingkasanKeuangan();
        ringkasan.tampilkanRingkasan(daftarTransaksi);
    }

    // Getter untuk pengguna aktif
    public Pengguna getPenggunaAktif() { return penggunaAktif; }
}