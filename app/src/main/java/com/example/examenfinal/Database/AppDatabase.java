package com.example.examenfinal.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.examenfinal.Entities.Potrero;
import com.example.examenfinal.Entities.Rotacion;
import com.example.examenfinal.Interfaces.PotreroDao;
import com.example.examenfinal.Interfaces.RotacionDao;

@Database(entities = {Potrero.class, Rotacion.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    @SuppressWarnings("WeakerAccess")
    public abstract PotreroDao potreroDao();

    public abstract RotacionDao rotacionDao();

    private static AppDatabase sIntance;

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (sIntance == null) {
            sIntance =
                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class).allowMainThreadQueries().build();
        }
        return sIntance;
    }

    public static void destroyInstance() {
        sIntance = null;
    }
}
