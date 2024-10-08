package com.example.jsonmoviedb;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FragmentMovie extends Fragment implements MovieAdapter.OnSelectData {

    private RecyclerView rvFileRecommend;
    private MovieAdapter movieAdapter;
    private ProgressDialog progressDialog;
    private List<ModelMovie> moviePopular = new ArrayList<>();
    private ApiEndPoint ApiEndpoint = new ApiEndPoint();

    public FragmentMovie() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_film, container, false);

        // Inisialisasi ProgressDialog
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        // Inisialisasi RecyclerView
        movieAdapter = new MovieAdapter(getActivity(), moviePopular, this);
        rvFileRecommend = rootView.findViewById(R.id.rvfilmRecommend);
        rvFileRecommend.setAdapter(movieAdapter);
        rvFileRecommend.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvFileRecommend.setHasFixedSize(true);



        // Memulai pengambilan data film
        getMovieHorizontal();
        getMovie();

        return rootView;
    }

    private void setSearchMovie(String query){
        progressDialog.show();

        AndroidNetworking.get(ApiEndpoint.BASEURL + ApiEndpoint.SEARCH_MOVIE + ApiEndpoint.APIKEY + ApiEndpoint.LANGUAGE + ApiEndpoint.QUERY + query).setPriority(Priority.HIGH).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    progressDialog.dismiss();
                    moviePopular = new ArrayList<>();
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ModelMovie dataApi = new ModelMovie();
                        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMMM yyyy");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                        String datePost = jsonObject.getString("release_date");

                        dataApi.setId(jsonObject.getInt("id"));
                        dataApi.setTitle(jsonObject.getString("title"));
                        dataApi.setVoteAverage(jsonObject.getDouble("vote_average"));
                        dataApi.setOverview(jsonObject.getString("overview"));
                        dataApi.setReleaseDate(formatter.format(dateFormat.parse(datePost)));
                        dataApi.setPosterPath(jsonObject.getString("poster_path"));
                        dataApi.setBackdropPath(jsonObject.getString("backdrop_path"));
                        dataApi.setPopularity(jsonObject.getString("popularity"));
                        moviePopular.add(dataApi);
                    }
                        showMovie();
                } catch (JSONException  | ParseException e ) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(ANError anError) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Tidak ada jaringan Internet", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getMovieHorizontal(){
        progressDialog.show();
        AndroidNetworking.get(ApiEndpoint.BASEURL + ApiEndpoint.MOVIE_PLAYNOW + ApiEndpoint.APIKEY + ApiEndpoint.LANGUAGE)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            JSONArray jsonArray = response.getJSONArray("results");
                            Log.d("API Response", "Total movies in getMovieHorizontal: " + jsonArray.length());

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ModelMovie dataApi = new ModelMovie();
                                SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMMM yyyy");
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Corrected format

                                String datePost = jsonObject.getString("release_date");

                                dataApi.setId(jsonObject.getInt("id"));
                                dataApi.setTitle(jsonObject.getString("title"));
                                dataApi.setVoteAverage(jsonObject.getDouble("vote_average"));
                                dataApi.setOverview(jsonObject.getString("overview"));
                                dataApi.setReleaseDate(formatter.format(dateFormat.parse(datePost)));
                                dataApi.setPosterPath(jsonObject.getString("poster_path"));
                                dataApi.setBackdropPath(jsonObject.getString("backdrop_path"));
                                dataApi.setPopularity(jsonObject.getString("popularity"));
                                moviePopular.add(dataApi);
                            }
                            showMovie();
                        } catch (JSONException | ParseException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Log.d("API Response", "Error Code: " + anError.getErrorCode());
                        Log.d("API Error", anError.getErrorDetail());
                        Toast.makeText(getActivity(), "Tidak ada jaringan Internet", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void getMovie() {
        progressDialog.show();
        AndroidNetworking.get(ApiEndpoint.BASEURL + ApiEndpoint.MOVIE_POPULAR + ApiEndpoint.APIKEY + ApiEndpoint.LANGUAGE)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            JSONArray jsonArray = response.getJSONArray("results");
                            Log.d("API Response", "Total movies in getMovieHorizontal: " + jsonArray.length());

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ModelMovie dataApi = new ModelMovie();
                                SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMMM yyyy");
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                String datePost = jsonObject.getString("release_date");

                                dataApi.setId(jsonObject.getInt("id"));
                                dataApi.setTitle(jsonObject.getString("title"));
                                dataApi.setVoteAverage(jsonObject.getDouble("vote_average"));
                                dataApi.setOverview(jsonObject.getString("overview"));
                                dataApi.setReleaseDate(formatter.format(dateFormat.parse(datePost)));
                                dataApi.setPosterPath(jsonObject.getString("poster_path"));
                                dataApi.setBackdropPath(jsonObject.getString("backdrop_path"));
                                dataApi.setPopularity(jsonObject.getString("popularity"));
                                moviePopular.add(dataApi);
                            }
                            showMovie();
                        } catch (JSONException | ParseException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Log.d("API Response", "Error Code: " + anError.getErrorCode());
                        Log.d("API Error", anError.getErrorDetail());
                        Toast.makeText(getActivity(), "Tidak ada jaringan Internet", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void showMovie() {
//        movieAdapter = new MovieAdapter(getActivity(), moviePopular, this);
//        rvFileRecommend.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSelected(ModelMovie modelMovie) {
    }
}
