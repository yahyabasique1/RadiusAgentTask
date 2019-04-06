package com.isma.dell.radiusagenttask.Model;

import android.content.Context;


import com.isma.dell.radiusagenttask.Presenter.MainContract;
import com.isma.dell.radiusagenttask.Network.ApiService;
import com.isma.dell.radiusagenttask.Network.RetrofitClient;
import com.isma.dell.radiusagenttask.ResponsePojo.DataModel;
import com.isma.dell.radiusagenttask.RoomDb.AppDatabase;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FacilityModel implements MainContract.GetFacilityIntractor {
    MainContract.GetFacilityIntractor.OnFinishedListener onFinishedListener;
    AppDatabase db;


    public FacilityModel(MainContract.MainView getFacilityIntractor) {
        db=AppDatabase.getAppDatabase((Context) getFacilityIntractor);
    }



    @Override
    public void getNoticeArrayList(OnFinishedListener onFinishedListener) {
        this.onFinishedListener=onFinishedListener;

        ApiService apiService= RetrofitClient.getClient().create(ApiService.class);
        apiService.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults,this::handleError);
    }

    private void handleResults(DataModel dataModel) {
        onFinishedListener.onFinished(dataModel);

    }




    private void handleError(Throwable throwable) {
        onFinishedListener.onFailure(throwable);
    }


}
