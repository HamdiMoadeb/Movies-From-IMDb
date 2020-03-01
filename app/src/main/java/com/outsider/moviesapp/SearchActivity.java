package com.outsider.moviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class SearchActivity extends AppCompatActivity {


    ImageButton searchbtn;
    EditText editsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchbtn = findViewById(R.id.searchbtn);
        editsearch = findViewById(R.id.searchedit);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String moviename = editsearch.getText().toString();
                moviename = moviename.replace(" ", "%20");

                Ion.with(SearchActivity.this)
                        .load("http://www.omdbapi.com/?t="+moviename+"&apikey=fc41c070")
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {

                                if (e == null){
                                    System.out.println("/////"+result);
                                    if(!result.get("Response").getAsString().equals("False")){

                                        String title = result.get("Title").getAsString();
                                        String poster = result.get("Poster").getAsString();
                                        String rate = result.get("imdbRating").getAsString();
                                        String genre = result.get("Genre").getAsString();
                                        String desc = result.get("Plot").getAsString();
                                        String date = result.get("Released").getAsString();

                                        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                                            intent.putExtra("Title", title);
                                            intent.putExtra("Poster", poster);
                                            intent.putExtra("imdbRating", rate);
                                            intent.putExtra("Genre", genre);
                                            intent.putExtra("Plot", desc);
                                            intent.putExtra("Released", date);
                                        startActivity(intent);

                                    }else{
                                        Toast.makeText(SearchActivity.this, "Movie not found!", Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(SearchActivity.this, "Movie not found!"+e, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
