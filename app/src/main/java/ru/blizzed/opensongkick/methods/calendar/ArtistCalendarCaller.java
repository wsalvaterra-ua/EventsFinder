package ru.blizzed.opensongkick.methods.calendar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import ru.blizzed.opensongkick.models.Event;
import ru.blizzed.opensongkick.models.ResultsPage;

import java.util.Map;

public interface ArtistCalendarCaller {

    @GET("artists/{artist_id}/calendar.json")
    Call<ResultsPage<Event>> byId(@Path("artist_id") String id);

    @GET("artists/{artist_id}/calendar.json")
    Call<ResultsPage<Event>> byId(@Path("artist_id") String id, @QueryMap Map<String, String> queries);

    @GET("artists/mbid:{mbid}/calendar.json")
    Call<ResultsPage<Event>> byMbid(@Query("mbid") String mbid);

    @GET("artists/mbid:{mbid}/calendar.json")
    Call<ResultsPage<Event>> byMbid(@Query("mbid") String mbid, @QueryMap Map<String, String> queries);

}
