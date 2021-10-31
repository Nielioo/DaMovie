package com.fei.damovie.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fei.damovie.R;
import com.fei.damovie.helper.Const;
import com.fei.damovie.model.Popular;
import com.fei.damovie.model.Trending;
import com.flaviofaria.kenburnsview.KenBurnsView;

import java.util.List;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder> {

    private Context context;
    private List<Trending.Results> trendingList;

    public TrendingAdapter(Context context) {
        this.context = context;
    }

    public List<Trending.Results> getTrendingList() {
        return trendingList;
    }

    public void setTrendingList(List<Trending.Results> trendingList) {
        this.trendingList = trendingList;
    }

    @NonNull
    @Override
    public TrendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_backdrop, parent, false);
        return new TrendingAdapter.TrendingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingViewHolder holder, int position) {
        final Trending.Results results = getTrendingList().get(position);

        if(results.getBackdrop_path()==null){
            Glide.with(context)
                    .load(R.drawable.damovie_icon)
                    .into(holder.backdrop_kenBurns);
        } else {
            Glide.with(context)
                    .load(Const.IMG_URL+results.getBackdrop_path())
                    .into(holder.backdrop_kenBurns);
        }

        holder.backdrop_title_textView.setText(results.getTitle());

        holder.backdrop_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("movie_id", String.valueOf(results.getId()));
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_movieDetailsFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class TrendingViewHolder extends RecyclerView.ViewHolder {

        CardView backdrop_cardView;
        KenBurnsView backdrop_kenBurns;
        TextView backdrop_title_textView;

        public TrendingViewHolder(@NonNull View itemView) {
            super(itemView);

            backdrop_cardView = itemView.findViewById(R.id.backdrop_cardView);
            backdrop_kenBurns = itemView.findViewById(R.id.backdrop_kenBurns);
            backdrop_title_textView = itemView.findViewById(R.id.backdrop_title_textView);

        }
    }
}
