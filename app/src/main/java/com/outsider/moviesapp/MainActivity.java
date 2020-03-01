package com.outsider.moviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    TextView title, genre, date, rate, desc;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.idtitle);
        genre = findViewById(R.id.idgenre);
        date = findViewById(R.id.iddate);
        rate = findViewById(R.id.rateid);
        desc = findViewById(R.id.iddesc);
        imageView = findViewById(R.id.idposter);


        title.setText(getIntent().getStringExtra("Title"));
        genre.setText(getIntent().getStringExtra("Genre"));
        date.setText(getIntent().getStringExtra("Released"));
        rate.setText("IMDb : "+getIntent().getStringExtra("imdbRating"));
        desc.setText(getIntent().getStringExtra("Plot"));

        String image = getIntent().getStringExtra("Poster");

        Picasso.get().load(image).placeholder(R.drawable.placeholder).into(imageView);
    }
}
