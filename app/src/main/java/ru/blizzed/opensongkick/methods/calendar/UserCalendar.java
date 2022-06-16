package ru.blizzed.opensongkick.methods.calendar;

import ru.blizzed.opensongkick.ApiCaller;
import ru.blizzed.opensongkick.models.CalendarEntry;
import ru.blizzed.opensongkick.models.ResultsPage;
import ru.blizzed.opensongkick.params.Param;
import ru.blizzed.opensongkick.params.ParamsConverter;
import ru.blizzed.opensongkick.params.ReasonParam;

public class UserCalendar {

    private UserCalendarCaller userCalendarCaller;

    UserCalendar(UserCalendarCaller userCalendarCaller) {
        this.userCalendarCaller = userCalendarCaller;
    }

    public ApiCaller<ResultsPage<CalendarEntry>> byName(String name) {
        return new ApiCaller<>(userCalendarCaller.byName(name));
    }

    public ApiCaller<ResultsPage<CalendarEntry>> byName(String name, Param... params) {
        return new ApiCaller<>(userCalendarCaller.byName(name, ParamsConverter.asMap(params)));
    }

    public ApiCaller<ResultsPage<CalendarEntry>> byNameWithReason(String name, ReasonParam reason) {
        return new ApiCaller<>(userCalendarCaller.byNameWithReason(name, reason.toString()));
    }

    public ApiCaller<ResultsPage<CalendarEntry>> byNameWithReason(String name, ReasonParam reason, Param... params) {
        return new ApiCaller<>(userCalendarCaller.byNameWithReason(name, reason.toString(), ParamsConverter.asMap(params)));
    }

}
