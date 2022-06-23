package api.blizzed.opensongkick.params;

public class ReasonParam extends EnumParam<ReasonParam.Reason> {

    public enum Reason {
        TRACKED_ARTIST, ATTENDANCE
    }

    public ReasonParam() {
        super("reason");
    }

}
