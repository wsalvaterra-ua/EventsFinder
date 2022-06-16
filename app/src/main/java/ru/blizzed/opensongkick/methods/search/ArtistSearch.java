package ru.blizzed.opensongkick.methods.search;

import ru.blizzed.opensongkick.ApiCaller;
import ru.blizzed.opensongkick.models.Artist;
import ru.blizzed.opensongkick.models.ResultsPage;
import ru.blizzed.opensongkick.params.Param;
import ru.blizzed.opensongkick.params.ParamsConverter;

public class ArtistSearch {

    private ArtistSearchCaller artistSearchCaller;

    public ArtistSearch(ArtistSearchCaller artistSearchCaller) {
        this.artistSearchCaller = artistSearchCaller;
    }

    public ApiCaller<ResultsPage<Artist>> byName(String name) {
        return new ApiCaller<>(artistSearchCaller.byName(name));
    }

    public ApiCaller<ResultsPage<Artist>> byName(String name, Param... params) {
        return new ApiCaller<>(artistSearchCaller.byName(name, ParamsConverter.asMap(params)));
    }

}
