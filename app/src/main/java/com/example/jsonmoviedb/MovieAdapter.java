package com.example.jsonmoviedb;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<ModelMovie> items;
    private MovieAdapter.OnSelectData onSelectData;
    private Context context;
    private double Rating;

    public interface OnSelectData {
        void onSelected(ModelMovie modelMovie);
    }

    public MovieAdapter(Context context, List<ModelMovie> items,
                        MovieAdapter.OnSelectData onSelectData) {
        this.context = context;
        this.items = items;
        this.onSelectData = onSelectData;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_film, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelMovie data = items.get(position);
        Rating = data.getVoteAverage();
        holder.tvTitle.setText(data.getTitle());
        holder.tvReleaseDate.setText(data.getReleaseDate());
        holder.tvDesc.setText(data.getOverview());

        // Set rating bar
        float newValue = (float) Rating;
        holder.ratingBar.setNumStars(5);
        holder.ratingBar.setStepSize(0.5f);
        holder.ratingBar.setRating(newValue / 2);

        // Load image using Glide
        Glide.with(context)
                .load(ApiEndPoint.URLIMAGE)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_image)
                        .transform( new RoundedCorners(16)))
                .into(holder.imgPhoto);

        // Set click listener
        holder.cvFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectData.onSelected(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("MovieAdapter", "Jumlah item: " + items.size());
        return items.size();
    }

    // ViewHolder class
    class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cvFilm;
        public ImageView imgPhoto;
        public TextView tvTitle;
        public TextView tvReleaseDate;
        public TextView tvDesc;
        public RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            cvFilm = itemView.findViewById(R.id.cvFilm);
            imgPhoto = itemView.findViewById(R.id.imgPhoto);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvReleaseDate = itemView.findViewById(R.id.tvReleaseDate);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}
