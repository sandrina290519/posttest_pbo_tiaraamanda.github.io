package barangmuseum;

public class BarangSeni extends Barang {
    public BarangSeni(String nama, String jenis, int tahun) {
        super(nama, jenis, tahun);
    }

    // override untuk method toString, Override toString method
    @Override
    public String toString() {
        return "[Barang Seni] " + super.toString();
    }
}