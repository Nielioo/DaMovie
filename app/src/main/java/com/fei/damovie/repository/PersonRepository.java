package com.fei.damovie.repository;

import androidx.lifecycle.MutableLiveData;

import com.fei.damovie.helper.Const;
import com.fei.damovie.model.Movies;
import com.fei.damovie.model.Person;
import com.fei.damovie.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonRepository {
    private static PersonRepository repository;

    private PersonRepository(){}

    public static PersonRepository getInstance(){
        if(repository==null){
            repository = new PersonRepository();
        }
        return repository;
    }

    public MutableLiveData<Person> getPersonData(String person_id){
        final MutableLiveData<Person> result = new MutableLiveData<>();

        ApiService.endPoint().getPersonById(person_id, Const.API_KEY).enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                result.setValue(null);
            }
        });

        return result;
    }

}
