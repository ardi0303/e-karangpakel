package com.ardi.projectuas.Model;

public class History {
    public String tglBeli, username, total, status, resi;
    int idHistory;

    public History(int idHistory, String tglBeli, String username, String total, String status, String resi) {
        this.idHistory = idHistory;
        this.tglBeli = tglBeli;
        this.username = username;
        this.total = total;
        this.status = status;
        this.resi = resi;
    }

    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }

    public String getTglBeli() {
        return tglBeli;
    }

    public void setTglBeli(String tglBeli) {
        this.tglBeli = tglBeli;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResi() {
        return resi;
    }

    public void setResi(String resi) {
        this.resi = resi;
    }
}
