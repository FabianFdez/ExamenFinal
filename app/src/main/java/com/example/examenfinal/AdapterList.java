package com.example.examenfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.examenfinal.Database.AppDatabase;
import com.example.examenfinal.Entities.Potrero;

import java.util.List;
import java.util.Map;

public class AdapterList extends BaseExpandableListAdapter {

    AppDatabase db;
    private List<Potrero> listpotreros;
    private Map<String, List<String>> mapChild;
    ListView viewPotrero;
    private Context context;

    public AdapterList(AppDatabase db, List<Potrero> listpotreros, Map<String, List<String>> mapChild, ListView viewPotrero, Context context) {
        this.db = db;
        this.listpotreros = listpotreros;
        this.mapChild = mapChild;
        this.viewPotrero = viewPotrero;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return listpotreros.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mapChild.get(listpotreros.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listpotreros.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mapChild.get(listpotreros.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String titulo = (String)getGroup(groupPosition);
        convertView = LayoutInflater.from(context).inflate(R.layout.group, null);
        TextView group = (TextView) convertView.findViewById(R.id.txtGroup);
        group.setText(titulo);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String item = (String) getChild(groupPosition, childPosition);
        convertView = LayoutInflater.from(context).inflate(R.layout.child, null);
        TextView child = (TextView)convertView.findViewById(R.id.txtChild);
        child.setText(item);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
