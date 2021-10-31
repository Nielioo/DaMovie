package com.fei.damovie.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fei.damovie.model.Movies;
import com.fei.damovie.model.NowPlaying;
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

    //==Begin of viewModel getVideoByMovieId

    private MutableLiveData<Videos> resultGetVideoByMovieId = new MutableLiveData<>();
    public void setResultGetVideoByMovieId(String movie_id){
        resultGetVideoByMovieId =  repository.getVideoData(movie_id);
    }
    public LiveData<Videos> getResultGetVideoByMovieId(){
        return resultGetVideoByMovieId;
    }

    //==End of viewModel getVideoByMovieId

}
