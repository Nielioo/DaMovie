package com.fei.damovie.view.main.nowPlaying;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.fei.damovie.R;
import com.fei.damovie.adapter.NowPlayingAdapter;
import com.fei.damovie.model.NowPlaying;
import com.fei.damovie.viewModel.MovieViewModel;

public class NowPlayingFragment extends Fragment {

    private RecyclerView nowPlaying_recyclerView;
    private MovieViewModel viewModel;
    private View view;

    private int currentPage = 1;
    private int totalPage = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_now_playing, container, false);

        initialize();

        return view;
    }

    private void setViewModel() {
        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        viewModel.setResultGetNowPlaying(currentPage);
        viewModel.getResultGetNowPlaying().observe(getActivity(), showNowPlaying);
    }

    private void initialize(){
        nowPlaying_recyclerView= view.findViewById(R.id.nowPlaying_recyclerView);

        setViewModel();
    }

    private void setProgressBar() {
//        ProgressBar progressBar = view.findViewById(R.id.main_progressBar);
//        if(currentPage == 1){
//            progressBar.setVisibility(View.INVISIBLE);
//        } else {
//
//        }
    }

    private Observer<NowPlaying> showNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {
            nowPlaying_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            NowPlayingAdapter adapter = new NowPlayingAdapter(getActivity());
            adapter.setNowPlayingList(nowPlaying.getResults());
            nowPlaying_recyclerView.setAdapter(adapter);

            totalPage = nowPlaying.getTotal_pages();

            nowPlaying_recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if(!nowPlaying_recyclerView.canScrollVertically(1)){
                        if(currentPage <= totalPage){
                            currentPage += 1;

                        }
                    }

                }
            });




            setProgressBar();

        }
    };

}