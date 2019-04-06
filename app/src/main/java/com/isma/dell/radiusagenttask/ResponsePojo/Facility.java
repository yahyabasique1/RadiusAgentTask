package com.isma.dell.radiusagenttask.ResponsePojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "facility_table")
public class Facility {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;


    @ColumnInfo(name = "facility_id")
    @SerializedName("facility_id")
    @Expose
    private String facilityId;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;

    @ColumnInfo(name = "OptionList")
    @SerializedName("options")
    @Expose
    private List<Option> options;

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Facility(String facilityId, String name, List<Option> options) {
        super();
        this.facilityId = facilityId;
        this.name = name;
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
