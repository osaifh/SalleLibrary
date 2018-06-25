package com.example.alumne.sallelibrary;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button inicio , registro;

    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InicioSesion a = new InicioSesion();
        FragmentManager b = getFragmentManager();
        b.beginTransaction().add(R.id.fralog,a).commit();
        inicio= findViewById(R.id.button_sign_in);
        inicio.setOnClickListener(this);
        registro= findViewById(R.id.button_sign_up);
        registro.setOnClickListener(this);


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user !=null){
                    Log.i("SESION","Sesion iniciada con " + user.getEmail());
                    System.out.println(user.getEmail());
                    Toast.makeText(getApplicationContext(), "Sesion iniciada con  " + user.getEmail(), Toast.LENGTH_SHORT).show();
                    if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                        Intent intent = new Intent(MainActivity.this, ApiBuscar.class);
                        startActivityForResult(intent, 0);

                    }

                }else {
                    Log.i("SESION","Sesion cerrada");
                    Toast.makeText(getApplicationContext(), "Necesario iniciar sesión ", Toast.LENGTH_SHORT).show();
                }
            }
        };


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_sign_in:
                InicioSesion a = new InicioSesion();
                FragmentManager b = getFragmentManager();
                b.beginTransaction().replace(R.id.fralog,a).commit();


                break;

            case R.id.button_sign_up:
                RegistroSesion j = new RegistroSesion();
                FragmentManager k = getFragmentManager();
                k.beginTransaction().replace(R.id.fralog,j).commit();
                break;


        }
    }

  public void aceptado(){
      Intent intent = new Intent(MainActivity.this, ApiBuscar.class);
      startActivityForResult(intent, 0);
  }


    @Override
    protected void onStart(){
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }
    @Override
    protected void onStop(){
        super.onStop();
        if (mAuthListener != null)
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
    }

}
