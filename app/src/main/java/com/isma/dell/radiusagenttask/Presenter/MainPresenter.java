package com.isma.dell.radiusagenttask.Presenter;

import android.util.Log;

import com.isma.dell.radiusagenttask.Model.DataBaseModel;
import com.isma.dell.radiusagenttask.Model.FacilityModel;
import com.isma.dell.radiusagenttask.ResponsePojo.DataModel;
import com.isma.dell.radiusagenttask.RoomDb.FacilitiesTable;

import java.util.List;

public class MainPresenter implements MainContract.presenter,MainContract.GetFacilityIntractor.OnFinishedListener {

    MainContract.GetFacilityIntractor getFacilityIntractor;
    MainContract.MainView mainView;
    MainContract.GetFacilityDataSource getFacilityDataSource;

    public MainPresenter(MainContract.MainView mainView){
        this.getFacilityIntractor=new FacilityModel(mainView);
        this.mainView=mainView;
        this.getFacilityDataSource=new DataBaseModel(mainView);
    }




    @Override
    public void onDestroy() {
       mainView=null;
    }

    @Override
    public void requestDataFromServer() {
        getFacilityIntractor.getNoticeArrayList( this);

    }

    @Override
    public void requestDataFromDb(String faciltyType,String facilityId,String optionId) {

       List<FacilitiesTable> list= getFacilityDataSource.fetchRowFromDb(faciltyType,facilityId,optionId);
       Log.d("ARREY",list.size()+" ");
       mainView.setDataToRecyclerView(list);

    }







    @Override
    public void onFinished(DataModel dataModel) {
        getFacilityDataSource.storeDataInDb(dataModel);
    }

    @Override
    public void onFailure(Throwable t) {
        mainView.onResponseFailure(t);
    }



}
