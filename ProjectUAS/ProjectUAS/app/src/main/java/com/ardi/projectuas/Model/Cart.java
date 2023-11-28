package com.ardi.projectuas.Model;

public class Cart {
    public String kdCart, nmBrg, username;
    public int jmlBeli;

    public Cart(String kdCart, String username, String nmBrg, int jmlBeli) {
        this.kdCart = kdCart;
        this.username = username;
        this.nmBrg = nmBrg;
        this.jmlBeli = jmlBeli;
    }

    public String getKdCart() {
        return kdCart;
    }

    public void setKdCart(String kdCart) {
        this.kdCart = kdCart;
    }

    public String getNmBrg() {
        return nmBrg;
    }

    public void setNmBrg(String nmBrg) {
        this.nmBrg = nmBrg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getJmlBeli() {
        return jmlBeli;
    }

    public void setJmlBeli(int jmlBeli) {
        this.jmlBeli = jmlBeli;
    }
}
