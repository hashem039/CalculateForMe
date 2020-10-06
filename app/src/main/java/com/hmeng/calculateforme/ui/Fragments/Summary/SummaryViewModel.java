package com.hmeng.calculateforme.ui.Fragments.Summary;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.hmeng.calculateforme.Data.BalconyRadiusOperationTask;
import com.hmeng.calculateforme.Repositories.BalconyRadiusRepository;

import java.util.List;

public class SummaryViewModel extends AndroidViewModel {

    MutableLiveData<List<BalconyRadiusOperationTask>> prefBalconyRadiusTasksLiveData;

    BalconyRadiusRepository repository;

    public MutableLiveData<List<BalconyRadiusOperationTask>> getPrefBalconyRadiusTasksLiveData() {
        return prefBalconyRadiusTasksLiveData;
    }

    public SummaryViewModel(@NonNull Application application) {
        super(application);
        // call your Rest API in init method
        init();
    }


    public void init(){
        if (prefBalconyRadiusTasksLiveData !=null) {
            return;
        }
        repository = BalconyRadiusRepository.getInstance();
        prefBalconyRadiusTasksLiveData = repository.getBalconyRadiusPrevTasks(getApplication());
    }
}