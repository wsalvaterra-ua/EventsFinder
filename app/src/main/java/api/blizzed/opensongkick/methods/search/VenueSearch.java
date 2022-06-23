package api.blizzed.opensongkick.methods.search;

import api.blizzed.opensongkick.ApiCaller;
import api.blizzed.opensongkick.models.ResultsPage;
import api.blizzed.opensongkick.models.Venue;
import api.blizzed.opensongkick.params.Param;
import api.blizzed.opensongkick.params.ParamsConverter;

public class VenueSearch {

    private VenueSearchCaller venueSearchCaller;

    public VenueSearch(VenueSearchCaller venueSearchCaller) {
        this.venueSearchCaller = venueSearchCaller;
    }

    public ApiCaller<ResultsPage<Venue>> byQuery(String query) {
        return new ApiCaller<>(venueSearchCaller.byQuery(query));
    }

    public ApiCaller<ResultsPage<Venue>> byQuery(String query, Param... params) {
        return new ApiCaller<>(venueSearchCaller.byQuery(query, ParamsConverter.asMap(params)));
    }

}
