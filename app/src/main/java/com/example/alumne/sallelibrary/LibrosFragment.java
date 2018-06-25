package com.example.alumne.sallelibrary;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LibrosFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    public static String VOLUMES_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    TextView maili;
    ListView lista;
    Button favori;
    public List<Book> listasitems = new ArrayList<>();
    private CustomBaseAdapter adapteritems;

    public static final String selbook= "Libro seleccionado";

    private String nombrelibro;


    public LibrosFragment() {

    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentç
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String mail = user.getEmail();
        nombrelibro = getArguments().getString("nombre");
        getVolumes getVolumes = new getVolumes();
        getVolumes.execute();
//        listasitems = l.GetBookList("potter");
//        System.out.println(listasitems);

        View v = inflater.inflate(R.layout.fragment_libros, container, false);
        lista = v.findViewById(R.id.list);
        lista.setOnItemClickListener(this);
        maili = v.findViewById(R.id.namep);
        maili.setText(mail);
        favori = v.findViewById(R.id.favori);
        favori.setOnClickListener(this);
        listasitems = new ArrayList<>();
//        adapteritems = new ArrayAdapter<Book>(getContext(), android.R.layout.simple_list_item_1, listasitems);
//
//        lista.setAdapter(adapteritems);

         adapteritems = new CustomBaseAdapter(getContext(), listasitems);
        lista.setAdapter(adapteritems);

        return v;


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PersonalActivity personalActivity =(PersonalActivity) getActivity();
        Bundle bundle = new Bundle();

        bundle.putString("nombre",listasitems.get(position).getAuthor());
        bundle.putString("titulo",listasitems.get(position).getTitle());
        bundle.putString("descripcion",listasitems.get(position).getDescription());
        bundle.putString("imagen",listasitems.get(position).getImage_url());

        personalActivity.cambioFragment(bundle);

    }
    @Override
    public void onClick(View v) {
        PersonalActivity personalActivity =(PersonalActivity) getActivity();
        personalActivity.openother();
    }

    private class getVolumes extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(VOLUMES_URL + nombrelibro + "&maxResults=6")
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                String responseStr = response.body().string();
                try {
                    parseJSONVolumes(responseStr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (isAdded() && getActivity() != null) {
                adapteritems.notifyDataSetChanged();

            }
        }
    }
    public void parseJSONVolumes(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);

        JSONArray jsonArray = new JSONArray(jsonObject.getString("items"));


        for (int i = 0; i < jsonArray.length(); i++) {
            Book book = new Book();
            JSONObject datosBook = jsonArray.getJSONObject(i);

            JSONObject librosaado = new JSONObject(datosBook.getString("volumeInfo"));
            String title = librosaado.getString("title");
            JSONArray authors = new JSONArray(librosaado.optString("authors"));
            String author = String.valueOf(authors.get(0));
            String description = librosaado.optString("description");
            JSONObject imageLinks = new JSONObject(librosaado.optString("imageLinks"));
            String image = imageLinks.optString("thumbnail");




            book.setTitle(title);
            book.setAuthor(author);;
            book.setDescription(description);
            book.setImage_url(image);
            listasitems.add(book);

        }

    }

    }

