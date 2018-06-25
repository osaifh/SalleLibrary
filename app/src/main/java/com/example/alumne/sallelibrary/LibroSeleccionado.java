package com.example.alumne.sallelibrary;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


/**
 * A simple {@link Fragment} subclass.
 */
public class LibroSeleccionado extends Fragment implements View.OnClickListener {
    Button insertarfav;
    Bundle bundlee;
    TextView textfag, titulo, descripcion;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public LibroSeleccionado() {
        // Required empty public constructor
    }

    public void setBundle(Bundle bundle) {
        bundlee = bundle;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_libro_seleccionado, container, false);

        textfag = v.findViewById(R.id.textonombre);
        titulo = v.findViewById(R.id.titulo);
        descripcion = v.findViewById(R.id.descripcion);
        insertarfav = v.findViewById(R.id.insertfav);
        insertarfav.setOnClickListener(this);
        

        if (bundlee != null) {
            textfag.setText(bundlee.getString("nombre"));
            titulo.setText(bundlee.getString("titulo"));
            descripcion.setText(bundlee.getString("descripcion"));

        }
        return v;
    }

    @Override
    public void onClick(final View v) {
        Book book = new Book(bundlee.getString("titulo"),bundlee.getString("descripcion"),bundlee.getString("nombre"));
        db.collection("libros").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("favoritos").document().set(book).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    insertarfav.setEnabled(false);
                    Toast.makeText(v.getContext(),"Libro guardado como favorito",Toast.LENGTH_LONG).show();

                }
            }
        });
    }


}
