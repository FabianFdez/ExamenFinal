package com.example.examenfinal.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.example.examenfinal.Config.Constante;
import com.example.examenfinal.Database.AppDatabase;
import com.example.examenfinal.Entities.Potrero;
import com.example.examenfinal.R;

import java.util.List;


public class HomeFragment extends Fragment {

    AppDatabase db;
    private List<Potrero> listpotreros;
    ListView viewPotrero;

    /*public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        db = Room.databaseBuilder(getContext(), AppDatabase.class, Constante.BD_NAME).allowMainThreadQueries().build();
        listpotreros = db.potreroDao().getAll();
        @SuppressLint("ResourceType")
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        viewPotrero = (ListView)root.findViewById(R.id.text_home);
        int pos = 0;
        TextView nombre = (TextView)root.findViewById(R.id.textPotrero);
        String dato = listpotreros.get(pos).getNombre()+" "+listpotreros.get(pos).getImg();
        nombre.setText(dato);
        @SuppressLint("ResourceType")
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.id.textPotrero, android.R.layout.list_content);

        viewPotrero.setAdapter(adapter);




        return root;


    }*/


}