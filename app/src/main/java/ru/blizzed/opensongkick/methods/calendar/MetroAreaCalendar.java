package ru.blizzed.opensongkick.methods.calendar;

import ru.blizzed.opensongkick.ApiCaller;
import ru.blizzed.opensongkick.models.Event;
import ru.blizzed.opensongkick.models.ResultsPage;
import ru.blizzed.opensongkick.params.Param;
import ru.blizzed.opensongkick.params.ParamsConverter;

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
