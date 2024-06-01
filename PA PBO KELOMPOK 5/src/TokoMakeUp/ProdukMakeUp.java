package TokoMakeUp;

public abstract class ProdukMakeUp {
    private String namaProduk;
    private double harga;

    public ProdukMakeUp(String namaProduk, double harga) {
        this.namaProduk = namaProduk;
        this.harga = harga;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    // Abstract method to calculate price
    public abstract double hitungHarga();

    @Override
    public abstract String toString();
    // Metode untuk mendapatkan jumlah barang
    public abstract int getJumlah();
}