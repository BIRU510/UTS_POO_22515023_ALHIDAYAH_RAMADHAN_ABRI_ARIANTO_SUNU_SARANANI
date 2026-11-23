import java.util.Arrays;

/**
 * ADT Sorted List berbasis array.
 * Elemen disimpan berdasarkan urutan alami (Comparable), dari kecil ke besar.
 */
public class SortedList<T extends Comparable<T>> {
    private Object[] data;
    private int jumlah;

    public SortedList() {
        this(100);
    }

    public SortedList(int kapasitas) {
        if (kapasitas <= 0)
            throw new IllegalArgumentException("Kapasitas harus lebih dari 0.");
        data = new Object[kapasitas];
        jumlah = 0;
    }

    public boolean insert(T item) {
        if (item == null)
            throw new IllegalArgumentException("Data tidak boleh null.");
        if (isFull()) {
            System.out.println("Sorted List penuh. Data tidak dapat ditambahkan.");
            return false;
        }

        int posisi = 0;
        while (posisi < jumlah && get(posisi).compareTo(item) < 0) {
            posisi++;
        }

        if (posisi < jumlah && get(posisi).compareTo(item) == 0) {
            System.out.println("Data dengan kunci yang sama sudah ada.");
            return false;
        }

        for (int i = jumlah; i > posisi; i--) {
            data[i] = data[i - 1];
        }
        data[posisi] = item;
        jumlah++;
        return true;
    }

    public boolean remove(T target) {
        int posisi = search(target);
        if (posisi == -1) return false;

        for (int i = posisi; i < jumlah - 1; i++) {
            data[i] = data[i + 1];
        }
        data[jumlah - 1] = null;
        jumlah--;
        return true;
    }

    /**
     * Mengembalikan indeks elemen yang dicari, atau -1 jika tidak ditemukan.
     */
    public int search(T target) {
        if (target == null) return -1;
        int kiri = 0;
        int kanan = jumlah - 1;

        while (kiri <= kanan) {
            int tengah = kiri + (kanan - kiri) / 2;
            int hasil = get(tengah).compareTo(target);

            if (hasil == 0) return tengah;
            if (hasil < 0) kiri = tengah + 1;
            else kanan = tengah - 1;
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= jumlah)
            throw new IndexOutOfBoundsException("Indeks di luar jangkauan.");
        return (T) data[index];
    }

    public void tampilkan() {
        if (isEmpty()) {
            System.out.println("Sorted List masih kosong.");
            return;
        }
        for (int i = 0; i < jumlah; i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(get(i));
        }
    }

    public int size() { return jumlah; }
    public boolean isEmpty() { return jumlah == 0; }
    public boolean isFull() { return jumlah == data.length; }
    public int capacity() { return data.length; }

    public void clear() {
        Arrays.fill(data, null);
        jumlah = 0;
    }
}
