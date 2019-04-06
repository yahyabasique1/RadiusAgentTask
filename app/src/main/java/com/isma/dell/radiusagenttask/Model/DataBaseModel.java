package com.isma.dell.radiusagenttask.Model;

import android.content.Context;

import com.isma.dell.radiusagenttask.Presenter.MainContract;
import com.isma.dell.radiusagenttask.ResponsePojo.DataModel;
import com.isma.dell.radiusagenttask.ResponsePojo.Exclusion;
import com.isma.dell.radiusagenttask.ResponsePojo.Facility;
import com.isma.dell.radiusagenttask.ResponsePojo.Option;
import com.isma.dell.radiusagenttask.RoomDb.AppDatabase;
import com.isma.dell.radiusagenttask.RoomDb.FacilitiesTable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseModel implements MainContract.GetFacilityDataSource {
    MainContract.GetFacilityDataSource getFacilityDataSource;
    AppDatabase db;

    public DataBaseModel(MainContract.MainView mainView){
        db=AppDatabase.getAppDatabase((Context) mainView);

    }

    @Override
    public void getFacilitiesArrayList(List<FacilitiesTable> facilitiesTableList) {

    }

    @Override
    public List<FacilitiesTable> fetchRowFromDb(String facilityType,String facilityId,String optionId) {
        List<FacilitiesTable> facilitiesTableList = new ArrayList<>(db.userDao().getAllFacilitiestypewithoption(facilityType,optionId,facilityId));
        return facilitiesTableList;
    }

    @Override
    public void storeDataInDb(DataModel dataModel) {

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



    }
}
