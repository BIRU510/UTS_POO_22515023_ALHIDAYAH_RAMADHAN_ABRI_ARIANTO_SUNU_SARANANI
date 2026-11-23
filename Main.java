import java.util.Scanner;


public class Main {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final Rental RENTAL = new Rental();

    public static void main(String[] args) {
        boolean berjalan = true;

        while (berjalan) {
            tampilkanMenu();
            int pilihan = bacaInt("Pilihan: ");

            try {
                switch (pilihan) {
                    case 1: tambahKendaraan(); break;
                    case 2: RENTAL.tampilkanKendaraan(); break;
                    case 3: tambahPenyewa(); break;
                    case 4: RENTAL.tampilkanPenyewa(); break;
                    case 5: buatSewa(); break;
                    case 6: kembalikanKendaraan(); break;
                    case 7: cariKendaraan(); break;
                    case 8: RENTAL.tampilkanRiwayatSewa(); break;
                    case 9: RENTAL.tampilkanLaporan(); break;
                    case 10: RENTAL.tampilkanOperasiSortedList(); break;
                    case 0:
                        berjalan = false;
                        System.out.println("Terima kasih telah menggunakan Rental Kendaraan.");
                        break;
                    default:
                        System.out.println("Pilihan tidak tersedia.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Kesalahan: " + e.getMessage());
            }
        }
        INPUT.close();
    }

    private static void tampilkanMenu() {
        System.out.println("\n======================================");
        System.out.println("          RENTAL KENDARAAN");
        System.out.println("======================================");
        System.out.println("1. Tambah kendaraan");
        System.out.println("2. Tampilkan kendaraan");
        System.out.println("3. Tambah penyewa");
        System.out.println("4. Tampilkan penyewa");
        System.out.println("5. Sewa kendaraan");
        System.out.println("6. Pengembalian kendaraan");
        System.out.println("7. Cari kendaraan");
        System.out.println("8. Riwayat transaksi");
        System.out.println("9. Laporan rental");
        System.out.println("10. Lihat Sorted List");
        System.out.println("0. Keluar");
        System.out.println("======================================");
    }

    private static void tambahKendaraan() {
        System.out.println("\n--- TAMBAH KENDARAAN ---");
        String kode = bacaString("Kode kendaraan: ");
        String nama = bacaString("Nama kendaraan: ");
        String jenis = bacaString("Jenis kendaraan (Mobil/Motor): ");
        double tarif = bacaDouble("Tarif per hari (Rupiah): ");

        Kendaraan kendaraan = new Kendaraan(kode, nama, jenis, tarif, true);
        if (RENTAL.tambahKendaraan(kendaraan))
            System.out.println("Kendaraan berhasil ditambahkan.");
    }

    private static void tambahPenyewa() {
        System.out.println("\n--- TAMBAH PENYEWA ---");
        String id = bacaString("ID penyewa: ");
        String nama = bacaString("Nama penyewa: ");
        String telepon = bacaString("Nomor telepon: ");
        String alamat = bacaString("Alamat: ");

        Penyewa penyewa = new Penyewa(id, nama, telepon, alamat);
        if (RENTAL.tambahPenyewa(penyewa))
            System.out.println("Penyewa berhasil ditambahkan.");
    }

    private static void buatSewa() {
        System.out.println("\n--- SEWA KENDARAAN ---");
        RENTAL.tampilkanKendaraan();
        String kode = bacaString("Kode kendaraan: ");
        String id = bacaString("ID penyewa: ");
        int hari = bacaInt("Lama sewa (hari): ");
        RENTAL.buatSewa(kode, id, hari);
    }

    private static void kembalikanKendaraan() {
        System.out.println("\n--- PENGEMBALIAN KENDARAAN ---");
        RENTAL.tampilkanRiwayatSewa();
        String kode = bacaString("Kode transaksi sewa: ");
        RENTAL.kembalikanKendaraan(kode);
    }

    private static void cariKendaraan() {
        String kode = bacaString("Masukkan kode kendaraan yang dicari: ");
        Kendaraan kendaraan = RENTAL.cariKendaraan(kode);
        if (kendaraan == null) {
            System.out.println("Kendaraan tidak ditemukan.");
        } else {
            System.out.println("\nKendaraan ditemukan:");
            System.out.println("KODE       | NAMA               | JENIS        | TARIF/HARI   | STATUS");
            kendaraan.tampilkanInfo();
        }
    }

    private static String bacaString(String pesan) {
        System.out.print(pesan);
        return INPUT.nextLine().trim();
    }

    private static int bacaInt(String pesan) {
        while (true) {
            System.out.print(pesan);
            String teks = INPUT.nextLine().trim();
            try {
                return Integer.parseInt(teks);
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa bilangan bulat. Coba lagi.");
            }
        }
    }

    private static double bacaDouble(String pesan) {
        while (true) {
            System.out.print(pesan);
            String teks = INPUT.nextLine().trim().replace(",", ".");
            try {
                double angka = Double.parseDouble(teks);
                if (angka < 0) {
                    System.out.println("Nilai tidak boleh negatif.");
                } else {
                    return angka;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka. Coba lagi.");
            }
        }
    }
}
