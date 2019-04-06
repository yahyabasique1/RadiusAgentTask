package com.isma.dell.radiusagenttask.RoomDb;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "facilities_table")
public class FacilitiesTable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;


    @ColumnInfo(name = "facility_id")
    private String facilityId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "option_name")
    private String optionName;

    @ColumnInfo(name = "option_icon")
    private String optionIconName;

    @ColumnInfo(name = "option_id")
    private String optionId;

    public FacilitiesTable() {

    }


    public int getId() {
        return id;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public String getName() {
        return name;
    }

    public String getOptionIconName() {
        return optionIconName;
    }

    public String getOptionId() {
        return optionId;
    }

    public String getOptionName() {
        return optionName;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOptionIconName(String optionIconName) {
        this.optionIconName = optionIconName;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public FacilitiesTable(String facilityId,String name,String optionName,String optionIconName,String optionId){
        this.facilityId=facilityId;
        this.name=name;
        this.optionName=optionName;
        this.optionIconName=optionIconName;
        this.optionId=optionId;

    }
}
