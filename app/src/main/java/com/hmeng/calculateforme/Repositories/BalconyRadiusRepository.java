package com.hmeng.calculateforme.Repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hmeng.calculateforme.Data.BalconyRadiusOperationTask;
import com.hmeng.calculateforme.Data.SharedPreferencesManager;
import com.hmeng.calculateforme.ui.Activities.BalconyRadius;

import java.lang.reflect.Type;
import java.util.List;

public class BalconyRadiusRepository {

    private static BalconyRadiusRepository instance;
    private List<BalconyRadiusOperationTask> mData;

    public static BalconyRadiusRepository getInstance() {
        if (instance == null) {
            instance = new BalconyRadiusRepository();
        }
        return instance;
    }
    public MutableLiveData<List<BalconyRadiusOperationTask>> getBalconyRadiusPrevTasks(Context context) {
      setBalconyRadiusPrevTasksData(context);
      MutableLiveData<List<BalconyRadiusOperationTask>> data = new MutableLiveData<>();
      data.setValue(mData);
      return data;
    }
    public void setBalconyRadiusPrevTasksData(Context context) {
        Gson gson = new Gson();
        String json = SharedPreferencesManager.readFromPreferences(context, "root_preferences",SharedPreferencesManager.BALCONY_RADIUS_SERVICE_DATA,null);
        Type type = new TypeToken<List<BalconyRadiusOperationTask>>() {}.getType();
        mData = gson.fromJson(json, type);
    }
}
