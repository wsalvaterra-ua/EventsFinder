package ru.blizzed.opensongkick.methods.search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import ru.blizzed.opensongkick.models.Artist;
import ru.blizzed.opensongkick.models.ResultsPage;

import java.util.Map;

public interface ArtistSearchCaller {

    @GET("search/artists.json")
    Call<ResultsPage<Artist>> byName(@Query("query") String name);

    @GET("search/artists.json")
    Call<ResultsPage<Artist>> byName(@Query("query") String name, @QueryMap Map<String, String> queryMap);

}
