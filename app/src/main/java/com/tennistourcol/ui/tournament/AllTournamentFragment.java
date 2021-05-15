package com.tennistourcol.ui.tournament;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tennistourcol.AllTournamentActivity;
import com.tennistourcol.InfoAllTournamentActivity;
import com.tennistourcol.R;
import com.tennistourcol.impl.AdaptadorTodosTorneos;
import com.tennistourcol.model.Tournament;
import com.tennistourcol.service.TournamentService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AllTournamentFragment extends Fragment {

    private TournamentService tournamentService;
    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );
    private ListView lTournaments;
    private AdaptadorTodosTorneos adaptadorTodosTorneos;
    private ArrayList<Tournament> listaTorneos = new ArrayList<>();

    public AllTournamentFragment() {
        // Required empty public constructor
    }

    public static AllTournamentFragment newInstance(String param1, String param2) {
        AllTournamentFragment fragment = new AllTournamentFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_tournament, container, false);
        lTournaments = (ListView) view.findViewById(R.id.lTournaments);
        listaTorneos = getTorneos();
        adaptadorTodosTorneos = new AdaptadorTodosTorneos(container.getContext(), listaTorneos);
        lTournaments.setAdapter(adaptadorTodosTorneos);

        lTournaments.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(container.getContext(), InfoAllTournamentActivity.class);
                intent.putExtra("torneo", listaTorneos.get(position));
                startActivity(intent);
            }
        });

        return view;
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