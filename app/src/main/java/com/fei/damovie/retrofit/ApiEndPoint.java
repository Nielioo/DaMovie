package com.fei.damovie.retrofit;

import com.fei.damovie.model.Credits;
import com.fei.damovie.model.Movies;
import com.fei.damovie.model.NowPlaying;
import com.fei.damovie.model.Person;
import com.fei.damovie.model.Upcoming;

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

}
