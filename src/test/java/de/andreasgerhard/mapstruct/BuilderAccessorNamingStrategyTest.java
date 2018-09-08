package de.andreasgerhard.mapstruct;

import de.andreasgerhard.mapstruct.builder.*;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class BuilderAccessorNamingStrategyTest {

    @Test
    public void testBuilder() {
        TestBuilderDto test = new TestBuilderDto.Builder().withTestDate(new Date()).withTestStr("test").withTestVal(-100).build();
        TestResult testResult = TestResultFactory.INSTANCE.create(test);
        assertTrue(testResult.check());
    }

    @Test
    public void testLombok() {
        TestLombokDto test = TestLombokDto.builder().testDate(new Date()).testStr("test").testVal(-100).build();
        TestResult testResult = TestResultFactory.INSTANCE.create(test);
        assertTrue(testResult.check());
    }

    @Test
    public void testNormal() {
        TestNormalDto dto = new TestNormalDto();
        dto.setTestDate(new Date());
        dto.setTestStr("test");
        dto.setTestVal(-100);
        TestResult testResult = TestResultFactory.INSTANCE.create(dto);
        assertTrue(testResult.check());
    }
}