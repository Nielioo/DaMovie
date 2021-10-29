package com.fei.damovie.view.main.movieDetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fei.damovie.R;
import com.fei.damovie.adapter.CompanyAdapter;
import com.fei.damovie.adapter.PersonAdapter;
import com.fei.damovie.helper.Const;
import com.fei.damovie.helper.ItemClickSupport;
import com.fei.damovie.helper.ProgressBarGoogle;
import com.fei.damovie.model.Credits;
import com.fei.damovie.model.Movies;
import com.fei.damovie.viewModel.CreditViewModel;
import com.fei.damovie.viewModel.MovieViewModel;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;

import java.util.List;

public class MovieDetailsFragment extends Fragment {

    private KenBurnsView movieDetails_backdrop_kenBurns;
    private ImageView movieDetails_poster_imageView;
    private TextView movieDetails_title_textView, movieDetails_rating_textView,movieDetails_genre_textView, movieDetails_tagline_textView,movieDetails_overview_textView,movieDetails_popularity_textView,movieDetails_vote_textView,movieDetails_releaseDate_textView;
    private RecyclerView movieDetails_company_recyclerView, movieDetails_cast_recyclerView;
    private ProgressBarGoogle progressBarGoogle;
    private MovieViewModel movieViewModel;
    private CreditViewModel creditViewModel;
    private String movie_id = "";
    private String credit_id = "";
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        initialize();

        return view;
    }

    private void setAnimation() {
        RandomTransitionGenerator randomTransitionGenerator = new RandomTransitionGenerator(1000, new DecelerateInterpolator());
        movieDetails_backdrop_kenBurns.setTransitionGenerator(randomTransitionGenerator);
    }

    private void initialize() {
        progressBarGoogle = new ProgressBarGoogle(getActivity());
        progressBarGoogle.startProgress();

        movie_id = getArguments().getString("movie_id");

        movieViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        movieViewModel.setResultGetMovieById(movie_id);
        movieViewModel.getResultGetMovieById().observe(getActivity(), showMovieDetails);

//        creditViewModel = new ViewModelProvider(getActivity()).get(CreditViewModel.class);
//        creditViewModel.setResultGetCreditById(credit_id);
//        creditViewModel.getResultGetCreditById().observe(getActivity(), showCreditDetails);

        movieDetails_backdrop_kenBurns = view.findViewById(R.id.movieDetails_backdrop_kenBurns);
        movieDetails_poster_imageView = view.findViewById(R.id.movieDetails_poster_imageView);
        movieDetails_title_textView = view.findViewById(R.id.movieDetails_title_textView);
        movieDetails_rating_textView = view.findViewById(R.id.movieDetails_rating_textView);
        movieDetails_genre_textView = view.findViewById(R.id.movieDetails_genre_textView);
        movieDetails_genre_textView.setText("");
        movieDetails_tagline_textView = view.findViewById(R.id.movieDetails_tagline_textView);
        movieDetails_overview_textView = view.findViewById(R.id.movieDetails_overview_textView);
        movieDetails_popularity_textView = view.findViewById(R.id.movieDetails_popularity_textView);
        movieDetails_vote_textView = view.findViewById(R.id.movieDetails_vote_textView);
        movieDetails_releaseDate_textView = view.findViewById(R.id.movieDetails_releaseDate_textView);
        movieDetails_company_recyclerView = view.findViewById(R.id.movieDetails_company_recyclerView);
//        movieDetails_cast_recyclerView = view.findViewById(R.id.movieDetails_cast_recyclerView);

        setAnimation();
    }

    private Observer<Movies> showMovieDetails = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {

            String backdrop_path = movies.getBackdrop_path().toString().trim();
            String poster_path = movies.getPoster_path().toString().trim();
            String title = movies.getTitle();
            String rating = String.valueOf(movies.getVote_average());
            List<Movies.Genres> genresList = movies.getGenres();
            String tagline = movies.getTagline();
            String overview = movies.getOverview();
            String popularity = String.valueOf(movies.getPopularity());
            String vote = String.valueOf(movies.getVote_count()) ;
            String releaseDate = movies.getRelease_date();
            List<Movies.ProductionCompanies> productionCompaniesList = movies.getProduction_companies();

            if(!(backdrop_path == null)){
                String full_path = Const.IMG_URL + backdrop_path;
                Glide.with(getActivity())
                        .load(full_path)
                        .into(movieDetails_backdrop_kenBurns);
            }

            if(!(poster_path == null)){
                String full_path = Const.IMG_URL + poster_path;
                Glide.with(getActivity())
                        .load(full_path)
                        .into(movieDetails_poster_imageView);
            }

            movieDetails_title_textView.setText(title);
            movieDetails_rating_textView.setText(rating);

            for (int i = 0; i < genresList.size(); i++) {
                Movies.Genres g = genresList.get(i);

                if (i < genresList.size() - 1) {
                    movieDetails_genre_textView.append(g.getName() + " . ");
                } else {
                    movieDetails_genre_textView.append(g.getName());
                }

            }

            movieDetails_tagline_textView.setText(tagline);
            movieDetails_overview_textView.setText(overview);
            movieDetails_popularity_textView.setText(popularity);
            movieDetails_vote_textView.setText(vote);
            movieDetails_releaseDate_textView.setText(releaseDate);

            //==Begin of Company RecyclerView
            LinearLayoutManager companylayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
            CompanyAdapter companyAdapter = new CompanyAdapter(getActivity());
            companyAdapter.setCompanyList(movies.getProduction_companies());
            movieDetails_company_recyclerView.setAdapter(companyAdapter);
            movieDetails_company_recyclerView.setLayoutManager(companylayoutManager);

            ItemClickSupport.addTo(movieDetails_company_recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    String company = productionCompaniesList.get(position).getName();

                    Toast.makeText(getActivity(), company, Toast.LENGTH_SHORT).show();
                }
            });
            //==End of Company RecyclerView

            progressBarGoogle.stopProgress();

        }
    };

//    private Observer<Credits> showCreditDetails = new Observer<Credits>() {
//        @Override
//        public void onChanged(Credits credits) {
//
//            //==Begin of Credit RecyclerView
//            LinearLayoutManager personlayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
//            PersonAdapter personAdapter = new PersonAdapter(getActivity());
//            personAdapter.setPersonList();
//            movieDetails_company_recyclerView.setAdapter(adapter);
//            movieDetails_company_recyclerView.setLayoutManager(layoutManager);
//
//            ItemClickSupport.addTo(movieDetails_company_recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
//                @Override
//                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                    String company = productionCompaniesList.get(position).getName();
//
//                    Toast.makeText(getActivity(), company, Toast.LENGTH_SHORT).show();
//                }
//            });
//            //==End of Credit RecyclerView
//
//        }
//    };

}