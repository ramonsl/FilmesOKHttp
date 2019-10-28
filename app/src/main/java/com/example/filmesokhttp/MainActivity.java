package com.example.filmesokhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

  List<Movie> mMovieList;
  MovieTask mMovieTask;
  TextView txtMovies;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    txtMovies= findViewById(R.id.Txt_Movies);
    mMovieTask = new MovieTask();
    mMovieTask.execute();


  }

  class MovieTask extends AsyncTask<Void,Void, List<Movie>>{

    @Override
    protected List<Movie> doInBackground(Void... voids) {
      MovieServices ms= new MovieServices();
      mMovieList =ms.getTopRated();
      return mMovieList;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
      super.onPostExecute(movies);
      String msg= "Filmes Buscados "+movies.size();
      Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
      txtMovies.setText(msg);

      mMovieList= movies;
    }
  }
}

