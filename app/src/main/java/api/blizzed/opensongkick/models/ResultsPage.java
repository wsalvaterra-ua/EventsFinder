package api.blizzed.opensongkick.models;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class ResultsPage<ResultType> {

    @SerializedName("resultsPage")
    private Container<ResultType> container;

    public List<ResultType> getResults() {
        if (container.results.content == null)
            return Collections.emptyList();
        return container.results.content;
    }

    public int getTotalEntries() {
        return container.totalEntries;
    }

    public int getPerPage() {
        return container.perPage;
    }

    public int getPage() {
        return container.page;
    }

    public Status getStatus() {
        return container.status;
    }

    public boolean isPaginated() {
        return container.totalEntries > 0;
    }

    private static class Container<ResultType> {
        private int totalEntries;
        private int perPage;
        private int page;
        private Status status;
        private Results<ResultType> results;

        private static class Results<ResultType> {
            @SerializedName(value = "artist", alternate = {"event", "location", "venue"})
            List<ResultType> content;
        }
    }

}
