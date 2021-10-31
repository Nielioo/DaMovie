package com.fei.damovie.view.main.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.fei.damovie.R;
import com.fei.damovie.adapter.PopularAdapter;
import com.fei.damovie.adapter.TopRatedAdapter;
import com.fei.damovie.adapter.TrendingAdapter;
import com.fei.damovie.helper.ProgressBarGoogle;
import com.fei.damovie.model.Popular;
import com.fei.damovie.model.TopRated;
import com.fei.damovie.model.Trending;
import com.fei.damovie.viewModel.MovieViewModel;

public class HomeFragment extends Fragment {

    private ViewPager2 home_trending_viewPager;
    private RecyclerView home_popular_recyclerView, home_topRated_recyclerView;
    private TrendingAdapter trendingAdapter;
    private PopularAdapter popularAdapter;
    private TopRatedAdapter topRatedAdapter;
    private MovieViewModel movieViewModel;
    private ProgressBarGoogle progressBarGoogle;
    private int currentPage;
    private int totalPage;
    private String media_type, time_window;
    private Handler handler;
    private View view;

    private Observer<Trending> showTrending = new Observer<Trending>() {
        @Override
        public void onChanged(Trending trending) {
            trendingAdapter = new TrendingAdapter(getActivity());
            trendingAdapter.setTrendingList(trending.getResults());
            home_trending_viewPager.setAdapter(trendingAdapter);

            progressBarGoogle.stopProgress();
        }
    };

    private Observer<Popular> showPopular = new Observer<Popular>() {
        @Override
        public void onChanged(Popular popular) {
            LinearLayoutManager popularlayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
            popularAdapter = new PopularAdapter(getActivity());
            popularAdapter.setPopularList(popular.getResults());
            home_popular_recyclerView.setAdapter(popularAdapter);
            home_popular_recyclerView.setLayoutManager(popularlayoutManager);

            totalPage = popular.getTotal_pages();

//            popularAdapter.notifyDataSetChanged();
        }
    };

    private Observer<Popular> showNewPopular = new Observer<Popular>() {
        @Override
        public void onChanged(Popular popular) {
            popularAdapter.addPopularList(popular.getResults());

            popularAdapter.notifyDataSetChanged();
        }
    };

    private Observer<TopRated> showTopRated = new Observer<TopRated>() {
        @Override
        public void onChanged(TopRated topRated) {
            LinearLayoutManager topRatedLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
            topRatedAdapter = new TopRatedAdapter(getActivity());
            home_topRated_recyclerView.setAdapter(topRatedAdapter);
            home_topRated_recyclerView.setLayoutManager(topRatedLayoutManager);

            topRatedAdapter.setTopRatedList(topRated.getResults());

            totalPage = topRated.getTotal_pages();

//            topRatedAdapter.notifyDataSetChanged();
        }
    };

    private Observer<TopRated> showNewTopRated = new Observer<TopRated>() {
        @Override
        public void onChanged(TopRated topRated) {
            topRatedAdapter.addTopRatedList(topRated.getResults());

            topRatedAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        initialize();
        setListener();

        return view;
    }

    private void setListener() {
        home_popular_recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (layoutManager.findLastVisibleItemPosition() > recyclerView.getAdapter().getItemCount() - 2) {
                    if ((currentPage * 20) == recyclerView.getAdapter().getItemCount()) {
                        if (currentPage <= totalPage) {
                            currentPage++;

                            movieViewModel.setResultGetPopular(currentPage);
                            movieViewModel.getResultGetPopular().observe(getActivity(), showNewPopular);
                        }
                    }
                }

            }
        });

        home_topRated_recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (layoutManager.findLastVisibleItemPosition() > recyclerView.getAdapter().getItemCount() - 2) {
                    if ((currentPage * 20) == recyclerView.getAdapter().getItemCount()) {
                        if (currentPage <= totalPage) {
                            currentPage++;

                            movieViewModel.setResultGetTopRated(currentPage);
                            movieViewModel.getResultGetTopRated().observe(getActivity(), showNewTopRated);
                        }
                    }
                }

            }
        });

    }

    private void initialize() {
        progressBarGoogle = new ProgressBarGoogle(getActivity());
        progressBarGoogle.startProgress();

        currentPage = 1;
        totalPage = 1;
        media_type = "movie";
        time_window = "day";

        movieViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);

        movieViewModel.setResultGetTrending(media_type, time_window, currentPage);
        movieViewModel.getResultGetTrending().observe(getActivity(),showTrending);

        movieViewModel.setResultGetPopular(currentPage);
        movieViewModel.getResultGetPopular().observe(getActivity(), showPopular);

        movieViewModel.setResultGetTopRated(currentPage);
        movieViewModel.getResultGetTopRated().observe(getActivity(), showTopRated);

        home_trending_viewPager = view.findViewById(R.id.home_trending_viewPager);
        home_popular_recyclerView = view.findViewById(R.id.home_popular_recyclerView);
        home_topRated_recyclerView = view.findViewById(R.id.home_topRated_recyclerView);

        handler = new Handler();

        home_trending_viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);
            }
        });
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(home_trending_viewPager.getCurrentItem()==home_trending_viewPager.getAdapter().getItemCount()-1){
                home_trending_viewPager.setCurrentItem(0);
            } else {
                home_trending_viewPager.setCurrentItem(home_trending_viewPager.getCurrentItem()+1);
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }
}