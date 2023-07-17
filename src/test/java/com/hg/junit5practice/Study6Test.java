package com.hg.junit5practice;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class Study6Test {

    @DisplayName("파라미터 포함 테스트 empty, null")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"날씨가", "많이", "더워지고", "있네요."})
    // 빈 문자열을 테스트 파라미터로 전달
    @EmptySource
    // null 값을 테스트 파라미터로 전달
    @NullSource
    // @EmptySource, @NullSource 합친 어노테이션
    @NullAndEmptySource
    void parameterized_test(String message) {
        System.out.println(message);
    }

    @DisplayName("파라미터 포함 테스트, value source type int 로 변경")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(ints = {10, 20, 40})
    void parameterized_test_ints(Integer limit) {
        System.out.println(limit);
    }

    @DisplayName("파라미터 포함 테스트 Custom Converter 구현")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(ints = {10, 20, 40})
    void parameterized_test_ints(@ConvertWith(StudyConverter.class) Study study) {
        // Converter 를 추가하여 원하는 객체로 convert 할 수 있다.
        System.out.println(study.getLimit());
    }

    static class StudyConverter extends SimpleArgumentConverter {

        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

    @DisplayName("파라미터 포함 테스트 CsvSource 사용")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, 자바 스터디", "20, 스프링"})
    void parameterized_test_csv(Integer limit, String name) {
        // csv source 로 여러 value 를 전달 할 수 있다.
        System.out.println(new Study(limit, name));
    }

    @DisplayName("파라미터 포함 테스트 ArgumentsAccessor 사용")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, 자바 스터디", "20, 스프링"})
    void parameterized_test_csv(ArgumentsAccessor argumentsAccessor) {
        // arguments accessor 로 원하는 파라미터를 받아 올 수 있다.
        System.out.println(new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1)));
    }

    @DisplayName("파라미터 포함 테스트 Custom Aggregator 사용")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, 자바 스터디", "20, 스프링"})
    void parameterized_test_csv(@AggregateWith(StudyAggregator.class) Study study) {
        // Aggregator 를 추가하여 원하는 객체로 변경 할 수 있다.
        System.out.println(study);
    }

    /**
     * static inner 클래스 이거나 public 클래스 여야 한다.
     */
    static class StudyAggregator implements ArgumentsAggregator {

        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return new Study(accessor.getInteger(0), accessor.getString(1));
        }
    }
}
