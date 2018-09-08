package de.andreasgerhard.mapstruct.builder;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Check {

    public abstract String getTestStr();

    public abstract int getTestVal();

    public abstract Date getTestDate();

    public boolean check() {
        boolean result = true;
        result &= getTestStr() != null && getTestStr().equals("test");
        result &= getTestVal() == -100;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        result &= simpleDateFormat.format(getTestDate()).equals(simpleDateFormat.format(new Date()));
        return result;
    }

}
