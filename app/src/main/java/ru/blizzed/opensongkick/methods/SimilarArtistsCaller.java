package ru.blizzed.opensongkick.methods;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.blizzed.opensongkick.models.Artist;
import ru.blizzed.opensongkick.models.ResultsPage;

public interface SimilarArtistsCaller {

    @GET("artists/{artist_id}/similar_artists.json")
    Call<ResultsPage<Artist>> byId(@Path("artist_id") String artistId);

}
