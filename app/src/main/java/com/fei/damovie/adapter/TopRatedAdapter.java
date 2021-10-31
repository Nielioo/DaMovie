package com.fei.damovie.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fei.damovie.R;
import com.fei.damovie.helper.Const;
import com.fei.damovie.model.Popular;
import com.fei.damovie.model.TopRated;

import java.util.ArrayList;
import java.util.List;

public class TopRatedAdapter extends RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder> {

    private Context context;
    private List<TopRated.Results> topRatedList;

    public TopRatedAdapter(Context context) {
        this.context = context;
    }

    public List<TopRated.Results> getTopRatedList() {
        return topRatedList;
    }

    public void setTopRatedList(List<TopRated.Results> topRatedList) {
        this.topRatedList = topRatedList;
    }

    @NonNull
    @Override
    public TopRatedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_poster, parent, false);
        return new TopRatedAdapter.TopRatedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopRatedViewHolder holder, int position) {
        final TopRated.Results results = getTopRatedList().get(position);

        if(results.getPoster_path()==null){
            Glide.with(context)
                    .load(R.drawable.damovie_icon)
                    .into(holder.poster_imageView);
        } else {
            Glide.with(context)
                    .load(Const.IMG_URL+results.getPoster_path())
                    .into(holder.poster_imageView);
        }

        holder.poster_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("movie_id", String.valueOf(results.getId()));
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_movieDetailsFragment, bundle);
            }
        });

    }

    public void addTopRatedList(List<TopRated.Results> results){
        topRatedList.addAll(results);
    }

    @Override
    public int getItemCount() {
        return getTopRatedList().size();
    }

    public class TopRatedViewHolder extends RecyclerView.ViewHolder {

        CardView poster_cardView;
        ImageView poster_imageView;

        public TopRatedViewHolder(@NonNull View itemView) {
            super(itemView);

            poster_cardView = itemView.findViewById(R.id.poster_cardView);
            poster_imageView = itemView.findViewById(R.id.poster_imageView);

        }
    }
}
