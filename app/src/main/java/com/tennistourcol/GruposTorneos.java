package com.tennistourcol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.tennistourcol.impl.AdaptadorGruposTorneos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GruposTorneos extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private AdaptadorGruposTorneos adaptadorGruposTorneos;
    private ArrayList<String> listaCategorias;
    private Map<String, ArrayList<String>> cuadrosChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos_torneos);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        listaCategorias = new ArrayList<>();
        cuadrosChild = new HashMap<>();

        cargarDatos();
    }

    private void cargarDatos(){
        ArrayList<String> listOctavos = new ArrayList<>();
        ArrayList<String> listCuartos = new ArrayList<>();
        ArrayList<String> listSemiFinales = new ArrayList<>();
        ArrayList<String> listFinales = new ArrayList<>();

        listaCategorias.add("Octavos");
        listaCategorias.add("Cuartos");
        listaCategorias.add("SemiFinales");
        listaCategorias.add("Finales");


        listOctavos.add("La roca");
        listOctavos.add("Toreto");
        listOctavos.add("Daniel");
        listOctavos.add("Carlos");
        listOctavos.add("Maria");
        listOctavos.add("Nahomi");

        listCuartos.add("Sofi");
        listCuartos.add("Juliana");
        listCuartos.add("Shaparova");
        listCuartos.add("Tatiana");
        listCuartos.add("Carlos");
        listCuartos.add("Sebastian");

        listSemiFinales.add("Sebastian");
        listSemiFinales.add("La roca");
        listSemiFinales.add("Tatiana");
        listSemiFinales.add("Sofi");

        listFinales.add("Adriana");
        listFinales.add("Sebastian");

        cuadrosChild.put(listaCategorias.get(0), listOctavos);
        cuadrosChild.put(listaCategorias.get(1), listCuartos);
        cuadrosChild.put(listaCategorias.get(2), listSemiFinales);
        cuadrosChild.put(listaCategorias.get(3), listFinales);

        adaptadorGruposTorneos = new AdaptadorGruposTorneos(this, listaCategorias, cuadrosChild);
        expandableListView.setAdapter(adaptadorGruposTorneos);



    }
}