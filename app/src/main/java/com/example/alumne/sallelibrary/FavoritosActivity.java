package com.example.alumne.sallelibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FavoritosActivity extends AppCompatActivity {
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Book> bokf = new ArrayList<Book>();
    private List<String> nombreslibros = new ArrayList<String>();
    private List<String> uidslibros = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        listView = (ListView) findViewById(R.id.listaservicios);
        db.collection("libros").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("favoritos").addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {


                if (e != null) {
                    //Errorr
                    }
                    for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                        if (doc.getType() == DocumentChange.Type.ADDED) {
                            Book book = new Book();
                            book.setTitle(doc.getDocument().getString("title"));
                            book.setDescription(doc.getDocument().getString("description"));
                            book.setAuthor(doc.getDocument().getString("author"));
                            //Log.i("favvv",book.getAuthor());
                            bokf.add(book);
                            nombreslibros.add(doc.getDocument().getString("title"));
                            uidslibros.add(doc.getDocument().getId());


                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                 Intent intent = new Intent(FavoritosActivity.this, FavoritoSeleccionado.class);
                                intent.putExtra("nombre",bokf.get(position).getAuthor());
                                intent.putExtra("titulo", bokf.get(position).getTitle());
                                intent.putExtra("descripcion", bokf.get(position).getDescription());
                                intent.putExtra("uid", uidslibros.get(position));

                                startActivity(intent);
                                finish();
//




                            }
                        });
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_selectable_list_item, nombreslibros);

                            adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);
                    }
                }


            }

        });
    }
}
