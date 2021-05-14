package com.tennistourcol;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText nameR,mailR,passwordR;
    Button registerButton;
    TextView loginText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameR= findViewById(R.id.fullName);
        mailR= findViewById(R.id.registerEmail);
        passwordR= findViewById(R.id.registerPassword);
        registerButton= findViewById(R.id.registerButton);
        loginText=findViewById(R.id.loginText);


        registerButton.setOnClickListener(v -> {
            String email = mailR.getText().toString().trim();
            String password= passwordR.getText().toString().trim();

            if(TextUtils.isEmpty(email)){
                mailR.setError("El correo es obligatorio");
                return;
            }
            else if (TextUtils.isEmpty(password)){
                passwordR.setError("La contraseña es obligatoria");
                return;
            }

            else if (password.length() < 6){
                passwordR.setError("La contraseña debe tener mas de 6 caracteres.");
            }

            else {
                SharedPreferences sharedPref = getSharedPreferences("Register", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(email,password);
                editor.apply();
                Toast.makeText(Register.this,"Usuario creado.",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Login.class));

            }
        });
        loginText.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),Login.class));
        });
    }
}