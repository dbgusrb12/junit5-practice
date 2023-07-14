package com.hg.junit5practice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Study3Test {

    @Test
    @DisplayName("스터디 생성 null 체크")
    void create_new_study_not_null() {
        Study study = new Study();

        // assertNotNull(Object actual);
        // 해당 파라미터의 값이 null 이 아닌지 체크
        assertNotNull(study);
    }

    @Test
    @DisplayName("스터디 생성 값 일치 여부 확인")
    void create_new_study_equals_status() {
        Study study = new Study();

        // assertEquals(Object expected, Object actual, String message);
        // expected(기대 값) 와 actual(실제 값) 이 서로 같은지 확인 후 아닐 경우 message 표시
        // 파라미터 순서는 원하는 기대 값, 실제 값 순서로 사용한다. (순서를 변경해도 무방하긴 하지만, argument 이름에 맞춰서 사용)
        assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DRAFT 여야 합니다.");
    }

    @Test
    @DisplayName("스터디 생성 값 일치 여부 확인 2")
    void create_new_study_equals_status_2() {
        Study study = new Study();

        // Supplier 를 사용하여 노출 할 메세지를 람다로 생성 가능
        // assertEquals(Object expected, Object actual, Supplier<String> messageSupplier);
        assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 " + StudyStatus.DRAFT + " 여야 합니다.");
    }

    @Test
    @DisplayName("스터디 생성 여러 테스트 한번에 실행")
    void create_new_study_all() {
        Study study = new Study();

        // 모든 테스트를 실행 후 한번에 결과 반환
        // assertAll(Executable... executables);
        assertAll(
            () -> assertNotNull(study),
            () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DRAFT 여야 합니다."),
            () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 " + StudyStatus.DRAFT + " 여야 합니다.")
        );
    }

    @Test
    @DisplayName("스터디 생성 에러 발생 여부 체크")
    void create_new_study_throws() {
        // assertThrows(Class<T> expectedType, Executable executable);
        // executable 을 실행 할 때 expectedType 의 exception 발생
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-1));
        // 발생 한 exception 을 리턴 받아서 다른 테스트 또한 가능
        String message = exception.getMessage();
        assertEquals("limit 은 0보다 커야합니다.", message);
    }

    @Test
    @DisplayName("스터디 생성 런타임 시간 체크")
    void create_new_study_timeout() {
        // assertTimeout(Duration timeout, Executable executable);
        // executable 을 실행 할 때 timeout 안에 성공 하는지 확인
        // executable 의 로직이 실행 될 때 까지 기다린 후 처리된다.
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
    }

    @Test
    @DisplayName("스터디 생성 런타임 시간 체크 2")
    void create_new_study_timeout_2() {
        // assertTimeoutPreemptively(Duration timeout, Executable executable);
        // executable 을 실행 할 때 timeout 안에 성공 하는지 확인
        // timeout 값이 지나면 바로 종료되지만, 주의해서 사용해야 한다.
        // executable 을 실행하는 Thread 와 Transaction 을 관리하는 Thread 가 다르다면 Transaction 이 제대로 동작 안 할 수도 있다.
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
    }

    @Test
    @DisplayName("스터디 생성 assertJ 사용")
    void create_new_study_assertJ() {
        // assertJ 나 Hamcrest 같은 third party library 들이 spring boot starter test 에 들어있다.
        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(10);
    }

}
