package ru.blizzed.opensongkick.params;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TimestampParam extends Param<Long> {

    public TimestampParam(String name) {
        super(name);
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return df.format(data[0]);
    }
}
