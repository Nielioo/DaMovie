package com.fei.damovie.view.main.nowPlaying;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fei.damovie.R;
import com.fei.damovie.adapter.NowPlayingAdapter;
import com.fei.damovie.model.NowPlaying;
import com.fei.damovie.viewModel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class NowPlayingFragment extends Fragment {

    private RecyclerView nowPlaying_recyclerView;
    private MovieViewModel viewModel;
    private NowPlayingAdapter adapter;
    private View view;

    private List<NowPlaying.Results> results;
    private int currentPage;
    private int totalPage;
    private boolean isLoading;

    private Observer<NowPlaying> showNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {

            totalPage = nowPlaying.getTotal_pages();

            for (int i = 0; i < nowPlaying.getResults().size(); i++) {
                results.add(nowPlaying.getResults().get(i));
            }


            adapter.notifyDataSetChanged();

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_now_playing, container, false);

        initialize();
        setScrollListener();

        return view;
    }

    private void setScrollListener() {
        nowPlaying_recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() == results.size() - 1) {
                        isLoading = true;

                        results.add(null);
                        adapter.notifyItemInserted(results.size() - 1);
                        results.remove(null);

                        if (!nowPlaying_recyclerView.canScrollVertically(1)) {
                            if (currentPage <= totalPage) {
                                currentPage += 1;
                                setViewModel();
                            }
                        }

                        isLoading = false;
                    }
                }

            }
        });
    }

    private void setViewModel() {
        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        viewModel.setResultGetNowPlaying(currentPage);
        viewModel.getResultGetNowPlaying().observe(getActivity(), showNowPlaying);
    }

    private void initialize() {
        nowPlaying_recyclerView = view.findViewById(R.id.nowPlaying_recyclerView);
        results = new ArrayList<>();
        currentPage = 1;
        totalPage = 1;
        isLoading = false;

        nowPlaying_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new NowPlayingAdapter(getActivity());
        adapter.setNowPlayingList(results);
        nowPlaying_recyclerView.setAdapter(adapter);

        setViewModel();
    }

}