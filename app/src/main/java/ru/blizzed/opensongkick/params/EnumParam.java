package ru.blizzed.opensongkick.params;

public class EnumParam<EnumType extends Enum> extends Param<EnumType> {

    public EnumParam(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

}
