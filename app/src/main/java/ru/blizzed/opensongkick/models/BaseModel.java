package ru.blizzed.opensongkick.models;

public abstract class BaseModel extends SimpleBaseModel {

    private long id;
    private String uri;

    public long getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

}
