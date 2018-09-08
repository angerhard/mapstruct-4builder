package de.andreasgerhard.mapstruct.builder;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class TestLombokDto extends Check {
    private String testStr;
    private int testVal;
    private Date testDate;
}
