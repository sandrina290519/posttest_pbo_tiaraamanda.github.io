package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class cConfig {
    private static Connection connection;

    public static Connection connection() {
        if (connection == null) {
            try {
                // Ganti dengan konfigurasi database Anda
                String url = "jdbc:mysql://localhost:3306/dbkosmetik";
                String user = "root";
                String password = "";

                connection = DriverManager.getConnection(url, user, password);
                // System.out.println("Koneksi ke database berhasil!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Koneksi ke database gagal!");
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Koneksi ke database ditutup!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
