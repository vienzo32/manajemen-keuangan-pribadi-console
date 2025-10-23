import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AplikasiKeuangan app = new AplikasiKeuangan();

        System.out.println("=================================");
        System.out.println("  MANAJEMEN KEUANGAN PERSONAL");
        System.out.println("=================================");

        // Inisialisasi data dummy
        app.inisialisasiData();

        // Login
        boolean loginSuccess = false;
        while (!loginSuccess) {
            System.out.print("Masukkan PIN Akses: ");
            String pin = scanner.nextLine();

            if (app.loginDenganPin(pin)) {
                loginSuccess = true;
                System.out.println(" Login berhasil! Selamat datang.");
            } else {
                System.out.println(" PIN salah. Coba lagi.");
            }
        }

        // Menu Utama
        boolean running = true;
        while (running) {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Kelola Transaksi");
            System.out.println("2. Kelola Kategori");
            System.out.println("3. Monitoring Anggaran");
            System.out.println("4. Buat Laporan");
            System.out.println("5. Ringkasan Keuangan");
            System.out.println("6. Kelola Profil");
            System.out.println("7. Keluar");
            System.out.print("Pilih menu [1-7]: ");

            if (scanner.hasNextInt()) {
                int pilihan = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (pilihan) {
                    case 1:
                        app.kelolaTransaksi();
                        break;
                    case 2:
                        app.kelolaKategori();
                        break;
                    case 3:
                        app.kelolaAnggaran();
                        break;
                    case 4:
                        app.kelolaLaporan();
                        break;
                    case 5:
                        app.tampilkanRingkasan();
                        break;
                    case 6:
                        if (app.getPenggunaAktif() != null) {
                            app.getPenggunaAktif().updateProfil(scanner);
                        }
                        break;
                    case 7:
                        running = false;
                        System.out.println("Terima kasih telah menggunakan aplikasi!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } else {
                System.out.println("Input harus angka!");
                scanner.nextLine(); // clear invalid input
            }
        }

        scanner.close();
    }
}