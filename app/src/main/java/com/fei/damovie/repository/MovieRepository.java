package com.fei.damovie.repository;

import androidx.lifecycle.MutableLiveData;

import com.fei.damovie.helper.Const;
import com.fei.damovie.model.Movies;
import com.fei.damovie.model.NowPlaying;
import com.fei.damovie.model.Popular;
import com.fei.damovie.model.TopRated;
import com.fei.damovie.model.Trending;
import com.fei.damovie.model.Upcoming;
import com.fei.damovie.model.Videos;
import com.fei.damovie.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static MovieRepository repository;

    private MovieRepository(){}

    public static MovieRepository getInstance(){
        if(repository==null){
            repository = new MovieRepository();
        }
        return repository;
    }

    public MutableLiveData<Movies> getMovieData(String movie_id){
        final MutableLiveData<Movies> result = new MutableLiveData<>();

        ApiService.endPoint().getMovieById(movie_id, Const.API_KEY).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                result.setValue(null);
            }
        });

        return result;
    }

    public MutableLiveData<NowPlaying> getNowPlayingData(int page){
        final MutableLiveData<NowPlaying> result = new MutableLiveData<>();

        ApiService.endPoint().getNowPlaying(Const.API_KEY, page).enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                result.setValue(null);
            }
        });

        return result;
    }

    public MutableLiveData<Upcoming> getUpcomingData(int page){
        final MutableLiveData<Upcoming> result = new MutableLiveData<>();

        ApiService.endPoint().getUpcoming(Const.API_KEY, page).enqueue(new Callback<Upcoming>() {
            @Override
            public void onResponse(Call<Upcoming> call, Response<Upcoming> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Upcoming> call, Throwable t) {
                result.setValue(null);
            }
        });

        return result;
    }

    public MutableLiveData<Popular> getPopularData(int page){
        final MutableLiveData<Popular> result = new MutableLiveData<>();

        ApiService.endPoint().getPopular(Const.API_KEY, page).enqueue(new Callback<Popular>() {
            @Override
            public void onResponse(Call<Popular> call, Response<Popular> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Popular> call, Throwable t) {
                result.setValue(null);
            }
        });

        return result;
    }

    public MutableLiveData<TopRated> getTopRatedData(int page){
        final MutableLiveData<TopRated> result = new MutableLiveData<>();

        ApiService.endPoint().getTopRated(Const.API_KEY, page).enqueue(new Callback<TopRated>() {
            @Override
            public void onResponse(Call<TopRated> call, Response<TopRated> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TopRated> call, Throwable t) {
                result.setValue(null);
            }
        });

        return result;
    }

    public MutableLiveData<Videos> getVideoData(String movie_id){
        final MutableLiveData<Videos> result = new MutableLiveData<>();

        ApiService.endPoint().getVideoByMovieId(movie_id,Const.API_KEY).enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {
                result.setValue(null);
            }
        });

        return result;
    }

    public MutableLiveData<Trending> getTrendingData(String media_type, String time_window, int page){
        final MutableLiveData<Trending> result = new MutableLiveData<>();

        ApiService.endPoint().getTrending(media_type, time_window,Const.API_KEY, page).enqueue(new Callback<Trending>() {
            @Override
            public void onResponse(Call<Trending> call, Response<Trending> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Trending> call, Throwable t) {
                result.setValue(null);
            }
        });

        return result;
    }

}
