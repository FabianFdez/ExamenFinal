package com.example.examenfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examenfinal.Config.Constante;
import com.example.examenfinal.Database.AppDatabase;
import com.example.examenfinal.Entities.Potrero;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    AppDatabase db;
    TextView txtPotrero;

    private List<Potrero> listpotreros;
    ListView viewPotrero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPotrero = (TextView)findViewById(R.id.textPotrero);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Constante.BD_NAME).allowMainThreadQueries().build();
        int cant = db.potreroDao().count();
        Toast.makeText(this, "Datos Creados: "+cant+" Potreros",Toast.LENGTH_SHORT).show();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });
        /*txtPotrero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView.ViewHolder viewHolder = null;
                int pos = viewHolder.getAdapterPosition();
                Potrero potrero = adapter.getPosition(pos);
                Toast.makeText(MainActivity.this, "Borrando"+potrero.getId(), Toast.LENGTH_LONG).show();

                db.potreroDao().deleteById(pos);
            }
        });*/
        FloatingActionButton list = findViewById(R.id.listar);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, ListarPotreros.class);
                startActivity(intent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    ListarPotreros.AdapterPotrero adapter;


    public void delete(View view) {
        Toast.makeText(MainActivity.this, "Borrando", Toast.LENGTH_LONG).show();
        AdapterPotreroD adapterPotreroD = new AdapterPotreroD(this);
        int pos = 0;
        db.potreroDao().deleteById(pos);
    }
    class AdapterPotreroD extends ArrayAdapter<Potrero> {
        AppCompatActivity appCompatActivity;

        public AdapterPotreroD(AppCompatActivity context){
            super(context, R.layout.item_potrero, listpotreros);
            appCompatActivity = context;
        }

        public View getView(int pos, View convertView, ViewGroup container){
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.item_potrero, null);

            TextView nombre = (TextView)item.findViewById(R.id.textPotrero);
            String dato = listpotreros.get(pos).getNombre()+" "+listpotreros.get(pos).getImg();
            nombre.setText(dato);

            return item;
        }

    }
}