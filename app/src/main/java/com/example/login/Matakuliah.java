package com.example.login;

public class Matakuliah {
    private int sks;
    private String nama;
    private String dosen;

    public Matakuliah(int sks, String nama, String dosen) {
        this.sks = sks;
        this.nama = nama;
        this.dosen = dosen;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getSks() {
        return sks;
    }

    public void setsks(int sks) {
        this.sks = sks;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

}
