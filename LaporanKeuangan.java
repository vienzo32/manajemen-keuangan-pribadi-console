import java.time.LocalDate;
import java.util.List;

public class LaporanKeuangan {
    private String idLaporan;
    private String tipeLaporan; // "HARIAN", "MINGGUAN", "BULANAN"
    private LocalDate tanggalMulai;
    private LocalDate tanggalSelesai;
    private double totalPemasukan;
    private double totalPengeluaran;
    private double saldo;

    public LaporanKeuangan(String idLaporan, String tipeLaporan, LocalDate tanggalMulai, LocalDate tanggalSelesai) {
        this.idLaporan = idLaporan;
        this.tipeLaporan = tipeLaporan;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
    }

    public void generateLaporan(List<Transaksi> transaksi) {
        totalPemasukan = 0;
        totalPengeluaran = 0;
        
        for (Transaksi t : transaksi) {
            if (t.getTipe().equals("PEMASUKAN")) {
                totalPemasukan += t.getJumlah();
            } else {
                totalPengeluaran += t.getJumlah();
            }
        }
        
        saldo = totalPemasukan - totalPengeluaran;
    }

    public void tampilkanLaporan() {
        System.out.println("\n=== LAPORAN KEUANGAN (" + tipeLaporan + ") ===");
        System.out.println("Periode: " + tanggalMulai + " hingga " + tanggalSelesai);
        System.out.println("Total Pemasukan: " + String.format("%,.2f", totalPemasukan));
        System.out.println("Total Pengeluaran: " + String.format("%,.2f", totalPengeluaran));
        System.out.println("Saldo: " + String.format("%,.2f", saldo));
        System.out.println("=================================");
    }

    public void eksporKePDF() {
        System.out.println("Mengekspor laporan ke PDF...");
    }

    public void eksporKeCSV() {
        System.out.println("Mengekspor laporan ke CSV...");
    }
}