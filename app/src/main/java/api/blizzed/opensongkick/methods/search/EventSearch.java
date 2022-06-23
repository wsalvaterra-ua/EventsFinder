package api.blizzed.opensongkick.methods.search;

import api.blizzed.opensongkick.ApiCaller;
import api.blizzed.opensongkick.models.Event;
import api.blizzed.opensongkick.models.ResultsPage;
import api.blizzed.opensongkick.params.Param;
import api.blizzed.opensongkick.params.ParamsConverter;

public class EventSearch {

    private EventSearchCaller eventSearchCaller;

    public EventSearch(EventSearchCaller eventSearchCaller) {
        this.eventSearchCaller = eventSearchCaller;
    }

    public ApiCaller<ResultsPage<Event>> byArtist(String artistName) {
        return new ApiCaller<>(eventSearchCaller.byArtist(artistName));
    }

    public ApiCaller<ResultsPage<Event>> byArtist(String artistName, Param... params) {
        return new ApiCaller<>(eventSearchCaller.byArtist(artistName, ParamsConverter.asMap(params)));
    }

    public ApiCaller<ResultsPage<Event>> byLocation(Param location) {
        return new ApiCaller<>(eventSearchCaller.byLocation(location.toString()));
    }

    public ApiCaller<ResultsPage<Event>> byLocation(Param location, Param... params) {
        return new ApiCaller<>(eventSearchCaller.byLocation(location.toString(), ParamsConverter.asMap(params)));
    }

}
