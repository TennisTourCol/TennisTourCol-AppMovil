package com.tennistourcol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.tennistourcol.impl.Adaptador;
import com.tennistourcol.model.Tournament;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public class TournamentActivity extends AppCompatActivity {

    private ListView lTournaments;
    private Adaptador adaptador;
    private ArrayList<Tournament> listaTorneos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);

        lTournaments = (ListView) findViewById(R.id.lTournaments);
        listaTorneos = getTorneos();
        adaptador = new Adaptador(this, listaTorneos);
        lTournaments.setAdapter(adaptador);

        lTournaments.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TournamentActivity.this, DetalleTorneos.class);
                intent.putExtra("torneo", listaTorneos.get(position));
                startActivity(intent);
            }
        });
    }

    private ArrayList<Tournament> getTorneos(){
        Tournament bogotaTorneo = Tournament.builder()
                .nombre("Torneo prueba")
                .responsable("Juan")
                .direccion("Tv 1 #2-3")
                .ciudad("Bogotá")
                .club("El club de prueba")
                .grado("4")
                .categoria("20-22")
                .precio(BigInteger.valueOf(10000))
                .hora("12:00")
                .fechaInicio(new Date())
                .fechaFin(new Date())
                .foto(R.drawable.bt)
                .build();
        Tournament serrezuela = Tournament.builder()
                .nombre("Torneo prueba2")
                .responsable("Juan")
                .direccion("Tv 1 #2-3")
                .ciudad("Bogotá")
                .club("El club de prueba")
                .grado("2")
                .categoria("20-22")
                .precio(BigInteger.valueOf(10000))
                .hora("12:00")
                .fechaInicio(new Date())
                .fechaFin(new Date())
                .foto(R.drawable.serrezuela)
                .build();
        listaTorneos.add(bogotaTorneo);
        listaTorneos.add(serrezuela);
        return listaTorneos;
    }

    public void eliminarTorneo(View view){
        System.out.println("ENTRE");
        System.out.println(view.findViewById(R.id.nombreTorneo));
        System.out.println(lTournaments);

    }
}