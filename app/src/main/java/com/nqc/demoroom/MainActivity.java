package com.nqc.demoroom;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String DATABASE_NAME = "movies_db";
    private MovieDatabase movieDatabase;
    EditText edtId, edtName;
    Button btnSave, btnSeach;
    ListView lv;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieDatabase = Room.databaseBuilder(getApplicationContext(),
                MovieDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movies movies = new Movies();
                movies.setMovieId(edtId.getText().toString());
                movies.setMovieName(edtName.getText().toString());
                InsertMoviesTask task = new InsertMoviesTask();
                task.execute(movies);
            }
        });
        btnSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movies movies= new Movies();
                movies.setMovieId(edtId.getText().toString());
                SeachMoviesTask seachMoviesTask= new SeachMoviesTask();
                seachMoviesTask.execute(movies);
            }
        });
    }

    private void addControls() {
        ds = new ArrayList<>();
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtTen);
        btnSave = findViewById(R.id.btnLuu);
        btnSeach = findViewById(R.id.btnXem);
        lv = findViewById(R.id.lv);
        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, ds);
        lv.setAdapter(arrayAdapter);
    }

    class InsertMoviesTask extends AsyncTask<Movies, Void, Void> {
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Movies... movies) {
            movieDatabase.daoAccess().insertOnlySingleMovie(movies[0]);
            return null;
        }
    }

    class SeachMoviesTask extends AsyncTask<Movies, Void, Movies> {
        @Override
        protected void onPostExecute(Movies movies) {
            super.onPostExecute(movies);
            edtName.setText(movies.getMovieName());
        }

        @Override
        protected Movies doInBackground(Movies... movies) {
           return movieDatabase.daoAccess().timMoviesTheoID(movies[0].getMovieId());
        }
    }
}