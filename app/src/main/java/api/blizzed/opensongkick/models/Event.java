package api.blizzed.opensongkick.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Event extends BaseModel {

    public enum Type {
        @SerializedName("Concert")
        CONCERT,

        @SerializedName("Festival")
        FESTIVAL
    }

    private Type type;
    private Start start;
    private End end;
    @SerializedName("performance")
    private List<Performance> performances;
    private SimpleLocation location;
    private BaseVenue venue;
    private Status status;
    private double popularity;

    public Type getType() {
        return type;
    }

    public Start getStart() {
        return start;
    }

    public End getEnd() {
        return end;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public SimpleLocation getLocation() {
        return location;
    }

    public BaseVenue getVenue() {
        return venue;
    }

    public Status getStatus() {
        return status;
    }

    public double getPopularity() {
        return popularity;
    }

    public static class Start {
        private String time;
        private String date;
        @SerializedName("datetime")
        private String dateTime;

        public String getTime() {
            return time;
        }

        public String getDate() {
            return date;
        }

        public String getDateTime() {
            return dateTime;
        }
    }
    public static class End {
        private String time;
        private String date;
        @SerializedName("datetime")
        private String dateTime;

        public String getTime() {
            return time;
        }

        public String getDate() {
            return date;
        }

        public String getDateTime() {
            return dateTime;
        }
    }

    public static class Performance extends BaseModel {
        private Artist artist;
        private int billingIndex;
        private String billing;

        public Artist getArtist() {
            return artist;
        }

        public int getBillingIndex() {
            return billingIndex;
        }

        public String getBilling() {
            return billing;
        }
    }

    public static class SimpleLocation extends LocationBaseModel {
        private String city;

        public String getCity() {
            return city;
        }

    }


}
