package com.tennistourcol.ui.ListTournament;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tennistourcol.DetalleTorneos;
import com.tennistourcol.R;
import com.tennistourcol.TournamentActivity;
import com.tennistourcol.impl.Adaptador;
import com.tennistourcol.model.Tournament;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public class TournamentActivityFragment extends Fragment {

    private ListView lTournaments;
    private Adaptador adaptador;
    private ArrayList<Tournament> listaTorneos = new ArrayList<>();

    private TournamentActivityViewModel mViewModel;
    private Context context;
    public static TournamentActivityFragment newInstance() {
        return new TournamentActivityFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tournament_activity_fragment, container, false);
        context = container.getContext();
        lTournaments = (ListView) view.findViewById(R.id.lTournaments);
        listaTorneos = getTorneos();
        adaptador = new Adaptador(context, listaTorneos);
        lTournaments.setAdapter(adaptador);

        lTournaments.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, DetalleTorneos.class);
                intent.putExtra("torneo", listaTorneos.get(position));
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TournamentActivityViewModel.class);
        // TODO: Use the ViewModel
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