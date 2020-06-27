package com.example.examenfinal.Interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.examenfinal.Entities.Potrero;

import java.util.List;

@Dao
public interface PotreroDao {
    //seleccionar todo
    @Query("SELECT * FROM "+ Potrero.TABLE_NAME)
    List<Potrero> getAll();



    //eliminar
    @Query("DELETE FROM " + Potrero.TABLE_NAME + " WHERE " + Potrero.COLUMN_ID + " = :ide")
    int deleteById(long ide);

    //actualizar
    @Update
    int updateEntidad(Potrero obj);


    //insertar 2
    @Insert
    long insert(Potrero potreros);



}
