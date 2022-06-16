package ru.blizzed.opensongkick.methods.search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import ru.blizzed.opensongkick.models.Event;
import ru.blizzed.opensongkick.models.ResultsPage;

import java.util.Map;

public interface EventSearchCaller {

    @GET("events.json")
    Call<ResultsPage<Event>> byArtist(@Query("artist_name") String artistName);

    @GET("events.json")
    Call<ResultsPage<Event>> byArtist(@Query("artist_name") String artistName, @QueryMap Map<String, String> queryMap);

    @GET("events.json")
    Call<ResultsPage<Event>> byLocation(@Query("location") String location);

    @GET("events.json")
    Call<ResultsPage<Event>> byLocation(@Query("location") String location, @QueryMap Map<String, String> queryMap);

}
