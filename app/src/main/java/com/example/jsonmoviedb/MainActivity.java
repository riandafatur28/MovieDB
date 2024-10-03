package com.example.jsonmoviedb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView showJson = findViewById(R.id.json);
        ImageView showJsonApi = findViewById(R.id.json_api);
        ImageView showMovieDB = findViewById(R.id.moviedb);

        showJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, JsonParseActivity.class);
                startActivity(intent);
            }
        });


        showJsonApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, JsonAPIActivity.class);
                startActivity(intent);
            }
        });

        showMovieDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MovieDBActivity.class);
                startActivity(intent);
            }
        });
    }
}
