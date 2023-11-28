package com.ardi.projectuas.Model;

public class Produk {
    public String kdBrg, nmbrg, deskripsi, gambar;
    public int harga, stok;

    //Constructor
    public Produk(String kdBrg, String nmbrg, int harga, String deskripsi, int stok, String gambar) {
        this.kdBrg = kdBrg;
        this.nmbrg = nmbrg;
        this.harga = harga;
        this.deskripsi = deskripsi;
        this.stok = stok;
        this.gambar = gambar;
    }

    public String getKdBrg() {
        return kdBrg;
    }

    public void setKdBrg(String kdBrg) {
        this.kdBrg = kdBrg;
    }

    public String getNmbrg() {
        return nmbrg;
    }

    public void setNmbrg(String nmbrg) {
        this.nmbrg = nmbrg;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}
