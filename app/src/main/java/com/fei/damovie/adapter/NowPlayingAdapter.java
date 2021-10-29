package com.fei.damovie.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fei.damovie.R;
import com.fei.damovie.helper.Const;
import com.fei.damovie.model.NowPlaying;

import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<NowPlaying.Results> nowPlayingList;

    public NowPlayingAdapter(Context context) {
        this.context = context;
    }

    public List<NowPlaying.Results> getNowPlayingList() {
        return nowPlayingList;
    }

    public void setNowPlayingList(List<NowPlaying.Results> nowPlayingList) {
        this.nowPlayingList = nowPlayingList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType != 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie, parent, false);
            return new NowPlayingAdapter.NowPlayingViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.default_progress_bar, parent, false);
            return new NowPlayingAdapter.ProgressBarViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NowPlayingViewHolder) {
            final NowPlaying.Results results = getNowPlayingList().get(position);

            ((NowPlayingViewHolder) holder).movieCard_title_textView.setText(results.getTitle());
            ((NowPlayingViewHolder) holder).movieCard_overview_textView.setText(results.getOverview());

            Glide.with(context)
                    .load(Const.IMG_URL + results.getPoster_path())
                    .into(((NowPlayingViewHolder) holder).movieCard_poster_imageView);

            ((NowPlayingViewHolder) holder).movieCard_cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Bundle bundle = new Bundle();
                    bundle.putString("movie_id", String.valueOf(results.getId()));
                    Navigation.findNavController(view).navigate(R.id.action_nowPlayingFragment_to_movieDetailsFragment, bundle);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return nowPlayingList.get(position) == null ? 0 : 1;
    }

//    @Override
//    public void onBindViewHolder(@NonNull NowPlayingAdapter.NowPlayingViewHolder holder, int position) {
//        final NowPlaying.Results results = getNowPlayingList().get(position);
//
//        holder.movieCard_title_textView.setText(results.getTitle());
//        holder.movieCard_overview_textView.setText(results.getOverview());
//
//        Glide.with(context)
//                .load(Const.IMG_URL+results.getPoster_path())
//                .into(holder.movieCard_poster_imageView);
//
//        holder.movieCard_cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Bundle bundle = new Bundle();
//                bundle.putString("movie_id",String.valueOf(results.getId()));
//                Navigation.findNavController(view).navigate(R.id.action_nowPlayingFragment_to_movieDetailsFragment,bundle);
//            }
//        });
//    }

    @Override
    public int getItemCount() {
        return getNowPlayingList().size();
    }

    public class NowPlayingViewHolder extends RecyclerView.ViewHolder {

        CardView movieCard_cardView;
        TextView movieCard_title_textView, movieCard_overview_textView;
        ImageView movieCard_poster_imageView;

        public NowPlayingViewHolder(@NonNull View itemView) {
            super(itemView);

            movieCard_cardView = itemView.findViewById(R.id.movieCard_cardView);
            movieCard_title_textView = itemView.findViewById(R.id.movieCard_title_textView);
            movieCard_overview_textView = itemView.findViewById(R.id.movieCard_overview_textView);
            movieCard_poster_imageView = itemView.findViewById(R.id.movieCard_poster_imageView);

        }
    }

    public class ProgressBarViewHolder extends RecyclerView.ViewHolder {
        public ProgressBarViewHolder(View view) {
            super(view);
        }
    }
}
