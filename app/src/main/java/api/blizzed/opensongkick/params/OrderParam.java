package api.blizzed.opensongkick.params;

public class OrderParam extends EnumParam<OrderParam.Type> {

    public enum Type {
        DESC, ASC
    }

    public OrderParam() {
        super("order");
    }


}
