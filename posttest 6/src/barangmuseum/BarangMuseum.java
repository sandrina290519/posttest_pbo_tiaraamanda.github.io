package barangmuseum;

import java.util.Scanner;

public interface BarangMuseum {

    void tambahBarang(Scanner scanner);  
    void lihatDaftarBarang();         
    void updateBarang(Scanner scanner);  
    void hapusBarang(Scanner scanner);       
}