package api.blizzed.opensongkick.models;

import com.google.gson.annotations.SerializedName;

public class Error {

    @SerializedName("resultsPage")
    private Container errorContainer;

    public String getMessage() {
        return errorContainer.message;
    }

    private static class Container {
        private String message;
    }
}
