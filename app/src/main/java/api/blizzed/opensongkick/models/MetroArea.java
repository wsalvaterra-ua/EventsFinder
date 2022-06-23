package api.blizzed.opensongkick.models;

public class MetroArea extends LocationBaseModel {

    private SimpleBaseModel country;
    private SimpleBaseModel state;

    public SimpleBaseModel getCountry() {
        return country;
    }

    public SimpleBaseModel getState() {
        return state;
    }

}
