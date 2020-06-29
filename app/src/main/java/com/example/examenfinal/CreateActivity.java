package com.example.examenfinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.examenfinal.Config.Constante;
import com.example.examenfinal.Database.AppDatabase;
import com.example.examenfinal.Entities.Potrero;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateActivity extends AppCompatActivity {

    EditText nombre, fecha, area, video;
    EditText img;
    Button fotobtn, btnVideo;
    ImageView imagenView;
    ImageView videoView;
    String nombreF = " ";
    String nombreV = " ";
    AppDatabase db;
    public static final  String CARPETA_PRINCIPAL = "MisImagenes";
    public static final String CARPETA_IMAGEN = "imagenes";
    public static final String DIRECTORIO = CARPETA_PRINCIPAL + CARPETA_IMAGEN;
    public String path,pathVideo, urlImage;
    public Uri uriPot;
    public Uri uriVideo;
    public static final int GALLERY = 40;
    public  static  final int COD_FOTO = 200;
    public  static  final int COD_VIDEO = 300;
    public static final  String CARPETA_PRINCIPAL_VIDEO = "MisVideos";
    public static final String CARPETA_VIDEO = "videos";
    public static final String DIRECTORIO_VIDEO= CARPETA_PRINCIPAL_VIDEO + CARPETA_VIDEO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        nombre = (EditText)findViewById(R.id.txt_PotreroName);
        fecha = (EditText)findViewById(R.id.txt_Date);
        area = (EditText)findViewById(R.id.txt_Area);
        img = (EditText)findViewById(R.id.namePhoto);
        video = (EditText)findViewById(R.id.editTextVideo);
        fotobtn = (Button)findViewById(R.id.btnTakePhoto);
        btnVideo = (Button)findViewById(R.id.buttonVideo);
        imagenView = (ImageView)findViewById(R.id.ImageA);
        videoView = (ImageView)findViewById(R.id.video);
        Date calendar = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String date = dateFormat.format(calendar);
        fecha.setText(date);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Constante.BD_NAME).allowMainThreadQueries().build();
        if(ContextCompat.checkSelfPermission(CreateActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(CreateActivity.this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
            }, 0);
        }
        /** END*/

        fotobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }


        });

        imagenView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(urlImage!=null){
                    Intent intent = new Intent(CreateActivity.this, ViewFoto.class);
                    intent.putExtra("filename",urlImage);
                    startActivity(intent);
                }else {
                    Toast.makeText(CreateActivity.this,"Debes agregar una foto",Toast.LENGTH_LONG).show();
                }
            }
        });
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pathVideo!=null){
                    Intent intent = new Intent(CreateActivity.this, FullscreenActivity.class);
                    intent.putExtra("filename",pathVideo);
                    startActivity(intent);
                }else {
                    Toast.makeText(CreateActivity.this,"Debes agregar un video",Toast.LENGTH_LONG).show();
                }
            }
        });

       btnVideo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               takeVideo();
           }
       });
    }
    private void takePhoto() {
        File miFile = new File(getExternalFilesDir(Environment.DIRECTORY_DCIM),DIRECTORIO);
        boolean isCreada = miFile.exists();

        if (isCreada==false){
            isCreada=miFile.mkdirs();
        }
        if (isCreada==true) {
            nombreF = (System.currentTimeMillis() / 1000) + ".jpg";
            miFile.mkdirs();
        }
            path= getExternalFilesDir(DIRECTORIO)+File.separator+nombreF;
            File image= new File(path);
            Intent intent = null;
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                String authorities = this.getPackageName() + ".provider";
                Uri imageUri = FileProvider.getUriForFile(this, authorities,image);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            }else {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(image));
            }



        startActivityForResult(intent, COD_FOTO);
    }

    private void takeVideo() {
        File miFileV = new File(Environment.getExternalStorageState(),DIRECTORIO);
        boolean isCreada = miFileV.exists();

        if (isCreada==false){
            isCreada=miFileV.mkdirs();
        }
        if (isCreada==true) {
            nombreV = (System.currentTimeMillis() / 1000) + ".mp4";
            miFileV.mkdirs();
        }
        pathVideo= getExternalFilesDir(DIRECTORIO_VIDEO)+File.separator+nombreV;
        File video= new File(pathVideo);
        Intent intentVideo = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            String authoritie = this.getPackageName() + ".provider";
            Uri imageUri = FileProvider.getUriForFile(this, authoritie,video);
            intentVideo.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        }else {
            intentVideo.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(video));
        }



        startActivityForResult(intentVideo, COD_VIDEO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == COD_FOTO){ // de donde viene la respuesta y si fue exitosa

            MediaScannerConnection.scanFile(CreateActivity.this, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String path, Uri uri) {
                    /** GET AND SET THE URI FOR THE PHOTO AND THE PATH*/
                    uriPot = uri;
                    urlImage = path;
                    img.setText(nombreF);
                    /** SHOW PHOTO TAKEN */
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                        imagenView.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            /** END*/
        }
        else if (resultCode == RESULT_OK && requestCode == COD_VIDEO){
            MediaScannerConnection.scanFile(CreateActivity.this, new String[]{pathVideo}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String path, Uri uri) {
                    uriVideo = uri;
                    pathVideo = path;
                    video.setText(nombreV);
                }
            });
        }

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

}