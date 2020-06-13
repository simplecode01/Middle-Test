package com.simplecode01.middletest.networking;

import com.simplecode01.middletest.Function.Constraint;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constraint.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    public static ApiServices getInstanceRetrofit() {
        return getRetrofit().create(ApiServices.class);
    }
}


