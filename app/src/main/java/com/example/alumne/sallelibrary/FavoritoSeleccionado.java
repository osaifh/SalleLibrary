package com.example.alumne.sallelibrary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class FavoritoSeleccionado extends AppCompatActivity implements View.OnClickListener {
    TextView textfag, titulo, descripcion;
    Button quitarfavo;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorito_seleccionado);
        textfag = findViewById(R.id.textonombre);
        titulo = findViewById(R.id.titulo);
        descripcion = findViewById(R.id.descripcion);
        textfag.setText(getIntent().getExtras().getString("nombre"));
        titulo.setText(getIntent().getExtras().getString("titulo"));
        descripcion.setText(getIntent().getExtras().getString("descripcion"));
        quitarfavo = findViewById(R.id.quitarfavo);
        quitarfavo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        db.collection("libros").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("favoritos").document(getIntent().getExtras().getString("uid"))
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Borrado", "DocumentSnapshot successfully deleted!");
                        finish();
                        Intent intent = new Intent(FavoritoSeleccionado.this, FavoritosActivity.class);


                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Error borrado", "Error deleting document", e);
                    }
                });
    }
}
