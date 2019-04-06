package com.isma.dell.radiusagenttask.RoomDb;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.isma.dell.radiusagenttask.ResponsePojo.Exclusion;

import java.util.List;

@Dao
public interface ExclusionDao {

    @Query("SELECT * FROM facilities_table where name=:facilityType AND option_id NOT IN (SELECT exclusion_options_id  FROM exclusion_table where options_id=:optionId and facility_id=:facilityId)")
    List<FacilitiesTable> getAllFacilitiestypewithoption(String facilityType, String optionId, String facilityId);


    @Query("DELETE FROM exclusion_table") //Query to delete complete data store stored for exclusion list
    public void deleteExclusionTable();

    @Query("DELETE FROM facilities_table")//Query to delete complete data store stored for facilties list
    public void deleteFacilitiesTable();

    @Insert           //It will insert data in exclusion table in ord
    void insertAll(Exclusion exclusion);

    @Insert
    void insertFacilities(FacilitiesTable facility);
}
