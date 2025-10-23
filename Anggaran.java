import java.util.ArrayList;
import java.util.List;

public class Anggaran {
    private String budgetId;
    private String kategoriId;
    private int bulan;
    private int tahun;
    private double jumlahAnggaran;
    private double jumlahTerpakai;

    public Anggaran(String budgetId, String kategoriId, int bulan, int tahun, double jumlahAnggaran) {
        this.budgetId = budgetId;
        this.kategoriId = kategoriId;
        this.bulan = bulan;
        this.tahun = tahun;
        this.jumlahAnggaran = jumlahAnggaran;
        this.jumlahTerpakai = 0;
    }

    public void updateAnggaran(double jumlahBaru) {
        this.jumlahAnggaran = jumlahBaru;
    }

    public String cekStatusAnggaran() {
        double persentase = (jumlahTerpakai / jumlahAnggaran) * 100;
        if (persentase >= 100) {
            return "MELAMPAUI";
        } else if (persentase >= 80) {
            return "WASPADA";
        } else {
            return "AMAN";
        }
    }

    public static List<Anggaran> getAnggaranBulanan(List<Anggaran> semuaAnggaran, int bulan, int tahun) {
        List<Anggaran> result = new ArrayList<>();
        for (Anggaran a : semuaAnggaran) {
            if (a.bulan == bulan && a.tahun == tahun) {
                result.add(a);
            }
        }
        return result;
    }

    // Getters & Setters
    public String getBudgetId() { return budgetId; }
    public String getKategoriId() { return kategoriId; }
    public int getBulan() { return bulan; }
    public int getTahun() { return tahun; }
    public double getJumlahAnggaran() { return jumlahAnggaran; }
    public double getJumlahTerpakai() { return jumlahTerpakai; }
    public void setJumlahTerpakai(double jumlahTerpakai) { this.jumlahTerpakai = jumlahTerpakai; }
}