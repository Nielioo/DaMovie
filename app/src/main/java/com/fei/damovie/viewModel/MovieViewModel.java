package com.fei.damovie.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fei.damovie.model.Movies;
import com.fei.damovie.model.NowPlaying;
import com.fei.damovie.model.Popular;
import com.fei.damovie.model.TopRated;
import com.fei.damovie.model.Trending;
import com.fei.damovie.model.Upcoming;
import com.fei.damovie.model.Videos;
import com.fei.damovie.repository.MovieRepository;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = MovieRepository.getInstance();
    }

    //==Begin of viewModel getMovieById

    private MutableLiveData<Movies> resultGetMovieById = new MutableLiveData<>();
    public void setResultGetMovieById(String movie_id){
        resultGetMovieById =  repository.getMovieData(movie_id);
    }
    public LiveData<Movies> getResultGetMovieById(){
        return resultGetMovieById;
    }

    //==End of viewModel getMovieById

    //==Begin of viewModel getNowPlaying

    private MutableLiveData<NowPlaying> resultGetNowPlaying = new MutableLiveData<>();
    public void setResultGetNowPlaying(int page){
        resultGetNowPlaying = repository.getNowPlayingData(page);
    }
    public LiveData<NowPlaying> getResultGetNowPlaying(){
        return resultGetNowPlaying;
    }

    //==End of viewModel getNowPlaying

    //==Begin of viewModel getUpcoming

    private MutableLiveData<Upcoming> resultGetUpcoming = new MutableLiveData<>();
    public void setResultGetUpcoming(int page){
        resultGetUpcoming = repository.getUpcomingData(page);
    }
    public LiveData<Upcoming> getResultGetUpcoming(){
        return resultGetUpcoming;
    }

    //==End of viewModel getUpcoming

    //==Begin of viewModel getPopular

    private MutableLiveData<Popular> resultGetPopular = new MutableLiveData<>();
    public void setResultGetPopular(int page){
        resultGetPopular = repository.getPopularData(page);
    }
    public LiveData<Popular> getResultGetPopular(){
        return resultGetPopular;
    }

    //==End of viewModel getPopular

    //==Begin of viewModel getTopRated

    private MutableLiveData<TopRated> resultGetTopRated = new MutableLiveData<>();
    public void setResultGetTopRated(int page){
        resultGetTopRated = repository.getTopRatedData(page);
    }
    public LiveData<TopRated> getResultGetTopRated(){
        return resultGetTopRated;
    }

    //==End of viewModel getTopRated

    //==Begin of viewModel getVideoByMovieId

    private MutableLiveData<Videos> resultGetVideoByMovieId = new MutableLiveData<>();
    public void setResultGetVideoByMovieId(String movie_id){
        resultGetVideoByMovieId =  repository.getVideoData(movie_id);
    }
    public LiveData<Videos> getResultGetVideoByMovieId(){
        return resultGetVideoByMovieId;
    }

    //==End of viewModel getVideoByMovieId

    //==Begin of viewModel getTrending

    private MutableLiveData<Trending> resultGetTrending = new MutableLiveData<>();
    public void setResultGetTrending(String media_type, String time_window, int page){
        resultGetTrending = repository.getTrendingData(media_type,time_window,page);
    }
    public LiveData<Trending> getResultGetTrending(){
        return resultGetTrending;
    }

    //==End of viewModel getTrending

}
