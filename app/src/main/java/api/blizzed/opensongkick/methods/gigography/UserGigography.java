package api.blizzed.opensongkick.methods.gigography;

import api.blizzed.opensongkick.ApiCaller;
import api.blizzed.opensongkick.models.Event;
import api.blizzed.opensongkick.models.ResultsPage;
import api.blizzed.opensongkick.params.Param;
import api.blizzed.opensongkick.params.ParamsConverter;

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
