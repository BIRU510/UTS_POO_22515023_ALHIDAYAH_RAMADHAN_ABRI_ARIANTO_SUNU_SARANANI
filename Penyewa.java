/**
 * Menyimpan informasi penyewa kendaraan.
 */
public class Penyewa {
    private String idPenyewa;
    private String nama;
    private String nomorTelepon;
    private String alamat;

    public Penyewa() {
        this("P000", "Belum diisi", "0000000000", "Belum diisi");
    }

    public Penyewa(String idPenyewa, String nama, String nomorTelepon, String alamat) {
        setIdPenyewa(idPenyewa);
        setNama(nama);
        setNomorTelepon(nomorTelepon);
        setAlamat(alamat);
    }

    public String getIdPenyewa() { return idPenyewa; }
    public void setIdPenyewa(String idPenyewa) {
        if (idPenyewa == null || idPenyewa.trim().isEmpty())
            throw new IllegalArgumentException("ID penyewa tidak boleh kosong.");
        this.idPenyewa = idPenyewa.trim().toUpperCase();
    }

    public String getNama() { return nama; }
    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty())
            throw new IllegalArgumentException("Nama penyewa tidak boleh kosong.");
        this.nama = nama.trim();
    }

    public String getNomorTelepon() { return nomorTelepon; }
    public void setNomorTelepon(String nomorTelepon) {
        if (nomorTelepon == null || nomorTelepon.trim().isEmpty())
            throw new IllegalArgumentException("Nomor telepon tidak boleh kosong.");
        this.nomorTelepon = nomorTelepon.trim();
    }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) {
        if (alamat == null || alamat.trim().isEmpty())
            throw new IllegalArgumentException("Alamat tidak boleh kosong.");
        this.alamat = alamat.trim();
    }

    public void ubahKontak(String nomorTelepon, String alamat) {
        setNomorTelepon(nomorTelepon);
        setAlamat(alamat);
    }

    public boolean validasiData() {
        return idPenyewa != null && !idPenyewa.isEmpty()
                && nama != null && !nama.isEmpty()
                && nomorTelepon != null && !nomorTelepon.isEmpty()
                && alamat != null && !alamat.isEmpty();
    }

    public void tampilkanInfo() {
        System.out.printf("%-10s | %-22s | %-16s | %s%n",
                idPenyewa, nama, nomorTelepon, alamat);
    }
}
