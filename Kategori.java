import java.util.ArrayList;
import java.util.List;

public class Kategori {
    private String categoryId;
    private String namaKategori;
    private String tipeKategori; // "PEMASUKAN" atau "PENGELUARAN"
    private double batasAnggaran;

    public Kategori(String categoryId, String namaKategori, String tipeKategori, double batasAnggaran) {
        this.categoryId = categoryId;
        this.namaKategori = namaKategori;
        this.tipeKategori = tipeKategori;
        this.batasAnggaran = batasAnggaran;
    }

    public void editKategori(String namaKategori, double batasAnggaran) {
        this.namaKategori = namaKategori;
        this.batasAnggaran = batasAnggaran;
    }

    public static Kategori getKategoriById(List<Kategori> semuaKategori, String categoryId) {
        for (Kategori k : semuaKategori) {
            if (k.categoryId.equals(categoryId)) {
                return k;
            }
        }
        return null;
    }

    public static List<Kategori> getKategoriByTipe(List<Kategori> semuaKategori, String tipe) {
        List<Kategori> result = new ArrayList<>();
        for (Kategori k : semuaKategori) {
            if (k.tipeKategori.equals(tipe)) {
                result.add(k);
            }
        }
        return result;
    }

    // Getters
    public String getCategoryId() { return categoryId; }
    public String getNamaKategori() { return namaKategori; }
    public String getTipeKategori() { return tipeKategori; }
    public double getBatasAnggaran() { return batasAnggaran; }
}