package api.blizzed.opensongkick.methods.calendar;

import api.blizzed.opensongkick.ApiCaller;
import api.blizzed.opensongkick.models.CalendarEntry;
import api.blizzed.opensongkick.models.ResultsPage;
import api.blizzed.opensongkick.params.Param;
import api.blizzed.opensongkick.params.ParamsConverter;
import api.blizzed.opensongkick.params.ReasonParam;

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
