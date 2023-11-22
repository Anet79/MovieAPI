package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MovieModelClass implements Serializable {
    /*if needed -> the name of the variable differs from the JSON key we can use annotation
     @serializedName("JSON KEY")

     */
    @SerializedName("vote_average")
    String id;
    @SerializedName("title")
    String name;
    @SerializedName("poster_path")
    String img;
    @SerializedName("release_date")
    String releaseDate;

    public MovieModelClass(String id, String name, String img, String releaseDate) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.releaseDate = releaseDate;
    }

    public MovieModelClass() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
