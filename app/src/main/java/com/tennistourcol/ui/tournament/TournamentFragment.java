package com.tennistourcol.ui.tournament;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.tennistourcol.R;

import java.util.Calendar;

public class TournamentFragment extends Fragment {

    private TournamentViewModel mViewModel;

    public static TournamentFragment newInstance() {
        return new TournamentFragment();
    }

    private EditText fechaInicial;
    private EditText fechaFinal;
    private final Calendar calendar = Calendar.getInstance();
    private final int mes = calendar.get(Calendar.MONTH);
    private final int dia = calendar.get(Calendar.DAY_OF_MONTH);
    private final int ano = calendar.get(Calendar.YEAR);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tournament_fragment, container, false);
        fechaInicial = (EditText) view.findViewById(R.id.fechainicio);
        fechaFinal = (EditText) view.findViewById(R.id.fechafinal);
        ((TextView) view.findViewById(R.id.fechainicio)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerFechaInicio();
            }
        });
        ((TextView) view.findViewById(R.id.fechafinal)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerFechaFinal();
            }
        });
        ((FloatingActionButton) view.findViewById(R.id.saveTournamentButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                obtenerFecha();
                System.out.println("GUARDAR-----------------");
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TournamentViewModel.class);

        // TODO: Use the ViewModel
    }

    private void obtenerFechaInicio(){
        DatePickerDialog recogerFecha = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                int mesActual = month + 1;
                String diaFormateado = (dayOfMonth < 10)? "0" + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? "0" + String.valueOf(mesActual):String.valueOf(mesActual);

                fechaInicial.setText(diaFormateado + "/" + mesFormateado +"/"+ year);

            }
        },ano, mes, dia);

        recogerFecha.show();

    }

    private void obtenerFechaFinal(){
        DatePickerDialog recogerFecha = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                int mesActual = month + 1;
                String diaFormateado = (dayOfMonth < 10)? "0" + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? "0" + String.valueOf(mesActual):String.valueOf(mesActual);

                fechaFinal.setText(diaFormateado + "/" + mesFormateado +"/"+ year);

            }
        },ano, mes, dia);

        recogerFecha.show();

    }
}