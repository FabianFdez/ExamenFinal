package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examenfinal.Config.Constante;
import com.example.examenfinal.Database.AppDatabase;
import com.example.examenfinal.Entities.Potrero;

public class CreateActivity extends AppCompatActivity {

    EditText nombre, fecha, area, img, video;
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        nombre = (EditText)findViewById(R.id.txt_PotreroName);
        fecha = (EditText)findViewById(R.id.txt_Date);
        area = (EditText)findViewById(R.id.txt_Area);
        img = (EditText)findViewById(R.id.namePhoto);
        video = (EditText)findViewById(R.id.editTextVideo);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Constante.BD_NAME).allowMainThreadQueries().build();

    }

    public void savePotrero(View view) {
        Potrero obj = new Potrero();
        obj.setNombre(nombre.getText().toString());
        obj.setFecha(fecha.getText().toString());
        obj.setArea(area.getText().toString());
        obj.setImg(img.getText().toString());
        obj.setVideo(video.getText().toString());

        long resul = db.potreroDao().insert(obj);
        if (resul > 0 ){
            Toast.makeText(CreateActivity.this,"Agregado Correctamente",Toast.LENGTH_LONG).show();
            startActivity(new Intent(CreateActivity.this, MainActivity.class));
        }else{
            Toast.makeText(CreateActivity.this,"No ha sido Agregado",Toast.LENGTH_LONG).show();
        }
    }

    public void tomarVideo(View view) {
    }
}