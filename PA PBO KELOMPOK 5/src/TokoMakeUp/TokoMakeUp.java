// File: TokoMakeUp.java
package TokoMakeUp;

import java.util.ArrayList;
import java.util.Scanner;

public class TokoMakeUp implements PendataanProduk {
    private static ArrayList<ProdukMakeUp> daftarProduk = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public void tambahProduk(String nama, double harga) {
        daftarProduk.add(new ProdukMakeUpBiasa(nama, harga));
        System.out.println("Produk berhasil ditambahkan!");
    }

    @Override
    public void lihatProduk() {
        if (daftarProduk.isEmpty()) {
            System.out.println("Belum ada produk yang tersedia.");
        } else {
            System.out.println("No  Nama Produk             Harga");
            for (int i = 0; i < daftarProduk.size(); i++) {
                ProdukMakeUp produk = daftarProduk.get(i);
                System.out.printf("%-4d%-25s%.2f\n", i + 1, produk.getNamaProduk(), produk.getHarga());
            }
        }
    }

    public void updateProduk(int indeks) {
        if (indeks >= 0 && indeks < daftarProduk.size()) {
            System.out.println("\n=========================");
            System.out.println("     Update Produk");
            System.out.println("=========================");
            System.out.print("Masukkan nama produk baru: ");
            String nama = scanner.nextLine();
            double harga = 0;
            boolean inputHargaValid = false;
            do {
                try {
                    System.out.print("Masukkan harga produk baru: ");
                    harga = scanner.nextDouble();
                    if (harga < 0) {
                        throw new Exception();
                    }
                    inputHargaValid = true;
                } catch (Exception e) {
                    System.out.println("Harga harus berupa angka positif. Silakan coba lagi.");
                    scanner.nextLine(); // Membersihkan buffer
                }
            } while (!inputHargaValid);

            ProdukMakeUp produkUpdate = daftarProduk.get(indeks);
            produkUpdate.setNamaProduk(nama);
            produkUpdate.setHarga(harga);
            System.out.println("Produk berhasil diupdate!");
        } else {
            System.out.println("Indeks produk tidak valid!!!");
        }
    }

    public void hapusProduk(int indeks) {
        if (indeks >= 0 && indeks < daftarProduk.size()) {
            daftarProduk.remove(indeks);
            System.out.println("Produk berhasil dihapus!");
        } else {
            System.out.println("Indeks produk tidak valid!!!");
        }
    }

    public static void main(String[] args) {
        TokoMakeUp toko = new TokoMakeUp(); // Membuat instance dari kelas TokoMakeUp
        int pilihan;

        do {
            System.out.println("\n=========================");
            System.out.println("     Menu Toko MakeUp");
            System.out.println("=========================");
            System.out.println("1. Tambah Produk");
            System.out.println("2. Lihat Produk");
            System.out.println("3. Update Produk");
            System.out.println("4. Hapus Produk");
            System.out.println("5. Keluar");
            System.out.println("=========================");
            System.out.print("Pilih menu: ");

            // Validasi input pilihan
            while (!scanner.hasNextInt()) {
                System.out.println("Masukkan angka!");
                scanner.next();
            }
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline di buffer

            switch (pilihan) {
                case 1:
                    System.out.println("\n=========================");
                    System.out.println("     Tambah Produk");
                    System.out.println("=========================");
                    System.out.print("Masukkan nama produk: ");
                    String nama = scanner.nextLine();
                    double harga = 0;
                    boolean inputHargaValid = false;
                    do {
                        try {
                            System.out.print("Masukkan harga produk: ");
                            harga = scanner.nextDouble();
                            if (harga < 0) {
                                throw new Exception();
                            }
                            inputHargaValid = true;
                        } catch (Exception e) {
                            System.out.println("Harga harus berupa angka positif. Silakan coba lagi.");
                            scanner.nextLine(); // Membersihkan buffer
                        }
                    } while (!inputHargaValid);
                    toko.tambahProduk(nama, harga); // Memanggil metode tambahProduk dari instance toko
                    break;

                case 2:
                    System.out.println("\n=========================");
                    System.out.println("     Daftar Produk");
                    System.out.println("=========================");
                    toko.lihatProduk(); // Memanggil metode lihatProduk dari instance toko
                    break;

                case 3:
                    System.out.println("\n=========================");
                    System.out.println("     Update Produk");
                    System.out.println("=========================");
                    System.out.print("Masukkan indeks produk yang ingin diupdate: ");
                    int indeksUpdate = scanner.nextInt() - 1; // Mengurangi 1 untuk mencocokkan indeks dengan daftar
                    scanner.nextLine(); // Membersihkan newline di buffer
                    toko.updateProduk(indeksUpdate); // Memanggil metode updateProduk dari instance toko
                    break;

                case 4:
                    System.out.println("\n=========================");
                    System.out.println("     Hapus Produk");
                    System.out.println("=========================");
                    System.out.print("Masukkan indeks produk yang ingin dihapus: ");
                    int indeksHapus = scanner.nextInt() - 1; // Mengurangi 1 untuk mencocokkan indeks dengan daftar
                    scanner.nextLine(); // Membersihkan newline di buffer
                    toko.hapusProduk(indeksHapus); // Memanggil metode hapusProduk dari instance toko
                    break;

                case 5:
                    System.out.println("\nTerima kasih telah berkunjung *><*");
                    break;

                default:
                    System.out.println("\nPilihan menu tidak valid. Silakan pilih lagi!!!");
            }
        } while (pilihan != 5);

        scanner.close();
    }
}
