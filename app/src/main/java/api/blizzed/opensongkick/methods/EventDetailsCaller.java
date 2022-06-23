package api.blizzed.opensongkick.methods;

import retrofit2.Call;
import retrofit2.http.*;
import api.blizzed.opensongkick.models.Event;
import api.blizzed.opensongkick.models.Result;

public interface EventDetailsCaller {

    @GET("events/{event_id}.json")
    Call<Result<Event>> byId(@Path("event_id") String eventId);

}
