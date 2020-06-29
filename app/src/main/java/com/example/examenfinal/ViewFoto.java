package com.example.examenfinal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewFoto extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_full);
        String fName = getIntent().getStringExtra("filename");
        String path = fName;
        Bitmap bm = BitmapFactory.decodeFile(path);

        ImageView imgDisplay;
        Button btnClose;
        imgDisplay = findViewById(R.id.imgDisplay);
        btnClose = findViewById(R.id.btnClose);
        imgDisplay.setImageBitmap(bm);
        btnClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ViewFoto.this.finish();
            }
        });
    }
}
