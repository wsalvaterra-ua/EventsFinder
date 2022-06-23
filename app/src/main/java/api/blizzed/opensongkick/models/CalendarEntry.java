package api.blizzed.opensongkick.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CalendarEntry {

    public enum Attendance {
        @SerializedName("all")
        ALL,

        @SerializedName("i_might_go")
        I_MIGHT_GO,

        @SerializedName("im_going")
        IM_GOING
    }

    private Event event;
    private Reason reason;

    public Event getEvent() {
        return event;
    }

    public Reason getReason() {
        return reason;
    }

    public static class Reason {
        @SerializedName("trackedArtist")
        private List<Artist> trackedArtists;

        public List<Artist> getTrackedArtists() {
            return trackedArtists;
        }
    }

}
