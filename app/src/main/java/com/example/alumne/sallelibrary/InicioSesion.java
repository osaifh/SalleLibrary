package com.example.alumne.sallelibrary;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class InicioSesion extends Fragment implements View.OnClickListener {

    EditText editmail, editpass;
    Button buttonlogin;
    public InicioSesion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_inicio_sesion, container, false);
        editmail = v.findViewById(R.id.email);
        editpass = v.findViewById(R.id.password);
        buttonlogin= v.findViewById(R.id.sign_in);
        buttonlogin.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        String email = editmail.getText().toString();
        String pass = editpass.getText().toString();
        iniciarsesion(email, pass);

    }

    private void iniciarsesion(String email, String password){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    MainActivity mainActivity = (MainActivity)getActivity();

                    mainActivity.aceptado();
                }else{
                    Toast.makeText(getContext(), "Inserte un usuario valido ", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

}
