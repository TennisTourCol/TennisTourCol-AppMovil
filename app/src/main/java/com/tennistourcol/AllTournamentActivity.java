package com.tennistourcol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tennistourcol.impl.AdaptadorTodosTorneos;
import com.tennistourcol.model.Tournament;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public class AllTournamentActivity extends AppCompatActivity {

    private ListView lTournaments;
    private AdaptadorTodosTorneos adaptadorTodosTorneos;
    private ArrayList<Tournament> listaTorneos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);

        lTournaments = (ListView) findViewById(R.id.lTournaments);
        listaTorneos = getTorneos();
        adaptadorTodosTorneos = new AdaptadorTodosTorneos(this, listaTorneos);
        lTournaments.setAdapter(adaptadorTodosTorneos);

        lTournaments.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AllTournamentActivity.this, InfoAllTournamentActivity.class);
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
        Tournament prueba = Tournament.builder()
                .nombre("Torneo prueba3")
                .responsable("San")
                .direccion("Tv 4 #5-6")
                .ciudad("Egipto")
                .club("El club de prueba3")
                .grado("3")
                .categoria("20-22")
                .precio(BigInteger.valueOf(10000))
                .hora("12:00")
                .fechaInicio(new Date())
                .fechaFin(new Date())
                .foto(R.drawable.serrezuela)
                .build();
        listaTorneos.add(bogotaTorneo);
        listaTorneos.add(serrezuela);
        listaTorneos.add(prueba);
        return listaTorneos;
    }
}
