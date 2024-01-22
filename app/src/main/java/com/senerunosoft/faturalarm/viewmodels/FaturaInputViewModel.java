package com.senerunosoft.faturalarm.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.senerunosoft.faturalarm.models.FaturaInput;

public class FaturaInputViewModel extends ViewModel {

    private MutableLiveData<FaturaInput> faturaInput = new MutableLiveData<>();

    FaturaInputViewModel() {
    }

    public MutableLiveData<FaturaInput> getFaturaInput() {
        return faturaInput;
    }

    public void setFaturaInput(FaturaInput faturaInput) {
        this.faturaInput.setValue(faturaInput);
    }

}
