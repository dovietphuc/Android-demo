package com.example.bai1de1;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    MutableLiveData<Integer> currentPage = new MutableLiveData<>(0);
    MutableLiveData<String> username = new MutableLiveData<>("");
    MutableLiveData<String> password = new MutableLiveData<>("");
}
