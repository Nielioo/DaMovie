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
import com.fei.damovie.model.Movies;
import com.fei.damovie.model.Popular;

import java.util.ArrayList;
import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder>{

    private Context context;
    private List<Popular.Results> popularList;

    public PopularAdapter(Context context) {
        this.context = context;
    }

    public List<Popular.Results> getPopularList() {
        return popularList;
    }

    public void setPopularList(List<Popular.Results> popularList) {
        this.popularList = popularList;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_poster, parent, false);
        return new PopularAdapter.PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        final Popular.Results results = getPopularList().get(position);

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

    public void addPopularList(List<Popular.Results> results){
        popularList.addAll(results);
    }

    @Override
    public int getItemCount() {
        return getPopularList().size();
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder {

        CardView poster_cardView;
        ImageView poster_imageView;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);

            poster_cardView = itemView.findViewById(R.id.poster_cardView);
            poster_imageView = itemView.findViewById(R.id.poster_imageView);

        }
    }
}
