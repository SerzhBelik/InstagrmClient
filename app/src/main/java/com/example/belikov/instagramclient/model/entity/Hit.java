package com.example.belikov.instagramclient.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "room_database")
public class Hit {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @Expose
    @SerializedName("webformatURL")
    public String webformatURL;

    @Expose
    @SerializedName("user")
    public String user;

    @Expose
    @SerializedName("likes")
    public Integer likes;

    @Expose
    @SerializedName("favorites")
    public Integer favorites;

    @Expose
    @SerializedName("comments")
    public Integer comments;

    @Expose
    public boolean isLiked = false;

    @Expose
    public boolean isFavorite = false;

}
