package barangmuseum;

public class BarangSejarah extends Barang {
    public BarangSejarah(String nama, String jenis, int tahun) {
        super(nama, jenis, tahun);
    }

    @Override
    public String toString() {
        return "[Barang Sejarah] " + super.toString();
    }
}
