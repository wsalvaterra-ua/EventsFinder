package api.blizzed.opensongkick.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Artist extends BaseModel {

    private String onTourUntil;

    @SerializedName("identifier")
    private List<Identifier> identifiers;

    public String getOnTourUntil() {
        return onTourUntil;
    }

    public List<Identifier> getIdentifiers() {
        return identifiers;
    }

    public static class Identifier {
        private String eventsHref;
        private String href;
        private String mbid;
        @SerializedName("setlistsHref")
        private String setListsHref;

        public String getEventsHref() {
            return eventsHref;
        }

        public String getHref() {
            return href;
        }

        public String getMbid() {
            return mbid;
        }

        public String getSetListsHref() {
            return setListsHref;
        }
    }
}
