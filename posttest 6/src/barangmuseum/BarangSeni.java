package barangmuseum;

import java.util.Scanner;

public class BarangSeni extends Barang {
    private String teknik;

    public BarangSeni(String nama, String jenis, int tahun, String teknik) {
        super(nama, jenis, tahun);
        this.teknik = teknik;
    }

    public String getTeknik() {
        return teknik;
    }

    public void setTeknik(String teknik) {
        this.teknik = teknik;
    }
}
