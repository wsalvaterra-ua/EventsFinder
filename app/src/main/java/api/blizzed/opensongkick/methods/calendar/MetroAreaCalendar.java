package api.blizzed.opensongkick.methods.calendar;

import api.blizzed.opensongkick.ApiCaller;
import api.blizzed.opensongkick.models.Event;
import api.blizzed.opensongkick.models.ResultsPage;
import api.blizzed.opensongkick.params.Param;
import api.blizzed.opensongkick.params.ParamsConverter;

public class MetroAreaCalendar {

    private MetroAreaCalendarCaller metroAreaCalendarCaller;

    public MetroAreaCalendar(MetroAreaCalendarCaller metroAreaCalendarCaller) {
        this.metroAreaCalendarCaller = metroAreaCalendarCaller;
    }

    public ApiCaller<ResultsPage<Event>> byId(String id) {
        return new ApiCaller<>(metroAreaCalendarCaller.byId(id));
    }

    public ApiCaller<ResultsPage<Event>> byId(String id, Param... params) {
        return new ApiCaller<>(metroAreaCalendarCaller.byId(id, ParamsConverter.asMap(params)));
    }

}
