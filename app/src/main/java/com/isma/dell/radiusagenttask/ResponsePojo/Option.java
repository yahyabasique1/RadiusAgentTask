package com.isma.dell.radiusagenttask.ResponsePojo;
import java.io.Serializable;
import java.util.Observable;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Option {
    @ColumnInfo(name = "option_name")
    @SerializedName("name")
    @Expose
    private String name;

    @ColumnInfo(name = "icon")
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("id")
    @Expose
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
