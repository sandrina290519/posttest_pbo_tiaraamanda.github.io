package TokoMakeUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Keranjang {
    private List<ProdukMakeUp> produkDalamKeranjang;
    private String username;

    public Keranjang(String username) {
        this.username = username;
        produkDalamKeranjang = new ArrayList<>();
    }

    public void tambahProduk(ProdukMakeUp produk) {
        produkDalamKeranjang.add(produk);
    }

    public void lihatKeranjang() {
        System.out.println("\n=========================");
        System.out.println("     Keranjang Belanja");
        System.out.println("=========================");
        for (ProdukMakeUp produk : produkDalamKeranjang) {
            System.out.println(produk);
        }
    }

    public void bayar(Scanner scanner) {
        lihatKeranjang();
        System.out.print("Lanjutkan pembayaran? (y/n): ");
        String jawaban = scanner.nextLine();
        if (jawaban.equalsIgnoreCase("y")) {
            // Hitung total harga dengan diskon
            double total = 0;
            for (ProdukMakeUp produk : produkDalamKeranjang) {
                if (produk instanceof ProdukMakeUpDiskon) {
                    ProdukMakeUpDiskon produkDiskon = (ProdukMakeUpDiskon) produk;
                    total += produkDiskon.hitungHarga();
                } else {
                    total += produk.getHarga();
                }
            }
            
            // Tambahkan jumlah barang yang ingin dibeli
            System.out.print("Masukkan jumlah barang yang ingin dibeli: ");
            int jumlahBarang = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline di buffer
            
            // Hitung total harga berdasarkan jumlah barang
            total *= jumlahBarang;
    
            System.out.println("Total harga: " + total);
    
            // Implementasi proses pembayaran
            System.out.print("Pilih metode pembayaran (ATM / Tunai): ");
            String metodePembayaran = scanner.nextLine();
            if (metodePembayaran.equalsIgnoreCase("ATM")) {
                System.out.println("Pembayaran dengan ATM berhasil. Terima kasih telah berbelanja.");
            } else if (metodePembayaran.equalsIgnoreCase("Tunai")) {
                System.out.println("Pembayaran dengan tunai berhasil. Terima kasih telah berbelanja.");
            } else {
                System.out.println("Metode pembayaran tidak valid. Pembayaran dibatalkan.");
                return;
            }
            
            // Bersihkan keranjang
            produkDalamKeranjang.clear();
        } else {
            System.out.println("Pembayaran dibatalkan.");
        }
    }
    
}
