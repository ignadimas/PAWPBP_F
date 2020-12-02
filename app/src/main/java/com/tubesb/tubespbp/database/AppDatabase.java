package com.tubesb.tubespbp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.tubesb.tubespbp.model.Penyewa;

@Database(entities = {Penyewa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PenyewaDao penyewaDao();
}
