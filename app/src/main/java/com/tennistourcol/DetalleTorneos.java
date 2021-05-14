package com.tennistourcol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tennistourcol.model.Tournament;

public class DetalleTorneos extends AppCompatActivity {
    private Tournament tournament;
    private TextView titulo, descrip, direccion, fecha, hora, grado, precio;
    private ImageView imgFoto;
    private Button pagar;
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
        pagar = findViewById(R.id.pagar);
        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalleTorneos.this, PayActivity.class);
                intent.putExtra("torneo", tournament);
                startActivity(intent);
            }
        });

    }
    public void verCuadros(View view){
        Intent intent = new Intent(this, GruposTorneos.class);
        startActivity(intent);
    }
}