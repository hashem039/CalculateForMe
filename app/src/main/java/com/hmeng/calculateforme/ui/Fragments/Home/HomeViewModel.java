package com.hmeng.calculateforme.ui.Fragments.Home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hmeng.calculateforme.Data.ServiceModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    MutableLiveData<List<ServiceModel>> sericeLiveData;
    List<ServiceModel> serviceArrayList;

    public HomeViewModel() {
        sericeLiveData = new MutableLiveData<>();

        // call your Rest API in init method
        init();
    }

    public MutableLiveData<List<ServiceModel>> getSericeLiveData() {
        return sericeLiveData;
    }

    public void init(){
        populateList();
        sericeLiveData.setValue(serviceArrayList);
    }

    public void populateList(){

        ServiceModel user = new ServiceModel();
        user.setName("Hashem");
        user.setDescription("first service in my app");

        serviceArrayList = new ArrayList<>();
        serviceArrayList.add(user);
        serviceArrayList.add(user);
        serviceArrayList.add(user);
        serviceArrayList.add(user);
        serviceArrayList.add(user);
        serviceArrayList.add(user);
    }
}