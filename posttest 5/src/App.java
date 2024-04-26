import java.util.Scanner;
import barangmuseum.Museum; 

public abstract class App {
    private static final Museum museum = new Museum();
    private static final Scanner scanner = new Scanner(System.in);
    private static final int MENU_KELUAR = 5;

    public static void main(String[] args) {
        int pilihan;

        do {
            System.out.println("\n====== Sistem Pendataan Barang Museum Mulawarman ======");
            System.out.println("1. Tambah Barang Baru");
            System.out.println("2. Lihat Daftar Barang Museum");
            System.out.println("3. Update Barang Museum");
            System.out.println("4. Hapus Barang Museum");
            System.out.println("5. Keluar dari Program");
            System.out.print("Pilih nomor menu (1-5): ");

            if (scanner.hasNextInt()) {
                pilihan = scanner.nextInt();
                switch (pilihan) {
                    case 1:
                        museum.tambahBarang();
                        break;
                    case 2:
                        museum.lihatDaftarBarang();
                        break;
                    case 3:
                        System.out.print("Masukkan nomor barang yang ingin diupdate: ");
                        int indexUpdate = scanner.nextInt();
                        System.out.print("Masukkan nama barang baru: ");
                        String namaUpdate = scanner.next();
                        System.out.print("Masukkan jenis barang baru: ");
                        String jenisUpdate = scanner.next();
                        System.out.print("Masukkan tahun barang baru: ");
                        int tahunUpdate = scanner.nextInt();
                        museum.updateBarang(indexUpdate, namaUpdate, jenisUpdate, tahunUpdate);
                        break;
                    case 4:
                        System.out.print("Masukkan nomor barang yang ingin dihapus: ");
                        int indexHapus = scanner.nextInt();
                        museum.hapusBarang(indexHapus);
                        break;
                    case MENU_KELUAR:
                        System.out.println("Terima kasih Telah menggunakan Program kami:)");
                        break;
                    default:
                        System.out.println("Pilihan yang anda masukkan salah. Harap masukkan nomor menu 1-5");
                        break;
                }
            } else {
                System.out.println("Maaf, masukkan nomor yang benar.");
                scanner.next();
                pilihan = -1;
            }

        } while (pilihan != MENU_KELUAR);

        scanner.close();
    }
}
