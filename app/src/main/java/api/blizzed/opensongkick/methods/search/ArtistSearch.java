package api.blizzed.opensongkick.methods.search;

import api.blizzed.opensongkick.ApiCaller;
import api.blizzed.opensongkick.models.Artist;
import api.blizzed.opensongkick.models.ResultsPage;
import api.blizzed.opensongkick.params.Param;
import api.blizzed.opensongkick.params.ParamsConverter;

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
