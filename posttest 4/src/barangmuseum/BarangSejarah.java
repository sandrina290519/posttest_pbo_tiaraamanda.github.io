package barangmuseum;

public class BarangSejarah extends Barang {
    public BarangSejarah(String nama, String jenis, int tahun) {
        super(nama, jenis, tahun);
    }

    // override untuk method toString, Override toString method
    @Override
    public String toString() {
        return "[Barang Sejarah] " + super.toString();
    }
}