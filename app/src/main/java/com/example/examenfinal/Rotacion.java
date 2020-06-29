package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.examenfinal.Config.Constante;
import com.example.examenfinal.Database.AppDatabase;
import com.example.examenfinal.Entities.Potrero;

import java.util.List;
import java.util.Map;

public class Rotacion extends AppCompatActivity {
    AppDatabase db;
    private List<Potrero> listpotreros;
    ListView viewPotrero;
    private ExpandableListView expView;
    private ExpandableListAdapter adapter;
    private Map<String, List<String>> mapChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotacion);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Constante.BD_NAME).allowMainThreadQueries().build();
        listpotreros = db.potreroDao().getAll();
        viewPotrero= (ListView)findViewById(R.id.text_home);

        AdapterPotrero adapterPotrero = new AdapterPotrero(this);
        viewPotrero.setAdapter(adapterPotrero);
        //expView = (ExpandableListView)findViewById(R.id.text_gallery);
    }

    class AdapterPotrero extends ArrayAdapter<Potrero> {
        AppCompatActivity appCompatActivity;

        public AdapterPotrero(AppCompatActivity context){
            super(context, R.layout.fragment_gallery, listpotreros);
            appCompatActivity = context;
        }

        public View getView(int pos, View convertView, ViewGroup container){
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.fragment_gallery, null);

            TextView nombre = (TextView)item.findViewById(R.id.textPotrero);
            String dato = listpotreros.get(pos).getNombre()+" "+listpotreros.get(pos).getFecha()+" "+listpotreros.get(pos).getImg();
            nombre.setText(dato);

            return item;
        }

        public Potrero getPosition(int pos) {
            return listpotreros.get(pos);
        }
    }
}