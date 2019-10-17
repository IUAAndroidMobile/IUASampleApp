package com.nicolasfanin.IUASampleApp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "color_table")
public class Color {

    @PrimaryKey
    @NonNull
    private String name;
    private String hex;

    public Color(String name, String hex) {
        this.name = name;
        this.hex = hex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }
}
