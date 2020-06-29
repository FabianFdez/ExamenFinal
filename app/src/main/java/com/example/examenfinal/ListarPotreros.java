package com.example.examenfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.examenfinal.Config.Constante;
import com.example.examenfinal.Database.AppDatabase;
import com.example.examenfinal.Entities.Potrero;

import java.util.List;

public class ListarPotreros extends AppCompatActivity {
    AppDatabase db;
    private List<Potrero> listpotreros;
    ListView viewPotrero;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Constante.BD_NAME).allowMainThreadQueries().build();
        listpotreros = db.potreroDao().getAll();
        viewPotrero= (ListView)findViewById(R.id.text_home);

        AdapterPotrero adapterPotrero = new AdapterPotrero(this);
        viewPotrero.setAdapter(adapterPotrero);
    }
    public void delete(int pos) {
        db.potreroDao().deleteById(pos);
    }
    class AdapterPotrero extends ArrayAdapter<Potrero>{
        AppCompatActivity appCompatActivity;

        public AdapterPotrero(AppCompatActivity context){
            super(context, R.layout.item_potrero, listpotreros);
            appCompatActivity = context;
        }

        public View getView(int pos, View convertView, ViewGroup container){
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.item_potrero, null);

            TextView nombre = (TextView)item.findViewById(R.id.textPotrero);
            String dato = listpotreros.get(pos).getNombre()+" "+listpotreros.get(pos).getFecha()+" "+listpotreros.get(pos).getImg();
            nombre.setText(dato);
            final Potrero potrero = listpotreros.get(pos);

            nombre.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(ListarPotreros.this, "Borrando  "+potrero.getNombre(), Toast.LENGTH_LONG).show();
                    delete((int) potrero.getId());
                    return false;
                }
            });
            return item;
        }

        public Potrero getPosition(int pos) {
            return listpotreros.get(pos);
        }
    }

}
