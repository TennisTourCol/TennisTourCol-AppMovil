package com.tennistourcol.ui.match;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.tennistourcol.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchFragment extends Fragment {

    public MatchFragment() {
        // Required empty public constructor
    }

    private TextView jugadorLocal;
    private TextView jugadorVisitante;
    private TextView numeroCancha;
    private EditText fechaPartido;
    private TextView horaPartido;
    private final Calendar calendar = Calendar.getInstance();
    private final int mes = calendar.get(Calendar.MONTH);
    private final int dia = calendar.get(Calendar.DAY_OF_MONTH);
    private final int ano = calendar.get(Calendar.YEAR);
    private final int horaC = calendar.get(Calendar.HOUR_OF_DAY);
    private final int minutoC = calendar.get(Calendar.MINUTE);

    public static MatchFragment newInstance() {
        MatchFragment fragment = new MatchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_match, container, false);
        jugadorLocal = view.findViewById(R.id.jugador_local);
        jugadorVisitante = view.findViewById(R.id.jugador_visitante);
        numeroCancha = view.findViewById(R.id.numero_cancha);
        fechaPartido = view.findViewById(R.id.fecha_partido);
        horaPartido = view.findViewById(R.id.hora_partido);

        ((TextView) view.findViewById(R.id.fecha_partido)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerFecha();
            }
        });

        ((TextView) view.findViewById(R.id.hora_partido)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerHora();
            }
        });

        return view;
    }

    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                int mesActual = month + 1;
                String diaFormateado = (dayOfMonth < 10)? "0" + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? "0" + String.valueOf(mesActual):String.valueOf(mesActual);

                fechaPartido.setText(diaFormateado + "/" + mesFormateado +"/"+ year);

            }
        },ano, mes, dia);
        recogerFecha.show();

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
                horaPartido.setText(horaFormateada + ":" + minutoFormateado + " " + AM_PM);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, horaC, minutoC, false);

        recogerHora.show();
    }
}