
public class Kendaraan implements Comparable<Kendaraan> {
    private String kode;
    private String nama;
    private String jenis;
    private double tarifPerHari;
    private boolean tersedia;

    public Kendaraan() {
        this("K000", "Belum diisi", "Umum", 0, true);
    }

    public Kendaraan(String kode, String nama, String jenis,
                     double tarifPerHari, boolean tersedia) {
        setKode(kode);
        setNama(nama);
        setJenis(jenis);
        setTarifPerHari(tarifPerHari);
        this.tersedia = tersedia;
    }

    public String getKode() { return kode; }
    public void setKode(String kode) {
        if (kode == null || kode.trim().isEmpty())
            throw new IllegalArgumentException("Kode kendaraan tidak boleh kosong.");
        this.kode = kode.trim().toUpperCase();
    }

    public String getNama() { return nama; }
    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty())
            throw new IllegalArgumentException("Nama kendaraan tidak boleh kosong.");
        this.nama = nama.trim();
    }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) {
        if (jenis == null || jenis.trim().isEmpty())
            throw new IllegalArgumentException("Jenis kendaraan tidak boleh kosong.");
        this.jenis = jenis.trim();
    }

    public double getTarifPerHari() { return tarifPerHari; }
    public void setTarifPerHari(double tarifPerHari) {
        if (tarifPerHari < 0)
            throw new IllegalArgumentException("Tarif tidak boleh negatif.");
        this.tarifPerHari = tarifPerHari;
    }

    public boolean isTersedia() { return tersedia; }
    public void setTersedia(boolean tersedia) { this.tersedia = tersedia; }

    public double hitungBiaya(int lamaHari) {
        if (lamaHari <= 0)
            throw new IllegalArgumentException("Lama sewa harus lebih dari 0 hari.");
        return tarifPerHari * lamaHari;
    }

    public void tampilkanInfo() {
        System.out.printf("%-10s | %-18s | %-12s | Rp%-12.2f | %s%n",
                kode, nama, jenis, tarifPerHari, tersedia ? "Tersedia" : "Disewa");
    }

    @Override
    public int compareTo(Kendaraan kendaraanLain) {
        return this.kode.compareToIgnoreCase(kendaraanLain.kode);
    }
}
