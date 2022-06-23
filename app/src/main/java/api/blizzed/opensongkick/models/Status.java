package api.blizzed.opensongkick.models;

import com.google.gson.annotations.SerializedName;

public enum Status {
    @SerializedName("ok")
    OK,

    @SerializedName("error")
    ERROR
}
