package api.blizzed.opensongkick.methods.search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import api.blizzed.opensongkick.models.Location;
import api.blizzed.opensongkick.models.ResultsPage;

import java.util.Map;

public interface LocationSearchCaller {

    @GET("search/locations.json")
    Call<ResultsPage<Location>> byQuery(@Query("query") String query);

    @GET("search/locations.json")
    Call<ResultsPage<Location>> byQuery(@Query("query") String query, @QueryMap Map<String, String> queryMap);

    @GET("search/locations.json")
    Call<ResultsPage<Location>> byLocation(@Query("location") String query);

    @GET("search/locations.json")
    Call<ResultsPage<Location>> byLocation(@Query("location") String query, @QueryMap Map<String, String> queryMap);

}
