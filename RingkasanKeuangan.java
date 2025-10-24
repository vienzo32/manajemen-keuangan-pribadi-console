import java.util.List;

public class RingkasanKeuangan {
    private double saldoSekarang;
    private double totalPemasukan;
    private double totalPengeluaran;
    private String periode;

    public double hitungSaldo(List<Transaksi> semuaTransaksi) {
        double saldo = 0;
        for (Transaksi t : semuaTransaksi) {
            if (t.getTipe().equals("PEMASUKAN")) {
                saldo += t.getJumlah();
            } else {
                saldo -= t.getJumlah();
            }
        }
        this.saldoSekarang = saldo;
        return saldo;
    }

    public double hitungTotalPemasukan(List<Transaksi> transaksi) {
        double total = 0;
        for (Transaksi t : transaksi) {
            if (t.getTipe().equals("PEMASUKAN")) {
                total += t.getJumlah();
            }
        }
        this.totalPemasukan = total;
        return total;
    }

    public double hitungTotalPengeluaran(List<Transaksi> transaksi) {
        double total = 0;
        for (Transaksi t : transaksi) {
            if (t.getTipe().equals("PENGELUARAN")) {
                total += t.getJumlah();
            }
        }
        this.totalPengeluaran = total;
        return total;
    }

    public void tampilkanRingkasan(List<Transaksi> semuaTransaksi) {
        hitungSaldo(semuaTransaksi);
        hitungTotalPemasukan(semuaTransaksi);
        hitungTotalPengeluaran(semuaTransaksi);
        
        System.out.println("\n=== RINGKASAN KEUANGAN ===");
        System.out.println("Saldo Sekarang: " + String.format("%,.2f", saldoSekarang));
        System.out.println("Total Pemasukan: " + String.format("%,.2f", totalPemasukan));
        System.out.println("Total Pengeluaran: " + String.format("%,.2f", totalPengeluaran));
        System.out.println("==========================");
    }
}