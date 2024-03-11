package barangmuseum;

import java.util.ArrayList;
import java.util.Scanner;

public class museum {
    ArrayList<barang> daftarBarang = new ArrayList<>();

    // menambahkan data barang museum
    public void tambahBarang() {
        Scanner scanner = new Scanner(System.in);

        // Meminta input nama barang dengan spasi
        System.out.print("Masukkan nama barang: ");
        String namaBarang = scanner.nextLine();

        // Meminta input jenis barang dengan spasi
        System.out.print("Masukkan jenis barang: ");
        String jenisBarang = scanner.nextLine();

        // Meminta input tahun barang
        System.out.print("Masukkan tahun barang: ");
        int tahunBarang = scanner.nextInt();

        // Memasukkan data ke dalam daftar barang
        barang barang = new barang(namaBarang, jenisBarang, tahunBarang);
        daftarBarang.add(barang);

        System.out.println("Data berhasil ditambahkan.");
    }

    // melihat data barang museum
    public void lihatDaftarBarang() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Tidak ada data barang di Museum.");
        } else {
            System.out.println("Daftar Barang di Museum Mulawarman:");
            int counter = 1;

            for (barang barang : daftarBarang) {
                System.out.println("No.: " + counter);
                System.out.println("Nama: " + barang.nama);
                System.out.println("Jenis: " + barang.jenis);
                System.out.println("Tahun: " + barang.tahun);
                System.out.println("--------------");
                counter++;
            }
        }
    }

    // merubah data barang museum
    public void updateBarang(int nomor, String nama, String jenis, int tahun) {
        if (nomor > 0 && nomor <= daftarBarang.size()) {
            int index = nomor - 1;
            barang barang = daftarBarang.get(index);

            // Meminta input nama barang dengan spasi
            System.out.print("Masukkan nama barang baru: ");
            String namaUpdate = new Scanner(System.in).nextLine();

            // Meminta input jenis barang dengan spasi
            System.out.print("Masukkan jenis barang baru: ");
            String jenisUpdate = new Scanner(System.in).nextLine();

            // Meminta input tahun barang baru
            System.out.print("Masukkan tahun barang baru: ");
            int tahunUpdate = new Scanner(System.in).nextInt();

            // Memperbarui data barang
            barang.nama = namaUpdate;
            barang.jenis = jenisUpdate;
            barang.tahun = tahunUpdate;

            System.out.println("Data berhasil diperbarui.");
        } else {
            System.out.println("Nomor yang Anda masukkan tidak valid;(");
        }
    }

    // menghapus data barang museum
    public void hapusBarang(int nomor) {
        if (nomor > 0 && nomor <= daftarBarang.size()) {
            int index = nomor - 1;
            daftarBarang.remove(index);
            System.out.println("Data berhasil dihapus.");
        } else {
            System.out.println("Nomor tidak valid.");
        }
    }
}
