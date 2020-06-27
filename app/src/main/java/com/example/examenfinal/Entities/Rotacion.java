package com.example.examenfinal.Entities;

import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Rotacion.TABLE_NAME)
public class Rotacion {
    public static final String TABLE_NAME = "rotacion";
    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_ID = BaseColumns._ID;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;


    @ColumnInfo(name = "fecha inicial")
    public String fechaIni;

    @ColumnInfo(name = "fecha final")
    public String fechaFin;



    public Rotacion(long id,  String fechaIni,String fechaFin) {
        this.id = id;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getColumnName() {
        return COLUMN_NAME;
    }

    public static String getColumnId() {
        return COLUMN_ID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
}
