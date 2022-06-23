package api.blizzed.opensongkick.methods.gigography;

import api.blizzed.opensongkick.ApiCaller;
import api.blizzed.opensongkick.models.Event;
import api.blizzed.opensongkick.models.ResultsPage;
import api.blizzed.opensongkick.params.Param;
import api.blizzed.opensongkick.params.ParamsConverter;

public class ArtistGigography {

    private ArtistGigographyCaller artistGigographyCaller;

    public ArtistGigography(ArtistGigographyCaller artistGigographyCaller) {
        this.artistGigographyCaller = artistGigographyCaller;
    }

    public ApiCaller<ResultsPage<Event>> byId(String id) {
        return new ApiCaller<>(artistGigographyCaller.byId(id));
    }

    public ApiCaller<ResultsPage<Event>> byId(String id, Param... params) {
        return new ApiCaller<>(artistGigographyCaller.byId(id, ParamsConverter.asMap(params)));
    }

    public ApiCaller<ResultsPage<Event>> byMbid(String mbid) {
        return new ApiCaller<>(artistGigographyCaller.byMbid(mbid));
    }

    public ApiCaller<ResultsPage<Event>> byMbid(String mbid, Param... params) {
        return new ApiCaller<>(artistGigographyCaller.byMbid(mbid, ParamsConverter.asMap(params)));
    }

}
