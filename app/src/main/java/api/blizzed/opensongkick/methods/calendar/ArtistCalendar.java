package api.blizzed.opensongkick.methods.calendar;

import api.blizzed.opensongkick.ApiCaller;
import api.blizzed.opensongkick.models.Event;
import api.blizzed.opensongkick.models.ResultsPage;
import api.blizzed.opensongkick.params.Param;
import api.blizzed.opensongkick.params.ParamsConverter;

public class ArtistCalendar {

    private ArtistCalendarCaller artistCalendarCaller;

    public ArtistCalendar(ArtistCalendarCaller artistCalendarCaller) {
        this.artistCalendarCaller = artistCalendarCaller;
    }

    public ApiCaller<ResultsPage<Event>> byId(String id) {
        return new ApiCaller<>(artistCalendarCaller.byId(id));
    }

    public ApiCaller<ResultsPage<Event>> byId(String id, Param... params) {
        return new ApiCaller<>(artistCalendarCaller.byId(id, ParamsConverter.asMap(params)));
    }

    public ApiCaller<ResultsPage<Event>> byMbid(String mbid) {
        return new ApiCaller<>(artistCalendarCaller.byMbid(mbid));
    }

    public ApiCaller<ResultsPage<Event>> byMbid(String mbid, Param... params) {
        return new ApiCaller<>(artistCalendarCaller.byMbid(mbid, ParamsConverter.asMap(params)));
    }

}
