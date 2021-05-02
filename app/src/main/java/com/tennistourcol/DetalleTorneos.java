package com.tennistourcol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.tennistourcol.model.Tournament;

public class DetalleTorneos extends AppCompatActivity {
    private Tournament tournament;
    private TextView titulo, descrip, direccion, fecha, hora, grado, precio;
    private ImageView imgFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_torneos);

        tournament = (Tournament) getIntent().getSerializableExtra("torneo");
        titulo = findViewById(R.id.tituloDescrip);
        descrip = findViewById(R.id.descrip);
        imgFoto = findViewById(R.id.imgDescrip);
        direccion = findViewById(R.id.direccion);
        fecha = findViewById(R.id.fecha);
        hora = findViewById(R.id.hora);
        grado = findViewById(R.id.grado);
        precio = findViewById(R.id.precio);

        titulo.setText(tournament.getCiudad());
        imgFoto.setImageResource(tournament.getFoto());
        descrip.setText(tournament.getClub());
        direccion.setText(tournament.getDireccion());
        fecha.setText(tournament.getFechaFin().toString());
        hora.setText(tournament.getHora());
        grado.setText(tournament.getGrado());
        precio.setText(tournament.getPrecio().toString());

    }
}