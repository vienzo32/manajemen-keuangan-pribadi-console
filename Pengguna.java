import java.util.Scanner;

public class Pengguna {
    protected String userId;
    protected String nama;
    protected String email;
    protected String mataUang;
    protected String pinAkses;

    public Pengguna(String userId, String nama, String email, String mataUang, String pinAkses) {
        this.userId = userId;
        this.nama = nama;
        this.email = email;
        this.mataUang = mataUang;
        this.pinAkses = pinAkses;
    }

    public boolean verifikasiPin(String pin) {
        return this.pinAkses.equals(pin);
    }

    public void updateProfil(Scanner scanner) {
        System.out.print("Nama baru: ");
        this.nama = scanner.nextLine();
        System.out.print("Email baru: ");
        this.email = scanner.nextLine();
        System.out.println("Profil berhasil diupdate!");
    }

    public void setPreferensi(String mataUang) {
        this.mataUang = mataUang;
        System.out.println("Preferensi mata uang diubah ke: " + mataUang);
    }

    // Getters
    public String getUserId() { return userId; }
    public String getNama() { return nama; }
    public String getEmail() { return email; }
    public String getMataUang() { return mataUang; }
}