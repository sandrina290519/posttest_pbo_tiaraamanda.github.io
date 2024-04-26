package barangmuseum;

import java.util.Scanner;

public class BarangSeni extends Barang {
    private String teknik;

    public BarangSeni(String nama, String jenis, int tahun) {
        super(nama, jenis, tahun);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan teknik barang seni: ");
        this.teknik = scanner.nextLine();
    }

    public String getTeknik() {
        return teknik;
    }

    public void setTeknik(String teknik) {
        this.teknik = teknik;
    }
}
