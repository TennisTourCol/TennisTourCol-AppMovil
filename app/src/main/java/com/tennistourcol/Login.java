package com.tennistourcol;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText emailL,passwordL;
    Button loginButton;
    TextView forgotPsText,registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailL= findViewById(R.id.loginMail);
        passwordL= findViewById(R.id.loginPassword);
        loginButton= findViewById(R.id.loginButton);
        forgotPsText=findViewById(R.id.forgotPasswordText);
        registerText=findViewById((R.id.registerText));

        loginButton.setOnClickListener(v -> {
            String email = emailL.getText().toString().trim();
            String password= passwordL.getText().toString().trim();
            SharedPreferences sharedPref = getSharedPreferences(
                    "Register", Context.MODE_PRIVATE);
            String clave= sharedPref.getString(email,"");
            if(!clave.equals("")){
                if(password.equals(clave)){
                    startActivity(new Intent(Login.this,MainActivity.class));

                }
                else{
                    Toast.makeText(Login.this, "Usuario o contraseña no validos", Toast.LENGTH_SHORT).show();
                }

            }
            else {
                Toast.makeText(Login.this, "Usuario o contraseña no validos", Toast.LENGTH_SHORT).show();
            }

        });
        registerText.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),Register.class));
        });
    }
}