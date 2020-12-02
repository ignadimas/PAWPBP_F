package com.tubesb.tubespbp.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Mobil {
    public String NamaMobil;
    public String JenisTransmisi;
    public int HargaMobil;
    public String jumlahSeat;
    public String imgURL;

    public Mobil(String namaMobil, String jenisTransmisi, int hargaMobil, String jumlahSeat, String imgURL) {
        this.NamaMobil = namaMobil;
        this.JenisTransmisi = jenisTransmisi;
        this.HargaMobil = hargaMobil;
        this.jumlahSeat = jumlahSeat;
        this.imgURL = imgURL;
    }

    public String getNamaMobil() {
        return NamaMobil;
    }

    public void setNamaMobil(String namaMobil) {
        this.NamaMobil = namaMobil;
    }

    public String getJenisTransmisi() {
        return JenisTransmisi;
    }

    public void setJenisTransmisi(String jenisTransmisi) {
        this.JenisTransmisi = jenisTransmisi;
    }

    public int getHargaMobil() {
        return HargaMobil;
    }

    public void setHargaMobil(int hargaMobil) {
        this.HargaMobil = hargaMobil;
    }

    public String getStringHargaMobil() { return String.valueOf(HargaMobil); }

    public void setStringHargaMobil(String hargaMobil) {
        if(!hargaMobil.isEmpty()) {
            this.HargaMobil = Integer.parseInt(hargaMobil);
        }else{
            this.HargaMobil = 0;
        }
    }

    public String getJumlahSeat() {
        return jumlahSeat;
    }

    public void setJumlahSeat(String jumlahSeat) {
        this.jumlahSeat = jumlahSeat;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView, String imgURL) {
        Glide.with(imageView)
                .load(imgURL)
                .into(imageView);
    }
}
