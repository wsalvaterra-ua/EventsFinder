package ru.blizzed.opensongkick.methods.search;

import ru.blizzed.opensongkick.ApiCaller;
import ru.blizzed.opensongkick.models.ResultsPage;
import ru.blizzed.opensongkick.models.Venue;
import ru.blizzed.opensongkick.params.Param;
import ru.blizzed.opensongkick.params.ParamsConverter;

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
