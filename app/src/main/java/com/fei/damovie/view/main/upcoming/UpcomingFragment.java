package com.fei.damovie.view.main.upcoming;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fei.damovie.R;
import com.fei.damovie.adapter.NowPlayingAdapter;
import com.fei.damovie.adapter.UpcomingAdapter;
import com.fei.damovie.model.Upcoming;
import com.fei.damovie.viewModel.MovieViewModel;

public class UpcomingFragment extends Fragment {

    private RecyclerView upcoming_recyclerView;
    private MovieViewModel viewModel;
    private View view;

    private int currentPage = 1;
    private int totalPage = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_upcoming, container, false);

        upcoming_recyclerView = view.findViewById(R.id.upcoming_recyclerView);
        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        viewModel.setResultGetUpcoming(currentPage);
        viewModel.getResultGetUpcoming().observe(getActivity(), showUpcoming);

        return view;
    }

    private Observer<Upcoming> showUpcoming = new Observer<Upcoming>() {
        @Override
        public void onChanged(Upcoming upcoming) {
            upcoming_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            UpcomingAdapter adapter = new UpcomingAdapter(getActivity());
            adapter.setUpcomingList(upcoming.getResults());
            upcoming_recyclerView.setAdapter(adapter);
        }
    };

}