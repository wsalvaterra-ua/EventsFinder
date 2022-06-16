package ru.blizzed.opensongkick.methods.gigography;

import ru.blizzed.opensongkick.ApiCaller;
import ru.blizzed.opensongkick.models.Event;
import ru.blizzed.opensongkick.models.ResultsPage;
import ru.blizzed.opensongkick.params.Param;
import ru.blizzed.opensongkick.params.ParamsConverter;

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
