package ru.blizzed.opensongkick.methods.calendar;

import ru.blizzed.opensongkick.ApiCaller;
import ru.blizzed.opensongkick.models.Event;
import ru.blizzed.opensongkick.models.ResultsPage;
import ru.blizzed.opensongkick.params.Param;
import ru.blizzed.opensongkick.params.ParamsConverter;

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
