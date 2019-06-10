package com.example.belikov.instagramclient.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.example.belikov.instagramclient.model.entity.Hit;
import com.example.belikov.instagramclient.model.entity.HitDao;

@Database(entities = {Hit.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HitDao hitDao();
}
