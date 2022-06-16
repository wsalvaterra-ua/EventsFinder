package ru.blizzed.opensongkick.params;

public class ClientIpLocation extends LocationParam {

    public ClientIpLocation() {
        super(Type.CLIENTIP);
    }

    @Override
    public String toString() {
        return getType().name().toLowerCase();
    }
}
