package com.fei.damovie.repository;

import androidx.lifecycle.MutableLiveData;

import com.fei.damovie.helper.Const;
import com.fei.damovie.model.Movies;
import com.fei.damovie.model.NowPlaying;
import com.fei.damovie.model.Upcoming;
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
}
