package com.fei.damovie.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fei.damovie.model.Credits;
import com.fei.damovie.model.Person;
import com.fei.damovie.repository.CreditRepository;
import com.fei.damovie.repository.PersonRepository;

public class CreditViewModel extends AndroidViewModel {

    CreditRepository repository;

    public CreditViewModel(@NonNull Application application) {
        super(application);
        repository = CreditRepository.getInstance();
    }

    //==Begin of viewModel getCreditById

    private MutableLiveData<Credits> resultGetCreditById = new MutableLiveData<>();
    public void setResultGetCreditById(String credit_id){
        resultGetCreditById =  repository.getCreditData(credit_id);
    }
    public LiveData<Credits> getResultGetCreditById(){
        return resultGetCreditById;
    }

    //==End of viewModel getCreditById
}
