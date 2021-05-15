package com.tennistourcol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tennistourcol.impl.Adaptador;
import com.tennistourcol.model.Player;
import com.tennistourcol.model.Tournament;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public class PlayerActivity extends AppCompatActivity {
    private ListView lTournaments;
    private Player jugadorPrueba;
    private TextView name, description, apodo, liga, puntos, ciudad;
    private ImageView imgPerfil;
    private Adaptador adaptador;
    private ArrayList<Tournament> listaTorneos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        jugadorPrueba = createPlayer();
        listaTorneos = getTorneos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        name=findViewById(R.id.namePerfil2);
        imgPerfil = findViewById(R.id.imagenPerfil);
        description = findViewById(R.id.descriptionPerfil2);
        ciudad = findViewById(R.id.cityPlayer2);
        apodo = findViewById(R.id.apodoPlayer2);
        puntos = findViewById(R.id.puntosPlayer2);
        liga=findViewById(R.id.ligaPlayer2);

        name.setText(jugadorPrueba.getName());
        imgPerfil.setImageResource(jugadorPrueba.getImagen());
        description.setText(jugadorPrueba.getDescription());
        ciudad.setText(jugadorPrueba.getCiudad());
        apodo.setText(jugadorPrueba.getApodo());
        liga.setText(jugadorPrueba.getLiga());
        puntos.setText(jugadorPrueba.getPuntos());
        lTournaments = (ListView) findViewById(R.id.lTournaments);
        adaptador = new Adaptador(this, listaTorneos);
            lTournaments.setAdapter(adaptador);

            lTournaments.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PlayerActivity.this, DetalleTorneos.class);
                intent.putExtra("torneo", listaTorneos.get(position));
                startActivity(intent);
            }
        });
}
    private Player createPlayer(){
        Player player = Player.builder()
                .id("1")
                .name("player prueba")
                .apodo("prueeba")
                .ciudad("Bogota")
                .description("Jugador de tennis con grandes espectaticas, desde muy pequeño entrenando para ser uno de los mejroes jugadore juvenil 2021")
                .liga("Bogota")
                .mail("prueba@gmail.com")
                .imagen(R.drawable.foto_perdil)
                .puntos("1000")
                .build();
        return player;
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

}

