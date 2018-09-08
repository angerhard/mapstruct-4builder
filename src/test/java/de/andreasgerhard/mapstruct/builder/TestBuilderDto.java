package de.andreasgerhard.mapstruct.builder;

import java.util.Date;

public class TestBuilderDto extends Check {

    @Override
    public String getTestStr() {
        return testStr;
    }

    @Override
    public int getTestVal() {
        return testVal;
    }

    @Override
    public Date getTestDate() {
        return testDate;
    }

    private String testStr;
    private int testVal;
    private Date testDate;

    public TestBuilderDto(String testStr, int testVal, Date testDate) {
        this.testStr = testStr;
        this.testVal = testVal;
        this.testDate = testDate;
    }

    private TestBuilderDto(Builder builder) {
        testStr = builder.testStr;
        testVal = builder.testVal;
        testDate = builder.testDate;
    }


    public static final class Builder {
        private String testStr;
        private int testVal;
        private Date testDate;

        public Builder() {
        }

        public Builder withTestStr(String val) {
            testStr = val;
            return this;
        }

        public Builder withTestVal(int val) {
            testVal = val;
            return this;
        }

        public Builder withTestDate(Date val) {
            testDate = val;
            return this;
        }

        public TestBuilderDto build() {
            return new TestBuilderDto(this);
        }
    }
}
