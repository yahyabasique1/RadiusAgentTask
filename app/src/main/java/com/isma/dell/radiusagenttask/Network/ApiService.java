package com.isma.dell.radiusagenttask.Network;



import com.isma.dell.radiusagenttask.ResponsePojo.DataModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/iranjith4/ad-assignment/db")
    Observable<DataModel> getData();
}