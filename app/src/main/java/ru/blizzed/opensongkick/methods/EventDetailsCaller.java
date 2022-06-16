package ru.blizzed.opensongkick.methods;

import retrofit2.Call;
import retrofit2.http.*;
import ru.blizzed.opensongkick.models.Event;
import ru.blizzed.opensongkick.models.Result;

public interface EventDetailsCaller {

    @GET("events/{event_id}.json")
    Call<Result<Event>> byId(@Path("event_id") String eventId);

}
