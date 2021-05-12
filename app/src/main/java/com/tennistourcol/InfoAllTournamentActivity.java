package com.tennistourcol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.tennistourcol.model.Tournament;

public class InfoAllTournamentActivity extends AppCompatActivity {
    private Tournament tournament;
    private TextView titulo, direccion, fecha, grado, precio;
    private ImageView imgFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_all_tournament);

        tournament = (Tournament) getIntent().getSerializableExtra("torneo");
        titulo = findViewById(R.id.clubAllInfoTournament);
        imgFoto = findViewById(R.id.imgAllInfoTournament);
        direccion = findViewById(R.id.direccionAllInfoTournament);
        fecha = findViewById(R.id.fechaAllInfoTournament);
        grado = findViewById(R.id.gradoAllInfoTournament);
        precio = findViewById(R.id.precioAllInfoTournament);

        imgFoto.setImageResource(tournament.getFoto());
        titulo.setText(tournament.getClub());
        direccion.setText(tournament.getDireccion());
        fecha.setText(tournament.getFechaFin().toString());
        grado.setText(tournament.getGrado());
        precio.setText(tournament.getPrecio().toString());
    }
}