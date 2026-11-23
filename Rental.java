import java.util.ArrayList;


public class Rental {
    private SortedList<Kendaraan> daftarKendaraan;
    private ArrayList<Penyewa> daftarPenyewa;
    private ArrayList<Sewa> daftarSewa;
    private int nomorTransaksi;

    public Rental() {
        this.daftarKendaraan = new SortedList<Kendaraan>(100);
        this.daftarPenyewa = new ArrayList<Penyewa>();
        this.daftarSewa = new ArrayList<Sewa>();
        this.nomorTransaksi = 1;
    }

    public boolean tambahKendaraan(Kendaraan kendaraan) {
        return daftarKendaraan.insert(kendaraan);
    }

    public boolean tambahPenyewa(Penyewa penyewa) {
        if (cariPenyewa(penyewa.getIdPenyewa()) != null) {
            System.out.println("ID penyewa sudah terdaftar.");
            return false;
        }
        daftarPenyewa.add(penyewa);
        return true;
    }

    public Kendaraan cariKendaraan(String kode) {
        Kendaraan kunci = new Kendaraan(kode, "Pencarian", "Pencarian", 0, true);
        int indeks = daftarKendaraan.search(kunci);
        return indeks == -1 ? null : daftarKendaraan.get(indeks);
    }

    public Penyewa cariPenyewa(String id) {
        for (Penyewa penyewa : daftarPenyewa) {
            if (penyewa.getIdPenyewa().equalsIgnoreCase(id.trim()))
                return penyewa;
        }
        return null;
    }

    public Sewa cariSewaAktif(String kodeSewa) {
        for (Sewa sewa : daftarSewa) {
            if (sewa.getKodeSewa().equalsIgnoreCase(kodeSewa.trim())
                    && sewa.isAktif()) return sewa;
        }
        return null;
    }

    public boolean buatSewa(String kodeKendaraan, String idPenyewa, int lamaHari) {
        Kendaraan kendaraan = cariKendaraan(kodeKendaraan);
        Penyewa penyewa = cariPenyewa(idPenyewa);

        if (kendaraan == null) {
            System.out.println("Kendaraan tidak ditemukan.");
            return false;
        }
        if (penyewa == null) {
            System.out.println("Penyewa tidak ditemukan.");
            return false;
        }
        if (!kendaraan.isTersedia()) {
            System.out.println("Kendaraan sedang disewa dan tidak dapat disewa lagi.");
            return false;
        }
        if (lamaHari <= 0) {
            System.out.println("Lama sewa harus lebih dari 0 hari.");
            return false;
        }

        String kodeSewa = String.format("SW%03d", nomorTransaksi++);
        Sewa transaksi = new Sewa(kodeSewa, kendaraan, penyewa, lamaHari);
        kendaraan.setTersedia(false);
        daftarSewa.add(transaksi);

        System.out.println("Transaksi berhasil dibuat.");
        System.out.println("Kode transaksi: " + kodeSewa);
        System.out.printf("Total biaya: Rp%.2f%n", transaksi.getTotalBiaya());
        return true;
    }

    public boolean kembalikanKendaraan(String kodeSewa) {
        Sewa sewa = cariSewaAktif(kodeSewa);
        if (sewa == null) {
            System.out.println("Transaksi aktif tidak ditemukan.");
            return false;
        }
        sewa.selesaikanSewa();
        System.out.println("Pengembalian berhasil. Kendaraan sekarang tersedia.");
        return true;
    }

    public void tampilkanKendaraan() {
        System.out.println("\nKODE       | NAMA               | JENIS        | TARIF/HARI   | STATUS");
        System.out.println("-----------+--------------------+--------------+--------------+----------");
        if (daftarKendaraan.isEmpty()) {
            System.out.println("Belum ada kendaraan.");
            return;
        }
        for (int i = 0; i < daftarKendaraan.size(); i++) {
            daftarKendaraan.get(i).tampilkanInfo();
        }
    }

    public void tampilkanPenyewa() {
        System.out.println("\nID         | NAMA                   | TELEPON          | ALAMAT");
        System.out.println("-----------+------------------------+------------------+----------------");
        if (daftarPenyewa.isEmpty()) {
            System.out.println("Belum ada penyewa.");
            return;
        }
        for (Penyewa penyewa : daftarPenyewa) penyewa.tampilkanInfo();
    }

    public void tampilkanRiwayatSewa() {
        System.out.println("\nKODE SEWA  | KENDARAAN  | NAMA               | PENYEWA            | HARI | TOTAL        | STATUS     | TANGGAL");
        System.out.println("-----------+------------+--------------------+--------------------+------+--------------+------------+------------");
        if (daftarSewa.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }
        for (Sewa sewa : daftarSewa) sewa.tampilkanInfo();
    }

    public void tampilkanLaporan() {
        int aktif = 0;
        int selesai = 0;
        double totalPendapatan = 0;

        for (Sewa sewa : daftarSewa) {
            totalPendapatan += sewa.getTotalBiaya();
            if (sewa.isAktif()) aktif++;
            else selesai++;
        }

        System.out.println("\n========== LAPORAN RENTAL ==========");
        System.out.println("Jumlah kendaraan : " + daftarKendaraan.size());
        System.out.println("Jumlah penyewa   : " + daftarPenyewa.size());
        System.out.println("Jumlah transaksi : " + daftarSewa.size());
        System.out.println("Sewa aktif       : " + aktif);
        System.out.println("Sewa selesai     : " + selesai);
        System.out.printf("Total nilai transaksi: Rp%.2f%n", totalPendapatan);
        System.out.println("====================================");
    }

    public void tampilkanOperasiSortedList() {
        System.out.println("\nSorted List kendaraan (urut berdasarkan kode):");
        tampilkanKendaraan();
        System.out.println("Jumlah elemen: " + daftarKendaraan.size()
                + "/" + daftarKendaraan.capacity());
    }
}
