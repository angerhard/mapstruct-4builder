package de.andreasgerhard.mapstruct.builder;

import java.util.Date;

public class TestNormalDto extends Check {

    private String testStr;
    private int testVal;
    private Date testDate;

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public int getTestVal() {
        return testVal;
    }

    public void setTestVal(int testVal) {
        this.testVal = testVal;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }
}
