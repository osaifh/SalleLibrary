package com.example.alumne.sallelibrary;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroSesion extends Fragment implements View.OnClickListener {

    EditText editmail, editpass;
    Button buttonregister;
    public RegistroSesion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_registro_sesion, container, false);
        editmail = v.findViewById(R.id.email);
        editpass = v.findViewById(R.id.password);
        buttonregister= v.findViewById(R.id.sign_up);
        buttonregister.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        String email = editmail.getText().toString();
        String pass = editpass.getText().toString();
        registrar(email, pass);
    }
    private void registrar(String email, String password){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.i("SESION","Sesion registrada ");
                if (task.isSuccessful()){
                    Log.i("SESION","Sesion registrada ");
                    MainActivity mainActivity = (MainActivity)getActivity();
                    mainActivity.aceptado();
                }else {
                    Log.i("SESION", task.getException().getMessage() + "");
                }
            }
        });


    }
}
