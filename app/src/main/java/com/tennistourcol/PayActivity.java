package com.tennistourcol;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tennistourcol.databinding.ActivityPayBinding;
import com.tennistourcol.model.Tournament;

import java.math.BigInteger;
import java.util.Date;


public class PayActivity extends AppCompatActivity {

    private ActivityPayBinding binding;
    String correo,nombre;
    String name = "Highbrow Director";
    String upiId = "hashimads123@oksbi";
    String transactionNote = "pay test";
    String status;
    Uri uri;
    int GOOGLE_PAY_REQUEST_CODE = 123;
    public static final String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    private Tournament tournament;
    private TextView nombreTorneo, lugarTorneo, totalAPagar, fechaTorneo, idTorneo, fechaFactura;
    private Date fechaFacturaCreacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fechaFacturaCreacion= new Date();
        super.onCreate(savedInstanceState);
        binding = ActivityPayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //tournament = (Tournament) getIntent().getSerializableExtra("torneo");
        tournament= (Tournament) getIntent().getSerializableExtra("torneo");
        nombreTorneo = findViewById(R.id.nombreTorneoFactura);
        lugarTorneo = findViewById(R.id.lugarTorneoFactura);
        fechaTorneo = findViewById(R.id.fechaInicioTorneoFactura);
        totalAPagar = findViewById(R.id.totalPagarFactura);
        idTorneo = findViewById(R.id.idFactura);
        fechaFactura = findViewById(R.id.fechaFactura);

        nombreTorneo.setText(tournament.getNombre());
        lugarTorneo.setText(tournament.getClub());
        fechaTorneo.setText(tournament.getFechaInicio().toString());
        totalAPagar.setText(tournament.getPrecio().toString());
        idTorneo.setText("001");
        fechaFactura.setText(fechaFacturaCreacion.toString());

        binding.googlePayButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               correo = binding.correoFactura.getText().toString();
               nombre = binding.nombreFactua.getText().toString();
               if (!correo.isEmpty() && !nombre.isEmpty()) {
                   uri = getUpiPaymentUri(name, upiId, transactionNote, "160000");
                   payWithGPay();
               } else {
                   binding.correoFactura.setError("Correo es requerido!");
                   binding.nombreFactua.setError("Nombre de la persona es requerido!");
               }
           }
       });


    }

    private static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private static Uri getUpiPaymentUri(String name, String upiId, String transactionNote, String amount) {
        return new Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", transactionNote)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();
    }
    private void payWithGPay() {
        if (isAppInstalled(this, GOOGLE_PAY_PACKAGE_NAME)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setPackage(GOOGLE_PAY_PACKAGE_NAME);
            startActivityForResult(intent, GOOGLE_PAY_REQUEST_CODE);
        } else {
            Toast.makeText(PayActivity.this, "Please Install GPay", Toast.LENGTH_SHORT).show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            status = data.getStringExtra("Status").toLowerCase();
        }

        if ((RESULT_OK == resultCode) && status.equals("success")) {
            Toast.makeText(PayActivity.this, "Transaction Successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(PayActivity.this, "Transaction Failed", Toast.LENGTH_SHORT).show();
        }
    }


}