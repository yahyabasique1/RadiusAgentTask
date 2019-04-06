package com.isma.dell.radiusagenttask.ResponsePojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(tableName = "exclusion_table")
public class Exclusion {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "facility_id")
    @SerializedName("facility_id")
    @Expose
    private String facilityId;

    @ColumnInfo(name = "options_id")
    @SerializedName("options_id")
    @Expose
    private String optionsId;

    @ColumnInfo(name = "exclusion_facility_id")
    @Expose
    private String exclusionFacilityId;

    @ColumnInfo(name = "exclusion_options_id")
    @Expose
    private String exclusionOptionsId;



    public Exclusion(String facilityId, String optionsId) {
        this.facilityId=facilityId;
        this.optionsId=optionsId;
    }

    public Exclusion() {

    }

    public String getExclusionFacilityId() {
        return exclusionFacilityId;
    }

    public String getExclusionOptionsId() {
        return exclusionOptionsId;
    }

    public void setExclusionFacilityId(String exclusionFacilityId) {
        this.exclusionFacilityId = exclusionFacilityId;
    }

    public void setExclusionOptionsId(String exclusionOptionsId) {
        this.exclusionOptionsId = exclusionOptionsId;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getOptionsId() {
        return optionsId;
    }

    public void setOptionsId(String optionsId) {
        this.optionsId = optionsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
