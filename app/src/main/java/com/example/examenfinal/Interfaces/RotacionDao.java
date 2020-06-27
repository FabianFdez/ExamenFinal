package com.example.examenfinal.Interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.examenfinal.Entities.Potrero;
import com.example.examenfinal.Entities.Rotacion;

import java.util.List;

@Dao
public interface RotacionDao {
    //seleccionar todo
    @Query("SELECT * FROM "+ Rotacion.TABLE_NAME)
    List<Rotacion> getAllR();



    //eliminar
    @Query("DELETE FROM " + Rotacion.TABLE_NAME + " WHERE " + Rotacion.COLUMN_ID + " = :ide")
    int deleteByIdR(long ide);

    //actualizar
    @Update
    int updateEntidadR(Rotacion obj);


    //insertar 2
    @Insert
    long insertR(Rotacion rotar);

}
