package com.json_dynamic_key_parsing.data.remote.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.json_dynamic_key_parsing.utils.Constants.Retrofit.RetrofitBaseUrl;

public class RestClient {
    private static RestApiInterface restApi;

    public RestClient() {
        restApi = setupRestClient();
    }

    public RestApiInterface get() {
        return restApi;
    }

    private RestApiInterface setupRestClient() {
        final Gson gson = new GsonBuilder()
                .setLenient()
                .setPrettyPrinting().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(new OkHttpClient().newBuilder().build())
                .build();
        return retrofit.create(RestApiInterface.class);
    }
}