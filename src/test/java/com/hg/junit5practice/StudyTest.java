package com.hg.junit5practice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class StudyTest {

    @Test
    void create() {
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create");
    }

    @Test // 테스트용 로직
    @Disabled // 테스트 제외
    void create1() {
        System.out.println("create1");
    }

    @BeforeAll // 모든 테스트가 실행되기 전 실행되는 로직 (static void 로 작성, 메서드 이름은 상관 없음)
    static void beforeAll() {
        System.out.println("Before all");
    }

    @AfterAll // 모든 테스트가 실행 된 후 실행되는 로직 (static void 로 작성, 메서드 이름은 상관 없음)
    static void afterAll() {
        System.out.println("After all");
    }

    @BeforeEach // 각각의 테스트가 실행되기 이전에 실행되는 로직
    void beforeEach() {
        System.out.println("Before each");
    }

    @AfterEach // 각각의 테스트가 실행 된 후에 실행되는 로직
    void afterEach() {
        System.out.println("After each");
    }
}