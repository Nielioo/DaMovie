package com.fei.damovie.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fei.damovie.model.Movies;
import com.fei.damovie.model.Person;
import com.fei.damovie.repository.PersonRepository;

public class PersonViewModel extends AndroidViewModel {

    PersonRepository repository;

    public PersonViewModel(@NonNull Application application) {
        super(application);
        repository = PersonRepository.getInstance();
    }

    //==Begin of viewModel getPersonById

    private MutableLiveData<Person> resultGetPersonById = new MutableLiveData<>();
    public void setResultGetPersonById(String person_id){
        resultGetPersonById =  repository.getPersonData(person_id);
    }
    public LiveData<Person> getResultGetPersonById(){
        return resultGetPersonById;
    }

    //==End of viewModel getPersonById

}
