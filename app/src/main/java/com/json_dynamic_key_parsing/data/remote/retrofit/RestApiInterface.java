package com.json_dynamic_key_parsing.data.remote.retrofit;

import com.json_dynamic_key_parsing.data.remote.retrofit.response.FeedResponse;
import com.json_dynamic_key_parsing.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiInterface {
    @GET(Constants.URL.FEED_URL)
    Call<FeedResponse>
    getSiFeed();
}
