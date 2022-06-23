package api.blizzed.opensongkick.params;

public class LocationParam<T> extends Param<T> {

    public enum Type {
        SK, GEO, IP, CLIENTIP
    }

    private Type type;

    public LocationParam(Type type) {
        super("location");
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.name().toLowerCase() + ":" + super.toString();
    }

}
