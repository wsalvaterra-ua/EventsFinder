package api.blizzed.opensongkick.methods;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import api.blizzed.opensongkick.models.Result;
import api.blizzed.opensongkick.models.Venue;

public interface VenueDetailsCaller {

    @GET("venues/{venue_id}.json")
    Call<Result<Venue>> byId(@Path("venue_id") String venueId);

}
