package com.example.alumne.sallelibrary;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class PersonalActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        LibrosFragment a = new LibrosFragment();
        String nombre = getIntent().getStringExtra("nombre");
        Bundle bundle = new Bundle();
        bundle.putString("nombre",nombre);
        a.setArguments(bundle);
        FragmentManager b = getFragmentManager();
        b.beginTransaction().add(R.id.pantallaentera,a).commit();
    }

    public void cambioFragment(Bundle bundle){

        LibroSeleccionado c = new LibroSeleccionado();
        c.setBundle(bundle);
        FragmentManager d = getFragmentManager();

        // En este caso utilizamo el replace para que quite el fragment que hay por el indicado
        d.beginTransaction().replace(R.id.pantallaentera,c).addToBackStack(null).commit();


    }
    public void openother(){
        Intent intent = new Intent(PersonalActivity.this, FavoritosActivity.class);
        startActivity(intent);
    }
}
