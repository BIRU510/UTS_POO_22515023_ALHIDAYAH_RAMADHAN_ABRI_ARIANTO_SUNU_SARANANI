import java.time.LocalDate;


public class Sewa {
    private String kodeSewa;
    private Kendaraan kendaraan;
    private Penyewa penyewa;
    private int lamaHari;
    private double totalBiaya;
    private boolean aktif;
    private LocalDate tanggalSewa;

    public Sewa() {
        this("S000", null, null, 1);
    }

    public Sewa(String kodeSewa, Kendaraan kendaraan, Penyewa penyewa, int lamaHari) {
        if (kodeSewa == null || kodeSewa.trim().isEmpty())
            throw new IllegalArgumentException("Kode sewa tidak boleh kosong.");
        if (kendaraan == null || penyewa == null)
            throw new IllegalArgumentException("Kendaraan dan penyewa wajib dipilih.");
        if (lamaHari <= 0)
            throw new IllegalArgumentException("Lama sewa harus lebih dari 0 hari.");

        this.kodeSewa = kodeSewa.trim().toUpperCase();
        this.kendaraan = kendaraan;
        this.penyewa = penyewa;
        this.lamaHari = lamaHari;
        this.tanggalSewa = LocalDate.now();
        this.totalBiaya = hitungTotal();
        this.aktif = true;
    }

    public String getKodeSewa() { return kodeSewa; }
    public Kendaraan getKendaraan() { return kendaraan; }
    public Penyewa getPenyewa() { return penyewa; }
    public int getLamaHari() { return lamaHari; }
    public double getTotalBiaya() { return totalBiaya; }
    public boolean isAktif() { return aktif; }
    public LocalDate getTanggalSewa() { return tanggalSewa; }

    public double hitungTotal() {
        if (kendaraan == null) return 0;
        return kendaraan.hitungBiaya(lamaHari);
    }

    public void selesaikanSewa() {
        if (!aktif)
            throw new IllegalStateException("Transaksi ini sudah selesai.");
        aktif = false;
        kendaraan.setTersedia(true);
    }

    public void tampilkanInfo() {
        System.out.printf("%-10s | %-10s | %-18s | %-18s | %3d | Rp%-12.2f | %-10s | %s%n",
                kodeSewa, kendaraan.getKode(), kendaraan.getNama(),
                penyewa.getNama(), lamaHari, totalBiaya,
                aktif ? "Berjalan" : "Selesai", tanggalSewa);
    }
}
