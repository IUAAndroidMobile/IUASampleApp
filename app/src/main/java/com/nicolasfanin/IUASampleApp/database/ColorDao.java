package com.nicolasfanin.IUASampleApp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.nicolasfanin.IUASampleApp.data.Color;

import java.util.List;

@Dao
public interface ColorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Color color);

    @Query("DELETE FROM color_table")
    void deleteAll();

    @Query("SELECT * FROM color_table ORDER BY name ASC")
    List<Color> getAllColorsOrdered();

    @Query("SELECT * FROM color_table WHERE name == :colorName")
    Color getColorByName(String colorName);
}
