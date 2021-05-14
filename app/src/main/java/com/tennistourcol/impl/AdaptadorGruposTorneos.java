package com.tennistourcol.impl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tennistourcol.R;
import com.tennistourcol.model.Tournament;

import java.util.ArrayList;
import java.util.Map;

public class AdaptadorGruposTorneos extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> categoria;
    private Map<String, ArrayList<String>> mapChild;

    public AdaptadorGruposTorneos(Context context, ArrayList<String> categoria, Map<String, ArrayList<String>> mapChild) {
        this.context = context;
        this.categoria = categoria;
        this.mapChild = mapChild;
    }

    @Override
    public int getGroupCount() {
        return categoria.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mapChild.get(categoria.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return categoria.get(i);
    }

    @Override
    public Object getChild(int i, int j) {
        return mapChild.get(categoria.get(i)).get(j);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String tituloCategoria = (String) getGroup(i);
        view = LayoutInflater.from(context).inflate(R.layout.cuadros_grupos, null);
        TextView tvGroup = (TextView) view.findViewById(R.id.textView12);
        tvGroup.setText(tituloCategoria);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String item = (String) getChild(i, i1);
        view = LayoutInflater.from(context).inflate(R.layout.cuadros_child, null);
        TextView tvChild = (TextView) view.findViewById(R.id.textView11);
        tvChild.setText(item);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}