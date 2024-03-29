package api.blizzed.opensongkick.methods.calendar;

import api.blizzed.opensongkick.ApiCaller;
import api.blizzed.opensongkick.models.Event;
import api.blizzed.opensongkick.models.ResultsPage;
import api.blizzed.opensongkick.params.Param;
import api.blizzed.opensongkick.params.ParamsConverter;

public class VenueCalendar {

    private VenueCalendarCaller venueCalendarCaller;

    public VenueCalendar(VenueCalendarCaller venueCalendarCaller) {
        this.venueCalendarCaller = venueCalendarCaller;
    }

    public ApiCaller<ResultsPage<Event>> byId(String id) {
        return new ApiCaller<>(venueCalendarCaller.byId(id));
    }

    public ApiCaller<ResultsPage<Event>> byId(String id, Param... params) {
        return new ApiCaller<>(venueCalendarCaller.byId(id, ParamsConverter.asMap(params)));
    }

}
