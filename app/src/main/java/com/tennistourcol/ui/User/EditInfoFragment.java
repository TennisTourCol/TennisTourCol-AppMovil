package com.tennistourcol.ui.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.tennistourcol.MainActivity;
import com.tennistourcol.R;

public class EditInfoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText userName,userMail,userApodo,userContraseña,usercontraseña2,userLiga,userCiudad,userDescripcion;
    Button saveButton;

    public EditInfoFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_edit_info, container, false);
        userName=view.findViewById(R.id.userName);
        userMail=view.findViewById(R.id.userEmail);
        userApodo=view.findViewById(R.id.userApodo);
        userContraseña=view.findViewById(R.id.userContraseña);
        usercontraseña2=view.findViewById(R.id.userContraseña2);
        userLiga=view.findViewById(R.id.userLiga);
        userCiudad=view.findViewById(R.id.userCiudad);
        userDescripcion=view.findViewById(R.id.descripcionUser);
        saveButton=view.findViewById(R.id.buttonSave);

        saveButton.setOnClickListener(v -> {
            String c1=userContraseña.getText().toString().trim();
            String c2=usercontraseña2.getText().toString().trim();
            if(!c1.equals(c2)){
                usercontraseña2.setError("Las contraseñas no son las mismas");
            }
            else{
                startActivity(new Intent(container.getContext(), MainActivity.class));
            }
        });
        return view;
    }
}