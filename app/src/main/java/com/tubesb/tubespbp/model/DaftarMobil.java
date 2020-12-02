package com.tubesb.tubespbp.model;

import java.util.ArrayList;

public class DaftarMobil {
    public ArrayList<Mobil> MOBIL;

    public DaftarMobil(){
        MOBIL = new ArrayList();
        MOBIL.add(AVANZA);
        MOBIL.add(HRV);
        MOBIL.add(INNOVA);
        MOBIL.add(FORTUNER);
        MOBIL.add(CRV);
    }

    public static final Mobil AVANZA = new Mobil("Toyota Avanza", "Otomatis",
            100000, "2" ,"https://www.semisena.com/wp-content/uploads/2014/01/harga-avanza-bekas.jpg");

    public static final Mobil HRV = new Mobil("Honda HRV", "Manual",
            200000, "4" ,"https://www.honda-indonesia.com/uploads/images/models/colors/bjVX2LSdiN2BNWwtAuvC_honda_id_white_orchid_pearl....png");

    public static final Mobil INNOVA = new Mobil("Toyota Innova", "Otomatis",
            200000, "2" ,"https://imgd.aeplcdn.com/0x0/n/cw/ec/20623/innova-crysta-exterior-right-front-three-quarter.jpeg");

    public static final Mobil FORTUNER = new Mobil("Toyota Fortuner", "Manual",
            250000, "8" , "https://upload.wikimedia.org/wikipedia/commons/6/66/2015_Toyota_Fortuner_%28New_Zealand%29.jpg");

    public static final Mobil CRV = new Mobil("Honda CRV", "Otomatis",
            180000, "8" ,"https://upload.wikimedia.org/wikipedia/commons/c/c3/2018_Honda_CR-V_%28RW_MY18%29_%2BSport_2WD_wagon_%282018-10-22%29_01.jpg");

}
