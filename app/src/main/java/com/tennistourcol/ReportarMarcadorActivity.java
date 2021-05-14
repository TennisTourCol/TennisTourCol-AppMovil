package com.tennistourcol;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tennistourcol.databinding.ActivityPayBinding;
import com.tennistourcol.databinding.ActivityReportarMarcadorBinding;

public class ReportarMarcadorActivity extends AppCompatActivity {

    private TextView j1, j2, cancha;
    private ActivityReportarMarcadorBinding binding;
    String s1j1,s1j2,s2j1,s2j2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportarMarcadorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        j1 = findViewById(R.id.nombreTorneoFactura);
        j2 = findViewById(R.id.lugarTorneoFactura);
        cancha = findViewById(R.id.fechaInicioTorneoFactura);

        j1.setText("David Herrera");
        j2.setText("Juan Manuel Herrera");
        cancha.setText("3");

        binding.confirmarMarador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1j1 = binding.set1J1.getText().toString();
                s1j2 = binding.set1J2.getText().toString();
                s2j1 = binding.set2J1.getText().toString();
                s2j2 = binding.set2J2.getText().toString();
                if (!s1j1.isEmpty() && !s1j2.isEmpty() && !s2j1.isEmpty() && !s2j2.isEmpty()) {
                    binding.set1J1.setError("exito");
                } else {
                    binding.set1J1.setError("Falta este campo");
                    binding.set1J2.setError("Falta este campo");
                    binding.set2J1.setError("Falta este campo");
                    binding.set2J2.setError("Falta este campo");
                }
            }
        });





    }
}