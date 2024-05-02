package barangmuseum;

import java.util.ArrayList;
import java.util.Scanner;

public class Museum implements BarangMuseum {
    private final ArrayList<Barang> daftarBarang = new ArrayList<>();

    @Override
    public void tambahBarang(Scanner scanner) {
        System.out.println("== Tambah Barang ==");
        System.out.print("Masukkan nama barang: ");
        String namaBarang = scanner.nextLine();
        System.out.print("Masukkan jenis barang (Seni/Sejarah): ");
        String jenisBarang = scanner.nextLine();

        if (jenisBarang.equalsIgnoreCase("Seni") || jenisBarang.equalsIgnoreCase("Sejarah")) {
            System.out.print("Masukkan tahun barang: ");
            try {
                int tahunBarang = Integer.parseInt(scanner.nextLine());

                if (jenisBarang.equalsIgnoreCase("Seni")) {
                    System.out.print("Masukkan teknik barang seni: ");
                    String teknikBarang = scanner.nextLine();
                    BarangSeni barang = new BarangSeni(namaBarang, jenisBarang, tahunBarang, teknikBarang);
                    daftarBarang.add(barang);
                    System.out.println("Data berhasil ditambahkan.");
                } else {
                    BarangSejarah barang = new BarangSejarah(namaBarang, jenisBarang, tahunBarang);
                    daftarBarang.add(barang);
                    System.out.println("Data berhasil ditambahkan.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Tahun barang harus berupa bilangan bulat.");
            }
        } else {
            System.out.println("Error: Jenis barang tidak valid.");
        }
    }

    @Override
    public void lihatDaftarBarang() {
        System.out.println("== Daftar Barang Museum ==");
        if (daftarBarang.isEmpty()) {
            System.out.println("Tidak ada data barang di Museum.");
        } else {
            for (int i = 0; i < daftarBarang.size(); i++) {
                Barang barang = daftarBarang.get(i);
                System.out.println("Nomor: " + (i + 1));
                System.out.println("Nama: " + barang.getNama());
                System.out.println("Jenis: " + barang.getJenis());
                System.out.println("Tahun: " + barang.getTahun());
                if (barang instanceof BarangSeni) {
                    System.out.println("Teknik: " + ((BarangSeni) barang).getTeknik());
                }
                System.out.println("--------------");
            }
        }
    }

    @Override
    public void updateBarang(Scanner scanner) {
        System.out.println("== Update Barang ==");
        if (daftarBarang.isEmpty()) {
            System.out.println("Tidak ada data barang di Museum.");
            return;
        }

        lihatDaftarBarang();
        System.out.print("Masukkan nomor barang yang ingin diupdate: ");
        int indexUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (indexUpdate > 0 && indexUpdate <= daftarBarang.size()) {
            Barang barang = daftarBarang.get(indexUpdate - 1);
            System.out.print("Masukkan nama barang baru: ");
            String namaUpdate = scanner.nextLine();
            System.out.print("Masukkan jenis barang baru: ");
            String jenisUpdate = scanner.nextLine();
            System.out.print("Masukkan tahun barang baru: ");
            int tahunUpdate = Integer.parseInt(scanner.nextLine());

            barang.setNama(namaUpdate);
            barang.setJenis(jenisUpdate);
            barang.setTahun(tahunUpdate);
            System.out.println("Data berhasil diperbarui.");
        } else {
            System.out.println("Nomor yang Anda masukkan tidak valid.");
        }
    }

    @Override
    public void hapusBarang(Scanner scanner) {
        System.out.println("== Hapus Barang ==");
        if (daftarBarang.isEmpty()) {
            System.out.println("Tidak ada data barang di Museum.");
            return;
        }

        lihatDaftarBarang();
        System.out.print("Masukkan nomor barang yang ingin dihapus: ");
        int indexHapus = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (indexHapus > 0 && indexHapus <= daftarBarang.size()) {
            daftarBarang.remove(indexHapus - 1);
            // Update nomor barang
            for (int i = 0; i < daftarBarang.size(); i++) {
                daftarBarang.get(i).setNomor(i + 1);
            }
            System.out.println("Data berhasil dihapus.");
        } else {
            System.out.println("Nomor tidak valid.");
        }
    }

    public static void printWelcomeMessage() {
        System.out.println("Selamat datang di Sistem Pendataan Barang Museum Mulawarman");
    }
}