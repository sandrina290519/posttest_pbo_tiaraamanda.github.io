package TokoMakeUp;

public class ProdukMakeUpDiskon extends ProdukMakeUp {
    private double diskon;

    public ProdukMakeUpDiskon(String namaProduk, double harga, double diskon) {
        super(namaProduk, harga);
        this.diskon = diskon;
    }

    public double getDiskon() {
        return diskon;
    }

    // Override method to calculate price after discount
    @Override
    public double hitungHarga() {
        double hargaSetelahDiskon = super.getHarga() - (super.getHarga() * diskon / 100);
        return hargaSetelahDiskon;
    }

    @Override
    public String toString() {
        return "Produk Diskon: " + getNamaProduk() + " - Harga: " + getHarga() + " - Diskon: " + getDiskon();
    }
}
