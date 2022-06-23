package api.blizzed.opensongkick.methods.gigography;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import api.blizzed.opensongkick.models.Event;
import api.blizzed.opensongkick.models.ResultsPage;

import java.util.Map;

public interface UserGigographyCaller {

    @GET("users/{username}/gigography.json")
    Call<ResultsPage<Event>> byName(@Path("username") String username);

    @GET("users/{username}/gigography.json")
    Call<ResultsPage<Event>> byName(@Path("username") String username, @QueryMap Map<String, String> queries);

}
