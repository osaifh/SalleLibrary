package com.example.alumne.sallelibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ApiBuscar extends AppCompatActivity implements View.OnClickListener {
Button busc , fav;
EditText textorev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_buscar);
        busc= findViewById(R.id.botonbuscar);
        textorev = findViewById(R.id.editbuscar);
        busc.setOnClickListener(this);
        fav = findViewById(R.id.mosfavos);
        fav.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.botonbuscar:
                Intent intent = new Intent(ApiBuscar.this, PersonalActivity.class);
                intent.putExtra("nombre",textorev.getText().toString());
                startActivity(intent);
                 break;
            case R.id.mosfavos:
                Intent intento = new Intent(ApiBuscar.this, FavoritosActivity.class);
                startActivity(intento);
                break;
        }

    }
}
