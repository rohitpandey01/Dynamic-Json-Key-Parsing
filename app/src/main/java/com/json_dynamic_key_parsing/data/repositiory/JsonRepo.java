package com.json_dynamic_key_parsing.data.repositiory;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.json_dynamic_key_parsing.data.remote.retrofit.response.FeedResponse;
import com.json_dynamic_key_parsing.data.remote.retrofit.ApiResponse;
import com.json_dynamic_key_parsing.data.remote.retrofit.RestApiInterface;
import com.json_dynamic_key_parsing.data.remote.retrofit.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JsonRepo {
    private final RestApiInterface mRestApiInterface;

    private static JsonRepo mInstance;
    private static final Object LOCK = new Object();

    public JsonRepo() {
        this.mRestApiInterface = new RestClient().get();
    }

    public synchronized static JsonRepo getInstance() {
        if (mInstance == null) {
            synchronized (LOCK) {
                mInstance = new JsonRepo();
            }
        }
        return mInstance;
    }

    public LiveData<ApiResponse<FeedResponse>> getSiFeeds() {
        final MutableLiveData<ApiResponse<FeedResponse>> liveData = new MutableLiveData<>();
        ApiResponse<FeedResponse> apiResponse = new ApiResponse<>();
        apiResponse.currentState = ApiResponse.State.LOADING;  //initially send the loading state with no data and error message
        liveData.setValue(apiResponse);
        mRestApiInterface.getSiFeed().enqueue(new CustomCallback<>(liveData));
        return liveData;
    }

    static class CustomCallback<T> implements Callback<T> {

        MutableLiveData<ApiResponse<T>> liveData;

        CustomCallback(MutableLiveData<ApiResponse<T>> liveData) {
            this.liveData = liveData;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.body() != null) {
                Log.d("ON REPSONSE ", response.body().toString());
            }
            ApiResponse<T> apiResponse = new ApiResponse<>();

            if (response.code() == 500) {
                //apiResponse.data = null;
                apiResponse.currentState = ApiResponse.State.ERROR;
                liveData.setValue(apiResponse);
            } else if (response.isSuccessful()) {
                //API successful!
                apiResponse.data = response.body();
                apiResponse.currentState = ApiResponse.State.SUCCESS;
                liveData.setValue(apiResponse);
            } else {
                //API hit success but throwed some error.
                apiResponse.data = response.body();
                apiResponse.currentState = ApiResponse.State.ERROR;
                liveData.setValue(apiResponse);
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            //API response failed!
            t.printStackTrace();
            ApiResponse<T> apiResponse = new ApiResponse<>();
            apiResponse.throwable = t;
            apiResponse.currentState = ApiResponse.State.FAILURE;
            liveData.setValue(apiResponse);
        }
    }
}