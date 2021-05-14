package com.tennistourcol.ui.User;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tennistourcol.R;
import com.tennistourcol.impl.Adaptador;
import com.tennistourcol.model.Player;
import com.tennistourcol.model.Tournament;
import com.tennistourcol.service.UserService;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public class UserFragment extends Fragment {

    public static UserFragment UserFragment() {
        return new UserFragment();
    }

    private Button editUserButton;
    private UserViewModel userViewModel;
    private UserService userService;
    private ListView lTournaments;
    private Player jugadorPrueba;
    private TextView name, description, apodo, liga, puntos, ciudad;
    private ImageView imgPerfil;
    private Adaptador adaptador;
    private ArrayList<Tournament> listaTorneos = new ArrayList<>();
    private Context context;
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, @NotNull Bundle savedInstanceState) {
        jugadorPrueba = createPlayer();
        listaTorneos = getTorneos();
        View view =inflater.inflate(R.layout.fragment_user, container, false);

        context=container.getContext();
        name=view.findViewById(R.id.namePerfil2);
        imgPerfil = view.findViewById(R.id.imgPerfil2);
        description = view.findViewById(R.id.descriptionPerfil2);
        ciudad = view.findViewById(R.id.cityPlayer2);
        apodo = view.findViewById(R.id.apodoPlayer2);
        puntos = view.findViewById(R.id.puntosPlayer2);
        liga=view.findViewById(R.id.ligaPlayer2);


        name.setText(jugadorPrueba.getName());
        imgPerfil.setImageResource(jugadorPrueba.getImagen());
        description.setText(jugadorPrueba.getDescription());
        ciudad.setText(jugadorPrueba.getCiudad());
        apodo.setText(jugadorPrueba.getApodo());
        liga.setText(jugadorPrueba.getLiga());
        puntos.setText(jugadorPrueba.getPuntos());

        lTournaments = (ListView) view.findViewById(R.id.lTorneos2);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);

        // TODO: Use the ViewModel
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
