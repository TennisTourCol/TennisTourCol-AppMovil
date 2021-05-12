package com.tennistourcol.ui.tournament;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;

import com.tennistourcol.R;
import com.tennistourcol.model.Tournament;
import com.tennistourcol.service.TournamentService;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TournamentFragment extends Fragment {

    private TournamentViewModel mViewModel;

    private TournamentService tournamentService;

    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );

    public static TournamentFragment newInstance() {
        return new TournamentFragment();
    }

    private TextView nombre;
    private TextView responsable;
    private TextView direccion;
    private TextView ciudad;
    private TextView club;
    private TextView grado;
    private TextView categoria;
    private TextView precio;
    private TextView hora;
    private EditText fechaInicial;
    private EditText fechaFinal;
    private final Calendar calendar = Calendar.getInstance();
    private final int mes = calendar.get(Calendar.MONTH);
    private final int dia = calendar.get(Calendar.DAY_OF_MONTH);
    private final int ano = calendar.get(Calendar.YEAR);
    private final int horaC = calendar.get(Calendar.HOUR_OF_DAY);
    private final int minutoC = calendar.get(Calendar.MINUTE);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tournament_fragment, container, false);
        nombre = view.findViewById(R.id.nombre);
        responsable = view.findViewById(R.id.responsable);
        direccion = view.findViewById(R.id.direcciontorneo);
        ciudad = view.findViewById(R.id.ciudad);
        club = view.findViewById(R.id.clubTorneo);
        grado = view.findViewById(R.id.gradoTorneo);
        categoria = view.findViewById(R.id.categoria);
        precio = view.findViewById(R.id.preciotorneo);
        hora = view.findViewById(R.id.horaTorneo);
        fechaInicial = view.findViewById(R.id.fechainicio);
        fechaFinal = view.findViewById(R.id.fechafinal);
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

        ((TextView) view.findViewById(R.id.horaTorneo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerHora();
            }
        });

        ((FloatingActionButton) view.findViewById(R.id.saveTournamentButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaCampos(view);
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

    private void obtenerHora(){
        TimePickerDialog recogerHora = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf("0" + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf("0" + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado
                hora.setText(horaFormateada + ":" + minutoFormateado + " " + AM_PM);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, horaC, minutoC, false);

        recogerHora.show();
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

    private void validaCampos(View view){;
        if(nombre.getText().toString().equals("")){
            nombre.setError("Ingrese el nombre del torneo");
        } else if (responsable.getText().toString().equals("")){
            responsable.setError("Ingrese nombre del responsable");
        } else if (direccion.getText().toString().equals("")){
            direccion.setError("Ingrese la dirección");
        } else if (ciudad.getText().toString().equals("")){
            ciudad.setError("Ingrese la ciudad");
        } else if (club.getText().toString().equals("")){
            club.setError("Ingrese el club");
        } else if (grado.getText().toString().equals("")){
            grado.setError("Ingrese el grado");
        } else if (categoria.getText().toString().equals("")){
            categoria.setError("Ingrese la categoria");
        } else if (precio.getText().toString().equals("")){
            precio.setError("Ingrese el precio");
        } else if (hora.getText().toString().equals("")){
            hora.setError("Ingrese la hora");
        } else if (fechaInicial.getText().toString().equals("")){
            fechaInicial.setError("Ingrese la fecha de inicio");
        } else if (fechaFinal.getText().toString().equals("")){
            fechaFinal.setError("Ingrese la fecha final");
        } else {
            //TODO
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://ieti-back.herokuapp.com/") //localhost for emulator
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            tournamentService = retrofit.create(TournamentService.class);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaIni = new Date();
            Date fechaFin = new Date();
            try {
                fechaIni = format.parse(fechaInicial.getText().toString());
                fechaFin = format.parse(fechaFinal.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Tournament tournament = Tournament.builder()
                    .nombre(nombre.getText().toString())
                    .responsable(responsable.getText().toString())
                    .direccion(direccion.getText().toString())
                    .ciudad(ciudad.getText().toString())
                    .club(club.getText().toString())
                    .grado(grado.getText().toString())
                    .categoria(categoria.getText().toString())
                    .precio(new BigInteger(precio.getText().toString()))
                    .hora(hora.getText().toString())
                    .fechaInicio(fechaIni)
                    .fechaFin(fechaFin)
                    .build();
            System.out.println(tournament);
//            saveTournament(tournament);
        }
    }

    private void saveTournament(Tournament tournament) {
        executorService.execute(new Runnable()
        {
            @Override
            public void run() {
                Response<com.tennistourcol.model.Response> response = null;
                try {
                    response = tournamentService.createTournament(tournament).execute();
                    System.out.println("res " + response.toString());
                    com.tennistourcol.model.Response tournamentResponse = response.body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }});
    }
}