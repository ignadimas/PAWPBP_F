package com.tubesb.tubespbp.model;

import java.util.ArrayList;

public class DaftarMahasiswa {
    public ArrayList<Mahasiswa> MAHASISWA;

    public DaftarMahasiswa(){
        MAHASISWA = new ArrayList();
        MAHASISWA.add(NANDA);
        MAHASISWA.add(DIMAS);
        MAHASISWA.add(RAYMOND);
    }

    public static final Mahasiswa NANDA = new Mahasiswa("160708701", "Paternus Aditya Resky Fernanda",
            "Fakultas Teknologi Industri", "Teknik Informatika", "160708701@students.uajy.ac.id");

    public static final Mahasiswa DIMAS = new Mahasiswa("180709937", "Ignatius Dimas Dwi Sulistya",
            "Fakultas Teknologi Industri", "Teknik Informatika", "180709937@students.uajy.ac.id");

    public static final Mahasiswa RAYMOND = new Mahasiswa("180709962", "Raymond Ibrahim",
            "Fakultas Teknologi Industri", "Teknik Informatika", "180709962@students.uajy.ac.id");

}
