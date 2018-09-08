package de.andreasgerhard.mapstruct.builder;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TestResultFactory {

    public static final TestResultFactory INSTANCE = Mappers.getMapper(TestResultFactory.class);

    @Mappings(
            {
                    @Mapping(source = "testDate", target = "testDate"),
                    @Mapping(source = "testVal", target = "testVal"),
                    @Mapping(source = "testStr", target = "testStr"),
            }
    )
    public TestResult create(TestBuilderDto dto);

    @Mappings(
            {
                    @Mapping(source = "testDate", target = "testDate"),
                    @Mapping(source = "testVal", target = "testVal"),
                    @Mapping(source = "testStr", target = "testStr"),
            }
    )
    public TestResult create(TestLombokDto dto);

    @Mappings(
            {
                    @Mapping(source = "testDate", target = "testDate"),
                    @Mapping(source = "testVal", target = "testVal"),
                    @Mapping(source = "testStr", target = "testStr"),
            }
    )
    public TestResult create(TestNormalDto dto);

}
