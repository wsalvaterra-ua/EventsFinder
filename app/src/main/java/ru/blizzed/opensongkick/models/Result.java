package ru.blizzed.opensongkick.models;

import com.google.gson.annotations.SerializedName;

public class Result<ResultType> {

    @SerializedName("resultsPage")
    private Container<ResultType> container;

    public ResultType getContent() {
        return container.result.content;
    }

    private static class Container<ResultType> {
        private Status status;
        @SerializedName("results")
        private Results<ResultType> result;

        private static class Results<ResultType> {
            @SerializedName(value = "artist", alternate = {"event", "location", "venue"})
            ResultType content;
        }
    }

}
