package com.json_dynamic_key_parsing.json_feed.view_model;

import androidx.lifecycle.LiveData;

import com.json_dynamic_key_parsing.base.view_model.BaseViewModel;
import com.json_dynamic_key_parsing.data.remote.retrofit.ApiResponse;
import com.json_dynamic_key_parsing.data.remote.retrofit.response.FeedResponse;

public class JsonFeedViewModel extends BaseViewModel {
    public LiveData<ApiResponse<FeedResponse>> getJsonFeed() {
        LiveData<ApiResponse<FeedResponse>> apiResponseLiveData;
        return apiResponseLiveData = jsonRepo.getSiFeeds();
    }
}
