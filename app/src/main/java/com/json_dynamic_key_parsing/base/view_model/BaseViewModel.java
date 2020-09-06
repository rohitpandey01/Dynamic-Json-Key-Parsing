package com.json_dynamic_key_parsing.base.view_model;

import androidx.lifecycle.ViewModel;

import com.json_dynamic_key_parsing.data.repositiory.JsonRepo;

public class BaseViewModel extends ViewModel {
    protected JsonRepo jsonRepo;

    public BaseViewModel() {
        jsonRepo = JsonRepo.getInstance();
    }
}
