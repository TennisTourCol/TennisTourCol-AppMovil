package com.tennistourcol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReportarMarcadorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_marcador);

        TextView j1 = findViewById(R.id.jugador1Marcador);
        TextView j2 = findViewById(R.id.jugador2Marcador);

        TextView cancha = findViewById(R.id.canchaMarcador);

        j1.setText("La Roca");
        j2.setText("Toreto");
        cancha.setText("3");

        Button boton = (Button) findViewById(R.id.confirmarMarador);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportarMarcadorActivity.this, GruposTorneos.class);
                startActivity(intent);
            }
        });

    }
}