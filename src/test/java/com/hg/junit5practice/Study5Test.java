package com.hg.junit5practice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Study5Test {

    @RepeatedTest(10)
    @DisplayName("반복 테스트")
    void repeat_test() {
        System.out.println("test");
    }

    @RepeatedTest(10)
    @DisplayName("반복 정보 포함 테스트")
    void repeat_test_with_arguments(RepetitionInfo repetitionInfo) {
        System.out.println("test " + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("반복 테스트 이름 변경")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition} / {totalRepetitions}")
    void repeat_test_with_name(RepetitionInfo repetitionInfo) {
        System.out.println("test " + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("파라미터 포함 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"날씨가", "많이", "더워지고", "있네요."})
    void parameterized_test(String message) {
        System.out.println(message);
    }

    @DisplayName("파라미터 포함 테스트 이름 변경")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"날씨가", "많이", "더워지고", "있네요."})
    void parameterized_test_name(String message) {
        System.out.println(message);
    }
}
