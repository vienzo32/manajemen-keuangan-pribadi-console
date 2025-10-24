import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Transaksi {
    private String transactionId;
    private String deskripsi;
    private double jumlah;
    private String tipe; // "PEMASUKAN" atau "PENGELUARAN"
    private LocalDate tanggal;
    private String kategoriId;

    public Transaksi(String transactionId, String deskripsi, double jumlah, String tipe, LocalDate tanggal, String kategoriId) {
        this.transactionId = transactionId;
        this.deskripsi = deskripsi;
        this.jumlah = jumlah;
        this.tipe = tipe;
        this.tanggal = tanggal;
        this.kategoriId = kategoriId;
    }

    public void editTransaksi(String deskripsi, double jumlah, String kategoriId) {
        this.deskripsi = deskripsi;
        this.jumlah = jumlah;
        this.kategoriId = kategoriId;
    }

    public static List<Transaksi> getTransaksiByPeriode(List<Transaksi> semuaTransaksi, LocalDate start, LocalDate end) {
        List<Transaksi> result = new ArrayList<>();
        for (Transaksi t : semuaTransaksi) {
            if (!t.tanggal.isBefore(start) && !t.tanggal.isAfter(end)) {
                result.add(t);
            }
        }
        return result;
    }

    public static List<Transaksi> getTransaksiByKategori(List<Transaksi> semuaTransaksi, String kategoriId) {
        List<Transaksi> result = new ArrayList<>();
        for (Transaksi t : semuaTransaksi) {
            if (t.kategoriId.equals(kategoriId)) {
                result.add(t);
            }
        }
        return result;
    }

    // Getters
    public String getTransactionId() { return transactionId; }
    public String getDeskripsi() { return deskripsi; }
    public double getJumlah() { return jumlah; }
    public String getTipe() { return tipe; }
    public LocalDate getTanggal() { return tanggal; }
    public String getKategoriId() { return kategoriId; }
}