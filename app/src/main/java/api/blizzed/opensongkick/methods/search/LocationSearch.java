package api.blizzed.opensongkick.methods.search;

import api.blizzed.opensongkick.ApiCaller;
import api.blizzed.opensongkick.models.Location;
import api.blizzed.opensongkick.models.ResultsPage;
import api.blizzed.opensongkick.params.LocationParam;
import api.blizzed.opensongkick.params.Param;
import api.blizzed.opensongkick.params.ParamsConverter;

public class LocationSearch {

    private LocationSearchCaller locationSearchCaller;

    public LocationSearch(LocationSearchCaller locationSearchCaller) {
        this.locationSearchCaller = locationSearchCaller;
    }

    public ApiCaller<ResultsPage<Location>> byQuery(String name) {
        return new ApiCaller<>(locationSearchCaller.byQuery(name));
    }

    public ApiCaller<ResultsPage<Location>> byQuery(String name, Param... params) {
        return new ApiCaller<>(locationSearchCaller.byQuery(name, ParamsConverter.asMap(params)));
    }

    public ApiCaller<ResultsPage<Location>> byLocation(LocationParam location) {
        return new ApiCaller<>(locationSearchCaller.byLocation(location.toString()));
    }

    public ApiCaller<ResultsPage<Location>> byLocation(LocationParam location, Param... params) {
        return new ApiCaller<>(locationSearchCaller.byLocation(location.toString(), ParamsConverter.asMap(params)));
    }

}
