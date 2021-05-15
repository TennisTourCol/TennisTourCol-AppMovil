package com.tennistourcol.impl;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tennistourcol.R;
import com.tennistourcol.model.Tournament;

import java.util.ArrayList;

public class AdaptadorTodosTorneos extends BaseAdapter {
    private Context context;
    private ArrayList<Tournament> torneos;
    private Button boton;

    public AdaptadorTodosTorneos(Context context, ArrayList<Tournament> torneos) {
        this.context = context;
        this.torneos = torneos;
    }

    @Override
    public int getCount() {
        return torneos.size();
    }

    @Override
    public Object getItem(int position) {
        return torneos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tournament tournament = (Tournament)getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item_todos_torneos, null);
        ImageView clubImg = convertView.findViewById(R.id.imgFoto);
        TextView nombreTorneo = convertView.findViewById(R.id.nombreTorneo);
        TextView club = convertView.findViewById(R.id.club);
        TextView fecha = convertView.findViewById(R.id.direccion);


        clubImg.setImageResource(tournament.getFoto());
        nombreTorneo.setText(tournament.getNombre());
        club.setText(tournament.getClub());
        fecha.setText(tournament.getFechaInicio().toString());
        return convertView;
    }
}
