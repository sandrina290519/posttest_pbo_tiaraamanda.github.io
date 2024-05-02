package barangmuseum;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static final Museum museum = new Museum(); // Importing the Museum class
    private static final Scanner scanner = new Scanner(System.in);
    private static final int MENU_KELUAR = 5;

    public static void main(String[] args) {
        Museum.printWelcomeMessage();

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
                scanner.nextLine(); // Consume newline
                switch (pilihan) {
                    case 1:
                        museum.tambahBarang(scanner);
                        break;
                    case 2:
                        museum.lihatDaftarBarang();
                        break;
                    case 3:
                        museum.updateBarang(scanner);
                        break;
                    case 4:
                        museum.hapusBarang(scanner);
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