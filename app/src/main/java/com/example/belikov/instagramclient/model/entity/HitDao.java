package com.example.belikov.instagramclient.model.entity;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface HitDao {

    @Query("SELECT * FROM  room_database")
    Single<List<Hit>> getAll();

    @Query("SELECT * FROM  room_database WHERE id = :id")
    Single<List<Hit>> getAllById(int id);

    @Insert
    long insert(Hit hit);

    @Insert
    List<Long> insertList(List<Hit> users);

    @Delete
    int delete(Hit hit);

    @Update
    int update(Hit hit);

}