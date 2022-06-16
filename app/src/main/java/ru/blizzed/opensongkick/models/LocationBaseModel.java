package ru.blizzed.opensongkick.models;

import com.google.gson.annotations.SerializedName;

public class LocationBaseModel extends BaseModel {

    @SerializedName("lng")
    private double longitude;

    @SerializedName("lat")
    private double latitude;

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
