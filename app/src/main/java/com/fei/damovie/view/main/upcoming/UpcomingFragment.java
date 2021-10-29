package com.fei.damovie.view.main.upcoming;

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
import com.fei.damovie.adapter.UpcomingAdapter;
import com.fei.damovie.model.Upcoming;
import com.fei.damovie.viewModel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class UpcomingFragment extends Fragment {

    private RecyclerView upcoming_recyclerView;
    private MovieViewModel viewModel;
    private UpcomingAdapter adapter;
    private View view;

    private List<Upcoming.Results> results;
    private int currentPage;
    private int totalPage;
    private boolean isLoading;

    private Observer<Upcoming> showUpcoming = new Observer<Upcoming>() {
        @Override
        public void onChanged(Upcoming upcoming) {

            totalPage = upcoming.getTotal_pages();

            for (int i = 0; i < upcoming.getResults().size(); i++) {
                results.add(upcoming.getResults().get(i));
            }

            adapter.notifyDataSetChanged();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_upcoming, container, false);

        initialize();
        setScrollListener();

        return view;
    }

    private void setScrollListener() {
        upcoming_recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

                        if (!upcoming_recyclerView.canScrollVertically(1)) {
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

    private void initialize() {
        upcoming_recyclerView = view.findViewById(R.id.upcoming_recyclerView);
        results = new ArrayList<>();
        currentPage = 1;
        totalPage = 1;
        isLoading = false;

        upcoming_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new UpcomingAdapter(getActivity());
        adapter.setUpcomingList(results);
        upcoming_recyclerView.setAdapter(adapter);

        setViewModel();
    }

    private void setViewModel() {
        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        viewModel.setResultGetUpcoming(currentPage);
        viewModel.getResultGetUpcoming().observe(getActivity(), showUpcoming);
    }


}