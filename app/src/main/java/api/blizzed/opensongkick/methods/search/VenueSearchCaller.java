package api.blizzed.opensongkick.methods.search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import api.blizzed.opensongkick.models.ResultsPage;
import api.blizzed.opensongkick.models.Venue;

import java.util.Map;

public interface VenueSearchCaller {

    @GET("search/venues.json")
    Call<ResultsPage<Venue>> byQuery(@Query("query") String query);

    @GET("search/venues.json")
    Call<ResultsPage<Venue>> byQuery(@Query("query") String query, @QueryMap Map<String, String> queryMap);

}
