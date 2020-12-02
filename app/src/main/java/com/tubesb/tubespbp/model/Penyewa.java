package com.tubesb.tubespbp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Penyewa implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nama")
    public String nama;

    @ColumnInfo(name = "alamat")
    public String alamat;

    @ColumnInfo(name = "mobil")
    public String mobil;

    @ColumnInfo(name = "hari")
    public String hari;

    @ColumnInfo(name = "tanggal")
    public String tanggal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getMobil() {
        return mobil ;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getHari() {
        return hari ;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getTanggal() {
        return tanggal ;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

}

