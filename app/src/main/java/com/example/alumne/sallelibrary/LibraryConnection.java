package com.example.alumne.sallelibrary;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class LibraryConnection {
    HttpURLConnection connection;
    String apiUrlString = "https://www.googleapis.com/books/v1/volumes?q=";

    public List<Book> GetBookList(String query){
        List<Book> bookList = new ArrayList<>();
        try {
            JSONObject jsonObject = RetrieveBooks(query);
            bookList = RetrieveBookList(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public List<Book> RetrieveBookList(JSONObject jsonObject){
        List<Book> bookList = new ArrayList<Book>();
        try {
            JSONArray jArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < jArray.length(); i++){
                Book book = new Book();
                JSONObject volumeInfo = jArray.getJSONObject(i).getJSONObject("volumeInfo");

                String title = volumeInfo.getString("title");
                book.setTitle("something");
                int titleSize = title.length();
                book.setTitle(title);
/*
                JSONArray authors = volumeInfo.getJSONArray("authors");
                for(int j =0; j< authors.length(); j++){
                    String author = authors.getString(i);
                    book.setAuthor(author);
                }
*/
                bookList.add(book);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public JSONObject RetrieveBooks(String searchString) throws IOException, JSONException {
        //Revisar
        //He supuesto que el fallo esta aqui en que faltaba la key de la api pero sigue sin funcionar
        String apikey = "&AIzaSyBS-6ZoZspem5KDvpdBPd_888Ge9BTYC14";
        String queryString = apiUrlString + searchString + apikey ;
        //queryString = URLEncoder.encode(queryString, "UTF-8");
        try {
            URL url = new URL(queryString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int responseCode = connection.getResponseCode();
        if (responseCode != 200){
            //query a GoogleBooksAPI ha fallat
            connection.disconnect();
            return null;
        }

        StringBuilder builder = new StringBuilder();
        BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = responseReader.readLine();
        while (line != null){
            builder.append(line);
            line = responseReader.readLine();
        }
        String responseString = builder.toString();
        JSONObject responseJson = new JSONObject(responseString);
        // Close connection and return response code.
        connection.disconnect();
        return responseJson;
    }
    public class atras extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            return null;
        }
    }
}
