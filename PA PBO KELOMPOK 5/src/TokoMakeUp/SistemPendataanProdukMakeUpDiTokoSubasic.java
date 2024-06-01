package TokoMakeUp;

import com.config.cConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class SistemPendataanProdukMakeUpDiTokoSubasic {
    private static ArrayList<ProdukMakeUp> daftarProduk = new ArrayList<>();
    private static Connection connection = cConfig.connection();

    static {
        // Inisialisasi admin jika belum ada
        initializeAdmin();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            menuRegister(scanner);
        }
    }

    private static void initializeAdmin() {
        try {
            String query = "SELECT COUNT(*) FROM users WHERE username = 'admin'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            if (resultSet.getInt(1) == 0) {
                query = "INSERT INTO users (username, password, role) VALUES ('admin', '123', 'admin')";
                statement = connection.prepareStatement(query);
                statement.executeUpdate();
                System.out.println("Admin berhasil diinisialisasi dengan username 'admin' dan password '123'.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void menuRegister(Scanner scanner) {
        int opsi = 0;
    
        do {
            System.out.println("======================================");
            System.out.println(" +    +    +    +    +    +    +    +" );
            System.out.println("Selamat datang di Toko MakeUp Subasic!");
            System.out.println(" +    +    +    +    +    +    +    + ");
            System.out.println("======================================");
            System.out.println("| 1. Registrasi                      |");
            System.out.println("| 2. Login                           |");
            System.out.println("| 3. Keluar                          |");
            System.out.println("======================================");
            System.out.print("Pilih opsi: ");
            try {
                opsi = scanner.nextInt();
                scanner.nextLine(); // Membersihkan newline di buffer
    
                switch (opsi) {
                    case 1:
                        registrasi(scanner);
                        break;
                    case 2:
                    System.out.println("=====================");
                    System.out.println(">>>>> Login <<<<<");
                    System.out.println("=====================");
                    System.out.print("Masukkan username: ");
                    String username = scanner.nextLine();
                    System.out.print("Masukkan password: ");
                    String password = scanner.nextLine();
                    if (authenticate(username, password)) {
                        System.out.println("Login berhasil!");
                        if (isAdmin(username)) {
                            menuAdmin(scanner);
                        } else {
                            menuUser(scanner, username);
                        }
                        return; // Keluar dari metode menuRegister setelah login berhasil
                    } else {
                        System.out.println("Username atau password salah. Silakan coba lagi.");
                    }
                    break;
                    case 3:
                        System.out.println("Terima kasih telah mengunjungi Toko MakeUp Subasic! Sampai jumpa lagi!");
                        System.exit(0);  // Mengakhiri program
                        break;
                    default:
                        System.out.println("Pilihan tidak benar, silakan masukkan kembali.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Input tidak valid, silakan masukkan kembali.");
                scanner.nextLine(); // Membersihkan buffer
            }
        } while (opsi < 1 || opsi > 3);
    }
    
    

    private static void registrasi(Scanner scanner) {
        System.out.println("======================");
        System.out.println(">>>>> Registrasi <<<<<");
        System.out.println("======================");
        String username;
        while (true) {
            System.out.print("Masukkan username (tekan 0 untuk kembali ke menu utama): ");
            username = scanner.nextLine();
            if ("0".equals(username)) {
                System.out.println("Kembali ke menu utama.");
                return; // Kembali ke menu utama
            } else if (isUsernameExist(username)) {
                System.out.println("Username sudah digunakan. Silakan pilih username lain.");
            } else {
                break;
            }
        }
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();
    
        try {
            String query = "INSERT INTO users (username, password, role) VALUES (?, ?, 'user')";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            System.out.println("Registrasi berhasil! Silakan login.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Registrasi gagal.");
        }
    }
    
    
    
    

    private static boolean isUsernameExist(String username) {
        try {
            String query = "SELECT COUNT(*) FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean authenticate(String username, String password) {
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isAdmin(String username) {
        try {
            String query = "SELECT role FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return "admin".equals(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void menuAdmin(Scanner scanner) {
        int pilihan;

        do {
            System.out.println("=======================================");
            System.out.println(" +   + Menu Admin Di Toko MakeUp +   + ");
            System.out.println("=======================================");
            System.out.println("1. Tambah Produk"                       );
            System.out.println("2. Lihat Produk"                        );
            System.out.println("3. Update Produk"                       );
            System.out.println("4. Hapus Produk"                        );
            System.out.println("5. Keluar"                              );
            System.out.println("=======================================");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();
            switch (pilihan) {
                case 1:
                    tambahProduk(scanner);
                    break;
                case 2:
                    lihatProduk();
                    break;
                case 3:
                    updateProduk(scanner);
                    break;
                case 4:
                    hapusProduk(scanner);
                    break;
                case 5:
                    System.out.println("\nTerima kasih telah berkunjung *><*");
                    return;
                default:
                    System.out.println("\nPilihan menu tidak valid. Silakan pilih lagi!!!");
            }
        } while (true);
    }

    private static void menuUser(Scanner scanner, String username) {
        Keranjang keranjang = new Keranjang(username);
        int pilihan;
    
        do {
            System.out.println("===================================");
            System.out.println(" +   + Menu User Toko MakeUp +   + ");
            System.out.println("===================================");
            System.out.println("1. Lihat Produk"                    );
            System.out.println("2. Tambah Produk ke Keranjang"      );
            System.out.println("3. Lihat Keranjang"                 );
            System.out.println("4. Bayar"                           );
            System.out.println("5. Keluar"                          );
            System.out.println("===================================");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();
            switch (pilihan) {
                case 1:
                    lihatProduk();
                    break;
                case 2:
                    tambahKeKeranjang(scanner, keranjang, username);
                    break;
                case 3:
                    keranjang.lihatKeranjang();
                    break;
                case 4:
                    keranjang.bayar(scanner);
                    break;
                case 5:
                    System.out.println("\nTerima kasih telah berkunjung *><*");
                    return;
                default:
                    System.out.println("\nPilihan menu tidak valid. Silakan pilih lagi!!!");
            }
        } while (true);
    }

    private static void tambahProduk(Scanner scanner) {
        System.out.println("=========================");
        System.out.println(">>>>> Tambah Produk <<<<<");
        System.out.println("=========================");
        System.out.print("Masukkan nama produk: ");
        String nama = scanner.nextLine();
    
        // Periksa apakah nama produk sudah ada dalam database
        if (isProdukExist(nama)) {
            System.out.println("Produk dengan nama tersebut sudah ada. Silakan gunakan nama produk lain.");
            return;
        }
    
        double harga = 0;
        boolean inputHargaValid = false;
        do {
            try {
                System.out.print("Masukkan harga produk: ");
                harga = scanner.nextDouble();
                if (harga <= 0) {
                    System.out.println("Harga harus lebih dari 0. Silakan coba lagi.");
                } else {
                    inputHargaValid = true;
                }
            } catch (Exception e) {
                System.out.println("Harga harus berupa angka. Silakan coba lagi.");
                scanner.nextLine();
            }
        } while (!inputHargaValid);
    
        double diskon = 0;
        boolean inputDiskonValid = false;
        String inputDiskon;
        while (!inputDiskonValid) {
            try {
                System.out.print("Apakah produk memiliki diskon? (y/n): ");
                inputDiskon = scanner.next();
                if (inputDiskon.equalsIgnoreCase("y")) {
                    System.out.print("Masukkan persentase diskon: ");
                    diskon = scanner.nextDouble();
                    if (diskon < 0 || diskon >= 100) {
                        System.out.println("Diskon harus di antara 0 dan 100. Silakan coba lagi.");
                    } else {
                        inputDiskonValid = true;
                    }
                } else if (inputDiskon.equalsIgnoreCase("n")) {
                    inputDiskonValid = true;
                } else {
                    System.out.println("Masukkan y atau n.");
                }
            } catch (Exception e) {
                System.out.println("Diskon harus berupa angka. Silakan coba lagi.");
                scanner.nextLine();
            }
        }
    
        // Hitung harga setelah diskon jika ada
        double hargaSetelahDiskon = harga;
        if (diskon > 0) {
            hargaSetelahDiskon = harga - (harga * diskon / 100);
        }
    
        try {
            String query = "INSERT INTO tbkosmetik (namabarang, hargabarang, diskonbarang) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nama);
            statement.setDouble(2, harga);
            statement.setDouble(3, diskon);
            statement.executeUpdate();
            System.out.println("Produk berhasil ditambahkan!");
            if (diskon > 0) {
                System.out.println("Harga setelah diskon: " + hargaSetelahDiskon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal menambahkan produk.");
        }
    }
    
    private static boolean isProdukExist(String nama) {
        try {
            String query = "SELECT COUNT(*) FROM tbkosmetik WHERE namabarang = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nama);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    
    private static void lihatProduk() {
        System.out.println("=========================");
        System.out.println(">>>>> Daftar Produk <<<<<");
        System.out.println("=========================");
    
        try {
            String query = "SELECT * FROM tbkosmetik";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                String nama = resultSet.getString("namabarang");
                double harga = resultSet.getDouble("hargabarang");
                double diskon = resultSet.getDouble("diskonbarang");
                double hargaSetelahDiskon = harga - (harga * diskon / 100); // Hitung harga setelah diskon
    
                if (diskon > 0) {
                    System.out.println("Produk Diskon: " + nama + " - Harga Setelah Diskon: " + hargaSetelahDiskon + " - Diskon: " + diskon + "%");
                } else {
                    System.out.println("Produk Biasa: " + nama + " - Harga: " + harga);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal mengambil data produk.");
        }
    }
    

    private static void updateProduk(Scanner scanner) {
        System.out.println("=========================");
        System.out.println(">>>>> Update Produk <<<<<");
        System.out.println("=========================");
        System.out.print("Masukkan nama produk yang ingin diupdate: ");
        String nama = scanner.nextLine();

        try {
            String query = "SELECT * FROM tbkosmetik WHERE namabarang = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nama);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("Produk tidak ditemukan.");
                return;
            }

            double harga = 0;
            boolean inputHargaValid = false;
            do {
                try {
                    System.out.print("Masukkan harga produk baru: ");
                    harga = scanner.nextDouble();
                    if (harga <= 0) {
                        System.out.println("Harga harus lebih dari 0. Silakan coba lagi.");
                    } else {
                        inputHargaValid = true;
                    }
                } catch (Exception e) {
                    System.out.println("Harga harus berupa angka. Silakan coba lagi.");
                    scanner.nextLine();
                }
            } while (!inputHargaValid);

            double diskon = -1; 
            boolean inputDiskonValid = false;
            while (!inputDiskonValid) {
                try {
                    System.out.print("Masukkan persentase diskon baru: ");
                    diskon = Double.parseDouble(scanner.next());
                    if (diskon < 0) {
                        System.out.println("Diskon harus lebih dari atau sama dengan 0. Silakan cobalagi.");
                    } else {
                        inputDiskonValid = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Diskon harus berupa angka. Silakan coba lagi.");
                }
            }

            query = "UPDATE tbkosmetik SET hargabarang = ?, diskonbarang = ? WHERE namabarang = ?";
            statement = connection.prepareStatement(query);
            statement.setDouble(1, harga);
            statement.setDouble(2, diskon);
            statement.setString(3, nama);
            statement.executeUpdate();

            System.out.println("Produk berhasil diupdate!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal mengupdate produk.");
        }
    }

    private static void hapusProduk(Scanner scanner) {
        System.out.println("========================");
        System.out.println(">>>>> Hapus Produk <<<<<");
        System.out.println("========================");
        System.out.print("Masukkan nama produk yang ingin dihapus: ");
        String nama = scanner.nextLine();

        try {
            String query = "DELETE FROM tbkosmetik WHERE namabarang = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nama);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Produk berhasil dihapus!");
            } else {
                System.out.println("Produk tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal menghapus produk.");
        }
    }

    private static void tambahKeKeranjang(Scanner scanner, Keranjang keranjang, String username) {
        System.out.print("Masukkan nama produk yang ingin ditambahkan ke keranjang: ");
        String nama = scanner.nextLine();
    
        try {
            String query = "SELECT * FROM tbkosmetik WHERE namabarang = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nama);
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                String namaProduk = resultSet.getString("namabarang");
                double harga = resultSet.getDouble("hargabarang");
                double diskon = resultSet.getDouble("diskonbarang");

                ProdukMakeUp produk;
                if (diskon > 0) {
                    produk = new ProdukMakeUpDiskon(namaProduk, harga, diskon);
                } else {
                    produk = new ProdukMakeUpBiasa(namaProduk, harga);
                }
    
                keranjang.tambahProduk(produk);
                System.out.println("Produk berhasil ditambahkan ke keranjang!");
            } else {
                System.out.println("Produk tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal menambahkan produk ke keranjang.");
        }
    }
}
