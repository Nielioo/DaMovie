package com.fei.damovie.repository;

import androidx.lifecycle.MutableLiveData;

import com.fei.damovie.helper.Const;
import com.fei.damovie.model.Credits;
import com.fei.damovie.model.Person;
import com.fei.damovie.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreditRepository {
    private static CreditRepository repository;

    private CreditRepository(){}

    public static CreditRepository getInstance(){
        if(repository==null){
            repository = new CreditRepository();
        }
        return repository;
    }

    public MutableLiveData<Credits> getCreditData(String credit_id){
        final MutableLiveData<Credits> result = new MutableLiveData<>();

        ApiService.endPoint().getCreditById(credit_id, Const.API_KEY).enqueue(new Callback<Credits>() {
            @Override
            public void onResponse(Call<Credits> call, Response<Credits> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Credits> call, Throwable t) {
                result.setValue(null);
            }
        });

        return result;
    }
}
