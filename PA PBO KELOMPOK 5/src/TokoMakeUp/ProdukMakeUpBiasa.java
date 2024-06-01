package TokoMakeUp;

public class ProdukMakeUpBiasa extends ProdukMakeUp {
    
    // Konstruktor untuk inisialisasi nama produk dan harga
    public ProdukMakeUpBiasa(String namaProduk, double harga) {
        super(namaProduk, harga);
    }

    // Implementasi metode toString() untuk mendapatkan representasi string dari objek
    @Override
    public String toString() {
        return "Produk Biasa: " + getNamaProduk() + " - Harga: " + getHarga();
    }
}
