package api.blizzed.opensongkick.methods.gigography;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import api.blizzed.opensongkick.models.Event;
import api.blizzed.opensongkick.models.ResultsPage;

import java.util.Map;

public interface ArtistGigographyCaller {

    @GET("artists/{artist_id}/gigography.json")
    Call<ResultsPage<Event>> byId(@Path("artist_id") String id);

    @GET("artists/{artist_id}/gigography.json")
    Call<ResultsPage<Event>> byId(@Path("artist_id") String id, @QueryMap Map<String, String> queries);

    @GET("artists/mbid:{mbid}/gigography.json")
    Call<ResultsPage<Event>> byMbid(@Path("mbid") String mbid);

    @GET("artists/mbid:{mbid}/gigography.json")
    Call<ResultsPage<Event>> byMbid(@Path("mbid") String mbid, @QueryMap Map<String, String> queries);

}
