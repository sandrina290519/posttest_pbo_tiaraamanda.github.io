package barangmuseum;

import java.util.ArrayList;
import java.util.Scanner;

public class Museum {
    private ArrayList<Barang> daftarBarang = new ArrayList<>();

    public void tambahBarang() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Masukkan nama barang: ");
        String namaBarang = scanner.nextLine();
    
        System.out.print("Masukkan jenis barang (Seni/Sejarah): ");
        String jenisBarang = scanner.nextLine();
    
        if (jenisBarang.equalsIgnoreCase("Seni") || jenisBarang.equalsIgnoreCase("Sejarah")) {
            System.out.print("Masukkan tahun barang: ");
            if (scanner.hasNextInt()) {
                int tahunBarang = scanner.nextInt();
                scanner.nextLine(); 
    
                if (jenisBarang.equalsIgnoreCase("Seni")) {
                    BarangSeni barang = new BarangSeni(namaBarang, jenisBarang, tahunBarang);
                    daftarBarang.add(barang);
                    System.out.println("Data berhasil ditambahkan.");
                } else {
                    BarangSejarah barang = new BarangSejarah(namaBarang, jenisBarang, tahunBarang);
                    daftarBarang.add(barang);
                    System.out.println("Data berhasil ditambahkan.");
                }
            } else {
                System.out.println("Error: Tahun barang harus berupa bilangan bulat.");
                scanner.nextLine(); 
            }
        } else {
            System.out.println("Error: Jenis barang tidak valid.");
        }
    }
    
    
    public void lihatDaftarBarang() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Tidak ada data barang di Museum.");
        } else {
            System.out.println("Daftar Barang di Museum Mulawarman:");
            int counter = 1;

            for (Barang barang : daftarBarang) {
                System.out.println("No.: " + counter);
                System.out.println("Nama: " + barang.getNama());
                System.out.println("Jenis: " + barang.getJenis());
                System.out.println("Tahun: " + barang.getTahun());
                System.out.println("--------------");
                counter++;
            }
        }
    }

    public void updateBarang(int nomor, String nama, String jenis, int tahun) {
        if (nomor > 0 && nomor <= daftarBarang.size()) {
            int index = nomor - 1;
            Barang barang = daftarBarang.get(index);

            // Memperbarui data barang
            barang.setNama(nama);
            barang.setJenis(jenis);
            barang.setTahun(tahun);

            System.out.println("Data berhasil diperbarui.");
        } else {
            System.out.println("Nomor yang Anda masukkan tidak valid;(");
        }
    }

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
