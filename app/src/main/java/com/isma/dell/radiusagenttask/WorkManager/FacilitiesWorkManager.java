package com.isma.dell.radiusagenttask.WorkManager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;


import com.isma.dell.radiusagenttask.Network.ApiService;
import com.isma.dell.radiusagenttask.Network.RetrofitClient;
import com.isma.dell.radiusagenttask.ResponsePojo.DataModel;
import com.isma.dell.radiusagenttask.ResponsePojo.Exclusion;
import com.isma.dell.radiusagenttask.ResponsePojo.Facility;
import com.isma.dell.radiusagenttask.ResponsePojo.Option;
import com.isma.dell.radiusagenttask.RoomDb.AppDatabase;
import com.isma.dell.radiusagenttask.RoomDb.FacilitiesTable;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import androidx.work.Data;
import androidx.work.Operation;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FacilitiesWorkManager extends Worker {
    AppDatabase db;
    Context context;
    Result result;

    public FacilitiesWorkManager(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context=context;
        db=AppDatabase.getAppDatabase(context);


    }

    @NonNull
    @Override
    public Result doWork() {

        ApiService apiService= RetrofitClient.getClient().create(ApiService.class);

        apiService.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults,this::handleError);

        return result;
    }

    private void handleError(Throwable throwable) {
        Log.d("Workerror",throwable.getLocalizedMessage());
        result=Result.retry();
    }

    private void handleResults(DataModel dataModel) {
        db.userDao().deleteExclusionTable();
        db.userDao().deleteFacilitiesTable();
        List<List<Exclusion>> exclusionList=new ArrayList<>();
        exclusionList.addAll(dataModel.getExclusions());

        Exclusion exclusion=new Exclusion();
        for(int i=0;i<exclusionList.size();i++){

            exclusion.setFacilityId(exclusionList.get(i).get(0).getFacilityId());
            exclusion.setOptionsId(exclusionList.get(i).get(0).getOptionsId());

            exclusion.setExclusionFacilityId(exclusionList.get(i).get(1).getFacilityId());
            exclusion.setExclusionOptionsId(exclusionList.get(i).get(1).getOptionsId());

            db.userDao().insertAll(exclusion);

        }
        FacilitiesTable facilitiesTable=new FacilitiesTable();

        for (Facility facility:dataModel.getFacilities()){
            facilitiesTable.setName(facility.getName());
            facilitiesTable.setFacilityId(facility.getFacilityId());
            for(Option option:facility.getOptions()){
                facilitiesTable.setOptionIconName(option.getIcon());
                facilitiesTable.setOptionId(option.getId());
                facilitiesTable.setOptionName(option.getName());
                db.userDao().insertFacilities(facilitiesTable);

            }

        }


        result=Result.success();



    }
}
