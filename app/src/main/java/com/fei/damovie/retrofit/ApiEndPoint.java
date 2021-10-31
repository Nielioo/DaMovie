package com.fei.damovie.retrofit;

import com.fei.damovie.model.Credits;
import com.fei.damovie.model.Movies;
import com.fei.damovie.model.NowPlaying;
import com.fei.damovie.model.Person;
import com.fei.damovie.model.Popular;
import com.fei.damovie.model.TopRated;
import com.fei.damovie.model.Trending;
import com.fei.damovie.model.Upcoming;
import com.fei.damovie.model.Videos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndPoint {
    @GET("movie/{movie_id}")
    Call<Movies> getMovieById(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey
    );

    @GET("movie/now_playing")
    Call<NowPlaying> getNowPlaying(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("movie/upcoming")
    Call<Upcoming> getUpcoming(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("movie/popular")
    Call<Popular> getPopular(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("movie/top_rated")
    Call<TopRated> getTopRated(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("credit/{credit_id}")
    Call<Credits> getCreditById(
            @Path("credit_id") String credit_id,
            @Query("api_key") String apiKey
    );

    @GET("person/{person_id}")
    Call<Person> getPersonById(
            @Path("person_id") String person_id,
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}/videos")
    Call<Videos> getVideoByMovieId(
            @Path("movie_id") String movie_id,
            @Query("api_key") String apiKey
    );

    @GET("trending/{media_type}/{time_window}")
    Call<Trending> getTrending(
            @Path("media_type") String media_type,
            @Path("time_window") String time_window,
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

}
