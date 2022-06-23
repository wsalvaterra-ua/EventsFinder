package api.blizzed.opensongkick.methods.calendar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import api.blizzed.opensongkick.models.Event;
import api.blizzed.opensongkick.models.ResultsPage;

import java.util.Map;

public interface VenueCalendarCaller {

    @GET("venues/{venue_id}/calendar.json")
    Call<ResultsPage<Event>> byId(@Path("venue_id") String id);

    @GET("venues/{venue_id}/calendar.json")
    Call<ResultsPage<Event>> byId(@Path("venue_id") String id, @QueryMap Map<String, String> queries);

}
