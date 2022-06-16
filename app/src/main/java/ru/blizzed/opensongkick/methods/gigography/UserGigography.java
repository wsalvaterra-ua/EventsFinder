package ru.blizzed.opensongkick.methods.gigography;

import ru.blizzed.opensongkick.ApiCaller;
import ru.blizzed.opensongkick.models.Event;
import ru.blizzed.opensongkick.models.ResultsPage;
import ru.blizzed.opensongkick.params.Param;
import ru.blizzed.opensongkick.params.ParamsConverter;

public class UserGigography {

    private UserGigographyCaller userGigographyCaller;

    public UserGigography(UserGigographyCaller userGigographyCaller) {
        this.userGigographyCaller = userGigographyCaller;
    }

    public ApiCaller<ResultsPage<Event>> byName(String username) {
        return new ApiCaller<>(userGigographyCaller.byName(username));
    }

    public ApiCaller<ResultsPage<Event>> byName(String username, Param... params) {
        return new ApiCaller<>(userGigographyCaller.byName(username, ParamsConverter.asMap(params)));
    }

}
