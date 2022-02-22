package com.example.project4hacktiv.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String URL = "http://192.168.43.54/project4/";
    //192.168.137.1-172.21.208.1
    //192.168.43.54
    private static Retrofit retrofit;

    public static Retrofit getClient() {

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
