package com.nguyenthai.qlcv.ui.bsc;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BSCViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BSCViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}