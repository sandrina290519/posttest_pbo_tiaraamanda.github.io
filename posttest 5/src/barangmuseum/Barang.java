package barangmuseum;
public abstract class Barang {
    private String nama;
    private String jenis;
    private int tahun;

    public Barang(String nama, String jenis, int tahun) {
        this.nama = nama;
        this.jenis = jenis;
        this.tahun = tahun;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }
}
