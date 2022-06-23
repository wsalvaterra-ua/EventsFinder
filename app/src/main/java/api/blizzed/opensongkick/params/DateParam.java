package api.blizzed.opensongkick.params;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateParam extends Param<Long> {

    public DateParam(String name) {
        super(name);
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(data[0]);
    }
}
