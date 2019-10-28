package com.example.filmesokhttp;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieServices {
  private static final String API_KEY="d15439ba9445688264047fbb91fce4c4";



  public List<Movie> getTopRated(){
    String API_TOP_RATED= "https://api.themoviedb.org/3/movie/top_rated?api_key="+API_KEY;
    //@JsonPropetirs("results")
    List<Movie> readValues= new ArrayList<>();
    try {
      OkHttpClient client = new OkHttpClient();
      Request request= new Request.Builder()
        .url(API_TOP_RATED)
        .build();
      Response response = client.newCall(request).execute();
      String json= response.body().string();

      JSONObject resultado = new JSONObject(json);
      JSONArray movies = resultado.getJSONArray("results");
      //@JsonPropetirs("results")
       readValues= new ObjectMapper().readValue(movies.toString(), new TypeReference<List<Movie>>() { });

        Log.e("API",json);
        return readValues;
    }catch (Exception e){
      Log.e("API",e.getMessage());
    }
    return readValues;




  }

}
