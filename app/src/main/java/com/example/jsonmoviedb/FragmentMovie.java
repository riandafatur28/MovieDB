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
import com.google.android.datatransport.Priority;

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

    public FragmentMovie() {
        // Required empty public constructor
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
        rvFileRecommend = rootView.findViewById(R.id.rvfilmRecommend);
        rvFileRecommend.setHasFixedSize(true);
        rvFileRecommend.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        // Memulai pengambilan data film
        getMovie();

        return rootView;
    }

    private void getMovie() {
        progressDialog.show();

        // Mengambil data film dari API
        AndroidNetworking.get(ApiEndpoint.BASEURL + ApiEndpoint.SEARCH_MOVIE)
                .addQueryParameter("api_key", ApiEndpoint.APIKEY)
                .addQueryParameter("language", ApiEndpoint.QUERY)
                .setPriority(Priority.HIGH)
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ModelMovie dataModel = new ModelMovie();

                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                String datePost = jsonObject.getString("release_date");

                                dataModel.setId(jsonObject.getInt("id"));
                                dataModel.setTitle(jsonObject.getString("title"));
                                dataModel.setVoteAverage(jsonObject.getDouble("vote_average"));
                                dataModel.setOverview(jsonObject.getString("overview"));
                                dataModel.setReleaseDate(dateFormat.format(dateFormat.parse(datePost)));
                                dataModel.setPosterPath(jsonObject.getString("poster_path"));
                                dataModel.setBackdropPath(jsonObject.getString("backdrop_path"));
                                dataModel.setPopularity(jsonObject.getString("popularity"));

                                moviePopular.add(dataModel);
                            }
                            showMovie();
                        } catch (JSONException | ParseException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Gagal menampilkan data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                        // Menampilkan detail error
                        Log.e("NetworkError", "Error Detail: " + anError.getErrorDetail());
                        Log.e("NetworkError", "Error Body: " + anError.getErrorBody());
                        Log.e("NetworkError", "Error Code: " + anError.getErrorCode());
                    }
                });
    }

    private void showMovie() {
        movieAdapter = new MovieAdapter(getActivity(), moviePopular, this);
        rvFileRecommend.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSelected(ModelMovie modelMovie) {
        // Implement action when a movie is selected
    }
}
