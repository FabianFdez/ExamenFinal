package com.example.examenfinal.Entities;

import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Blob;
import java.sql.Date;

@Entity(tableName = Potrero.TABLE_NAME)
public class Potrero {
    public static final String TABLE_NAME = "potrero";
    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_ID = BaseColumns._ID;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;


    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "area")
    public String area;

    @ColumnInfo(name = "fecha")
    public String fecha;

    @ColumnInfo(name = "foto")
    public String img;

    @ColumnInfo(name = "video")
    public String video;

    public Potrero(long id, String nombre, String fecha,String area, String img, String video) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.area = area;
        this.img = img;
        this.video = video;
    }

    public Potrero() {

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
