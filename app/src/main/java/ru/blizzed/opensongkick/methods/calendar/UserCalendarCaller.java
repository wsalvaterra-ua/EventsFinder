package ru.blizzed.opensongkick.methods.calendar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import ru.blizzed.opensongkick.models.CalendarEntry;
import ru.blizzed.opensongkick.models.ResultsPage;

import java.util.Map;

public interface UserCalendarCaller {

    @GET("users/{username}/calendar.json")
    Call<ResultsPage<CalendarEntry>> byNameWithReason(@Path("username") String username, @Query("reason") String reason);

    @GET("users/{username}/calendar.json")
    Call<ResultsPage<CalendarEntry>> byNameWithReason(@Path("username") String username, @Query("reason") String reason, @QueryMap Map<String, String> queries);

    @GET("users/{username}/calendar.json")
    Call<ResultsPage<CalendarEntry>> byName(@Path("username") String username);

    @GET("users/{username}/calendar.json")
    Call<ResultsPage<CalendarEntry>> byName(@Path("username") String username, @QueryMap Map<String, String> queries);

}
